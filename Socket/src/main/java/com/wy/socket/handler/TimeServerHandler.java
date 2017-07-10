package com.wy.socket.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 宇 on 2016/2/3.
 */
public class TimeServerHandler extends IoHandlerAdapter implements Runnable{

    List<IoSession> sessionList = new ArrayList<IoSession>();
    /**
     * 异常处理
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    /**
     * 接收消息处理方法
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
//        System.out.println("Message received:"+str);
//        session.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if(str.trim().equalsIgnoreCase("quit")){
            session.close(true);
            return;
        }
    }

    /**
     * 发送消息处理方法
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    /**
     * session关闭
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    /**
     * session创建
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    /**
     * 空闲处理
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("IDLE..."+session.getIdleCount(status));
    }

    /**
     * session打开
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        sessionList.add(session);
        run();
        super.sessionOpened(session);
    }

    public void run() {
        while(true) {
            for (IoSession session : sessionList) {
                session.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
