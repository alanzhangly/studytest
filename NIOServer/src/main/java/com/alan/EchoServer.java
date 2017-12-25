package com.alan;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println(
//                    "Usage: " + EchoServer.class.getSimpleName() +
//                            " ");
//        }
//        int port = Integer.parseInt(args[0]); //⇽---　设置端口值（如果端口参数的格式不正确，则抛出一个NumberFormatException）
        int port = 9060;
        new EchoServer(port).start();    //⇽---  调用服务器的start()方法
    }
    public void start() throws Exception {
        System.out.println("The netty-Server is Starting...");
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //NioEventLoopGroup接受和处理新的连接---进行事件处理
        EventLoopGroup group = new NioEventLoopGroup();   // ⇽---  ➊ 创建Event-LoopGroup
        try {
            //创建一个ServerBootstrap的实例以引导和绑定服务器--BootStrap:在运行时组装和配置一个应用程序的所有组件的过程
            ServerBootstrap b = new ServerBootstrap();    //⇽---   ❷ 创建Server-Bootstrap
            //这里有三个配置，指定Channel类型，指定Address， 添加一个Handle
            b.group(group)
                    .channel(NioServerSocketChannel.class)  //⇽---　 ❸ 指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port))//⇽---　 ❹ 使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer(){   // ⇽---   ❺添加一个EchoServer-Handler到子Channel的ChannelPipeline

                        @Override
                        public void initChannel(Channel ch)   //原文这里是SocketChannel
                                throws Exception {
                            ch.pipeline().addLast(serverHandler); //⇽---　 EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例
                        }
                    });
            //调用ServerBootstrap.bind()方法以绑定服务器
            ChannelFuture f = b.bind().sync();    //⇽---   ❻ 异步地绑定服务器；调用sync()方法阻塞等待直到绑定完成
            f.channel().closeFuture().sync();//⇽---　 ❼ 获取Channel的CloseFuture，并且阻塞当前线程直到它完成
        } finally {
            group.shutdownGracefully().sync(); //⇽---   ❽ 关闭EventLoopGroup，释放所有的资源
        }
    }
}
