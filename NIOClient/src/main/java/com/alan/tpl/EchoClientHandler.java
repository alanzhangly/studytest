package com.alan.tpl;

import io.netty.buffer.*;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable     //⇽---  标记该类的实例可以被多个Channel共享
public class EchoClientHandler extends
        SimpleChannelInboundHandler<ByteBuf> {

    //在到服务器的连接已经建立之后将被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.alloc();
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",     //⇽--  当被通知Channel是活跃的时候，发送一条消息
//                CharsetUtil.UTF_8));
        ByteBuf packData = Unpooled.buffer();
        System.out.println(packData.writerIndex());
        packData.writeByte(0x01);
        System.out.println(packData.writerIndex());
        String s = StringUtils.GetImageStr("D:\\2017-12-12\\6.jpg");
        System.out.println(s);
//        s = s.replace('\r\n','');
        FaceImgRequest image = new FaceImgRequest("myImage",s);
        byte[] data = image.toString().getBytes();
        System.out.println(data.length + " : length");
        packData.writeInt(data.length).writeBytes(data);
        System.out.println(packData.writerIndex());
        ctx.writeAndFlush(packData);
    }


    //当从服务器接收到一条消息时被调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println(    //⇽---  记录已接收消息的转储
       in.toString(CharsetUtil.UTF_8));
//        byte[] bytes = new byte[in.readableBytes()];
//        in.getBytes(0,bytes);
//        System.out.print(new String(bytes));
    }

    //在处理过程中引发异常时被调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,     //⇽---  在发生异常时，记录错误并关闭Channel
            Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args){
        ByteBufAllocator allocator = new PooledByteBufAllocator();
        allocator.heapBuffer();
        ByteBuf bf = Unpooled.copiedBuffer("dsaxxasxa",CharsetUtil.UTF_8);
        System.out.println(bf.toString());
        System.out.println(bf.toString(CharsetUtil.UTF_8));
    }
}
