package ru.korchinskiy.bean.impl;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PushBean {
    @Inject
    @Push
    private PushContext productChannel;

    public void sendMessage(String message) {
        productChannel.send(message);
    }
}
