package com.wy.socket.filter;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.logging.LoggingFilter;

/**
 * 记录日志
 * Created by 宇 on 2016/2/18.
 */
public class MyLogger extends LoggingFilter {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
        logger.info("Session [" + session.getRemoteAddress().toString() + "] be Created...");
        super.sessionCreated(nextFilter, session);
    }

    @Override
    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception


    {
        logger.info("Session [" + session.getRemoteAddress().toString() + "] be Opened...");
        super.sessionOpened(nextFilter, session);
    }

    @Override
    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
        logger.info("Session [" + session.getRemoteAddress().toString() + "] be Closed...");
        super.sessionClosed(nextFilter, session);
    }

    @Override
    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
        logger.info("Session [" + session.getRemoteAddress().toString() + "] be Idled...");
        super.sessionIdle(nextFilter, session, status);
    }

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        logger.info("Session [" + session.getRemoteAddress().toString() + "] received message : " + message.toString());
        super.messageReceived(nextFilter, session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        logger.info("Session [" + session.getRemoteAddress().toString() + "] sent message : " + writeRequest.getMessage().toString());
        super.messageSent(nextFilter, session, writeRequest);
    }


}
