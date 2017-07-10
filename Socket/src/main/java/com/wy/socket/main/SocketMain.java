package com.wy.socket.main;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import com.wy.socket.handler.TimeServerHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by 宇 on 2016/2/3.
 */
public class SocketMain {
    /**
     * 端口及缓存
     */
    private static final int PORT=23256,BUF_SIZE=2048;

    public static void main(String[] args) throws IOException{
        IoAcceptor acceptor = new NioSocketAcceptor();
        /**
         * 定义logger，用于记录session打开/关闭，messages接收/发送
         */
        acceptor.getFilterChain().addLast("logger",new LoggingFilter());

        /**
         * 定义编码格式
         */
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        /**
         * 指定处理类
         */
        acceptor.setHandler(new TimeServerHandler());

        /**
         * 定义消息读取缓存区大小
         */
        acceptor.getSessionConfig().setReadBufferSize(BUF_SIZE);

        /**
         * 读写 通道均在10 秒内无任何操作就进入空闲状态
         */
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);

        /**
         * 绑定socket端口
         */
        acceptor.bind(new InetSocketAddress(PORT));
    }
}
