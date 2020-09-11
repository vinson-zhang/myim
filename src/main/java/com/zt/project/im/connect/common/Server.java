package com.zt.project.im.connect.common;

import com.zt.project.im.proto.Message;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:03 2018/3/31
 */
@Component
public class Server {

    private static final Logger logger = Logger.getLogger(Server.class);
    /**
     * 创建bootstrap
     */
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    /**
     * BOSS
     */
    EventLoopGroup boss = new NioEventLoopGroup();
    /**
     * Worker
     */
    EventLoopGroup work = new NioEventLoopGroup();

    /**
     * handler
     */
    ServerHandler serverHandler = new ServerHandler();

    /**
     * 端口号
     */
    private static final Integer port = 8888;


    /**
     * 关闭服务器方法
     */
    @PreDestroy
    public void close() {
        logger.info("关闭服务器....");
        //优雅退出
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    /**
     * 开启及服务线程
     */
    @PostConstruct
    public void start() {
        // 从配置文件中(application.yml)获取服务端监听端口号
        serverBootstrap.group(boss, work);
        serverBootstrap.channel(NioServerSocketChannel.class);
        new Thread(this::run).start();
    }

    private void run() {
        try {
            //设置事件处理
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    //1、按照分隔符切割消息
//                    pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
//                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufDecoder(Message.BaseMessage.getDefaultInstance()));
                    pipeline.addLast(new ProtobufEncoder());
//                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    //自定义handler
                    pipeline.addLast(new ServerHandler());
                }
            });
            logger.info(String.format("netty服务器在%s端口启动监听........",port));
            ChannelFuture f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
