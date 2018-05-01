package com.zt.project.im.connect.websocket;

import com.zt.project.im.connect.common.Server;
import com.zt.project.im.connect.common.ServerHandler;
import com.zt.project.im.protobuf.Message;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class WebSocketServer {

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
	private static final Integer port = 8889;


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
		serverBootstrap.handler(new  LoggingHandler(LogLevel.INFO));
		new Thread(this::run).start();
	}

	private void run() {
		try {
			//设置事件处理
			serverBootstrap.childHandler(new WebSocketChannelInitializer());
			logger.info(String.format("netty websocket 服务器在%s端口启动监听......",port));
			ChannelFuture f = serverBootstrap.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}

}
