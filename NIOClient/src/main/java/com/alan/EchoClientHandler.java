package com.alan;

import com.alan.tcp.face.CommonManager;
import com.alan.tcp.face.RequestModel;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.awt.image.BufferedImage;

@ChannelHandler.Sharable     //⇽---  标记该类的实例可以被多个Channel共享
public class EchoClientHandler extends
        SimpleChannelInboundHandler<ByteBuf> {



    //在到服务器的连接已经建立之后将被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",     //⇽--  当被通知Channel是活跃的时候，发送一条消息
                CharsetUtil.UTF_8));
    }


    //当从服务器接收到一条消息时被调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println(    //⇽---  记录已接收消息的转储
        "Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.disconnect();
    }

    //在处理过程中引发异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,     //⇽---  在发生异常时，记录错误并关闭Channel
            Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args){
        RequestModel model = new RequestModel();
        model.setCommand(CommonManager.PIC_BEGIN);
        System.out.println(JSON.toJSONString(model));
    }
}
