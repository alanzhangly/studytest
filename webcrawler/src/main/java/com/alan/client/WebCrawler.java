package com.alan.client;

import com.alan.exception.MyException;
import com.alan.exception.ReturnCodeModel;
import com.alan.properties.Properties;
import com.alan.util.HtmlParserTool;
import com.alan.util.LinkFilter;
import com.alan.util.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Ke Zhang on 2017/10/12.
 */
public class WebCrawler {

    private static HttpClient client;
    private static RequestConfig requestConfig;

    private static BlockingQueue<String> pendingURL;
    private static HashSet<String> processedURL;
    private static volatile int threadNumber = 0;  //正在运行的线程数量

    static {
         requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).build();

         //没有采用池化管理
//         client = HttpClients.createDefault();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        manager.setMaxTotal(200);
        //设置每个路由最大连接数
        manager.setDefaultMaxPerRoute(20);
        //采用池化管理
        client = HttpClients.custom().setConnectionManager(manager).build();

        pendingURL = new ArrayBlockingQueue<String>(100);
        processedURL = new HashSet<>(1000);
    }

    public static void init(){
        pendingURL.offer("http://fanyi.youdao.com/");
    }

    public static void main(String[] args){

        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        init();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //不断向连接池中加任务时， 会加载任务依赖的类文件，导致内存溢出
        while(true){
            if(threadNumber < 5){
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = null;
                        try {
                            url = pendingURL.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("开始解析 " + url);
                        WebCrawler.run(url);
                        System.out.println("解析完成 " + url);
                        threadNumber -- ;
                    }
                });
                threadNumber ++;
            }
            else {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    public static void run(String uri){
        //判断该uri是否已经检索过
        if(processedURL.contains(uri)){
            System.out.println("重复解析 " + uri);
            return;
        }
        //处理一个页面---直到下载
        parseHtml(uri);
        //添加到已处理列表
        processedURL.add(uri);
        //从给定页面得到更多的页面
        Set<String> newUrl = HtmlParserTool.extracLinks(uri, new LinkFilter() {
            @Override
            public boolean accept(String url) {
//                if(-1 != url.indexOf("blog.csdn.net")){
//                    return true;
//                }
//                else {
//                    return false;
//                }
                if(!StringUtils.isEmpty(url)){
                    return true;
                }
                return false;
            }
        });
        //添加到待处理队列
        for(String url : newUrl){
            pendingURL.offer(url);
        }
    }

    /**
     * 解析指定的uri
     * @param uri
     */
    public static void parseHtml(String uri){
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(requestConfig);
        InputStream inputStream = null;
        HttpEntity httpEntity =  null;
        try{
            //发送get请求并获得响应结果
            HttpResponse httpResponse = client.execute(httpGet);
            System.out.println(uri + "  连接成功");
            httpEntity = httpResponse.getEntity();

            //文件类型处理,只要text,html,jsp等页面文件或者图片文件
            if(httpEntity.getContentType().getValue().indexOf("text/html") < 0){
                System.out.println(uri + "结果不为 " + "text/html");
                return;
            }

            //响应码状态处理
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode != HttpStatus.SC_OK){
                System.out.println("解析" + uri + "出现异常:"+statusCode);
                return;
            }
//            //当响应码是200+ 转发的时候
//            if(statusCode == HttpStatus.SC_OK){
//                //Todo
//            }
//            //请求已经接收，服务器在做进一步处理
//            if(statusCode == HttpStatus.SC_ACCEPTED){
//                //Todo
//            }
//            //当响应码是300+ 转发的时候
//            if(statusCode == HttpStatus.SC_MULTIPLE_CHOICES || statusCode == HttpStatus.SC_MOVED_PERMANENTLY
//                    || statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_NOT_MODIFIED){
//                Header header = httpResponse.getLastHeader("location");
//                String newUrl = header.getValue();
//                //使用post转向
//                HttpPost httpPost = new HttpPost(newUrl);
//                //Todo
//
//            }
            //响应码状态处理结束
//            System.out.println(EntityUtils.toString(httpEntity,"utf-8"));

            inputStream = httpEntity.getContent();
            //todo 线程池管理下载
            downFile(uri, inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            EntityUtils.consumeQuietly(httpEntity);
        }
    }

    /**
     * 将给定uri文本内容下载到电脑上--->应新开一个线程
     * @param uri
     * @param inputStream
     */
    public static void downFile(String uri, InputStream inputStream){
        Map<String, String> nameMap = null;
        try {
            nameMap = parseURI(uri);
        } catch (MyException e) {
            e.printStackTrace();
            return;
        }
        String fileName = nameMap.get("fileName");
        try(FileOutputStream fileOutputStream = new FileOutputStream(nameMap.get("dirName") + File.separator + fileName)) {
            System.out.println("开始下载文件：" + fileName);
            byte[] buff = new byte[1024];
            int len;
            while(-1 != (len = inputStream.read(buff))){
                fileOutputStream.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return;
        }
    }

    /**
     * 从给定的uri中解析出文件名，并创建相应文件夹
     * @param uri
     * @return
     * @throws MyException
     */
    public static Map parseURI (String uri) throws MyException {
        if(StringUtils.isEmpty(uri)){
            throw new MyException(ReturnCodeModel.URI_IS_NULL);
        }
        String url = uri.substring(uri.indexOf("//") + 2);
        String[] paths = url.split("/");

        //创建网站文件夹
        StringBuilder fileDir = new StringBuilder(Properties.rootDir + File.separator + paths[0]);
        File file = new File(fileDir.toString());
        if(!file.exists()){
            file.mkdir();
            System.out.println("创建文件夹"+fileDir.toString());
        }
        //创建定时文件夹
        fileDir.append(File.separator + "test1");
        File subFile = new File(fileDir.toString());
        if(!subFile.exists()){
            subFile.mkdir();
        }
        String fileName = null;
        if(paths.length == 1){
            fileName = "index.html";
        }
        else {
            fileName = paths[paths.length - 1];
            //特殊字符转换，暂时只处理“？”
            fileName = fileName.replace("?","");
        }
        if(fileName.indexOf(".") < 0){
            fileName = fileName.concat(".txt");
        }

//        //当该uri不是网站主页的时候，递归创建文件夹
//        if(paths.length == 2){
//            fileName = paths[1];
//        }
//        if(paths.length > 2){
//            List<String> pathList = Arrays.asList(paths);
//            //去掉最后一个代表文件名的字符串
//            fileName = pathList.get(pathList.size() -1);
////            pathList.remove(pathList.size() -1);
//            File subFile;    //待创建的目录
//            int index = 0;  //待创建的目录名的指针
//            while(index < pathList.size() - 1){
//                fileDir.append(File.separator + pathList.get(index));
//                subFile = new File(fileDir.toString());
//                if(!subFile.exists()){
//                    subFile.mkdir();
//                    System.out.println("创建文件夹"+fileDir.toString());
//                }
//                index ++;
//            }
//        }
//        fileName.replaceAll("\\.","_").concat(".txt");
        Map<String, String> result = new HashMap<String, String>();
        result.put("dirName", fileDir.toString());
        result.put("fileName", fileName);
        return result;
    }

    public static void main1(String[] args){
        String uri = "http://www.baidu.com/index.html";
//        try {
//            Map map = parseURI(uri);
//            System.out.println(map.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String fileName = "www.baidu.com";
        String s = fileName.replaceAll("\\.","_").concat(".txt");
        System.out.println(s);
    }


}
