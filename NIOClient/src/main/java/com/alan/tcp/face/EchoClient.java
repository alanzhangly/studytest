package com.alan.tcp.face;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;
    private ChannelFuture f;
    private EchoClientImgHandler clientHandler;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        System.out.println("The NIO-Client is Starting...");
        //⇽---  指定EventLoopGroup以处理客户端事件；需要适用于NIO的实现
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建Bootstrap
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)     //⇽---  适用于NIO传输的Channel类型
                    .remoteAddress(new InetSocketAddress(host, port))//⇽---  设置服务器的InetSocketAddr-ess ![](/api/storage/getbykey/screenshow?key=17043add7e9c14a5d3f7)
                     .handler(new ChannelInitializer<SocketChannel>() {    //⇽---  在创建Channel时，向ChannelPipeline中添加一个Echo-ClientHandler实例
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    clientHandler);
                        }
            });

//            connect()方法将会直接返回，而不会阻塞，该调用将会在后台完成。这究竟什么时候会发生
//            则取决于若干的因素，但这个关注点已经从代码中抽象出来了。因为线程不用阻塞以等待对应的
//            操作完成，所以它可以同时做其他的工作，从而更加有效地利用资源。
            f = b.connect().sync();    // ⇽---  连接到远程节点，阻塞等待直到连接完成
            f.channel().closeFuture().sync();     // ⇽---  阻塞，直到Channel关闭
        } finally {
            group.shutdownGracefully().sync();      // ⇽---  关闭线程池并且释放所有的资源
        }
    }

    public EchoClientImgHandler getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(EchoClientImgHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public static void main(String[] args) throws Exception {
        String host = "192.168.3.91";
        int port = 2556;
//        String host = "localhost";
//        int port = 9060;
        EchoClient client = new EchoClient(host, port);
        EchoClientImgHandler handler = new EchoClientImgHandler();
        client.setClientHandler(handler);
        client.start();
    }
}
