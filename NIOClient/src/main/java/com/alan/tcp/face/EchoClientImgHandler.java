package com.alan.tcp.face;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable     //⇽---  标记该类的实例可以被多个Channel共享
public class EchoClientImgHandler extends
        SimpleChannelInboundHandler<ByteBuf> {

    byte[] b = Base64Image.GetImageBytes("D:\\img\\1.jpg");

    ByteBuf totle = Unpooled.buffer(2048);

    private boolean isFirstReceived = true;

    //在到服务器的连接已经建立之后将被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        RequestModel model = new RequestModel();
        model.setCommand(CommonManager.PIC_BEGIN);
        model.setSize(b.length);
        ctx.writeAndFlush(Unpooled.copiedBuffer(JSON.toJSONString(model),     //⇽--  当被通知Channel是活跃的时候，发送一条消息
                CharsetUtil.UTF_8));
        System.out.println(JSON.toJSONString(model));
    }


    //当从服务器接收到一条消息时被调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        if(isFirstReceived){
            System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
//            byte[] bytes = new byte[in.readableBytes()];
//            in.writeBytes(bytes);
            in.clear();
            ByteBuf byteBuf = Unpooled.buffer(b.length);
            byteBuf.writeBytes(b);
            ctx.writeAndFlush(byteBuf);
        }else {
            totle.writeBytes(in);
            in.clear();
//            in.readBytes(totle);
        }
//        in.writerIndex(in.array().length);

//        System.out.println("收到一次...." + totle.toString(CharsetUtil.UTF_8));
//        List<Float> floatList = new ArrayList<>();
//        System.out.println(in.readableBytes());
//        byte[] b = new byte[1024];
//        in.writeBytes(b);
//        System.out.println(b.length);
//        if(in.isReadable()){
//            float v = in.readFloat();
//            floatList.add(v);
//        }
//        System.out.println(floatList.size());
//        StringBuffer buffer = new StringBuffer();
//        for(int i = 0;i<500;i++){
//            buffer.append("s");
//        }
//        byteBuf.writeBytes(buffer.toString().getBytes());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
//        ctx.disconnect();
//        System.out.println(totle.readableBytes());
//        System.out.println(totle.toString(CharsetUtil.UTF_8));
        if(isFirstReceived){
            isFirstReceived = false;
        } else {
            int index = 1;
            for(int i = 0; i<512;i++){
                byte[] b = new byte[4];
                b[3] = totle.readByte();
                b[2] = totle.readByte();
                b[1] = totle.readByte();
                b[0] = totle.readByte();
                ByteBuf byteBuf = Unpooled.copiedBuffer(b);
                System.out.println(index + " : " + byteBuf.readFloat());
                index ++;
            }
        }
    }

    //在处理过程中引发异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,     //⇽---  在发生异常时，记录错误并关闭Channel
            Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
