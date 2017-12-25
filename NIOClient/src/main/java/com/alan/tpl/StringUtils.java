package com.alan.tpl;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.List;

/**
 * Created by Ke Zhang on 2017/11/9.
 */
public class StringUtils {

    // 图片转化成base64字符串
    @SuppressWarnings("restriction")
    public static String GetImageStr(String strPicPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(strPicPath);
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            System.out.println("文件初始化失败");
        } finally {
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
            }
        }
        // 对字节数组Base64编码

        return Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean listIsEmpty(List obj){
        if(obj == null || obj.size() == 0){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String s){
        if(s == null || s.trim().length() == 0){
           return true;
        }
        return false;
    }

    public static void SaveToFile(String fileName, String contexts) {
        File file;
        BufferedWriter bw = null;
        try {
            file = new File(fileName);

            if (!(file.exists())) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(contexts);


            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null){
                    bw.close();
                }
            } catch (IOException e) {

            }
        }
    }
}
