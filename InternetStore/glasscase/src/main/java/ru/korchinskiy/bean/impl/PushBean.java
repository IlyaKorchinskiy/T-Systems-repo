package ru.korchinskiy.bean.impl;

import org.apache.log4j.Logger;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PushBean {
    private static Logger logger = Logger.getLogger(PushBean.class);

    @Inject
    @Push
    private PushContext productChannel;

    public void sendMessage(String message) {
        productChannel.send(message);
        logger.info(message);
    }
}
