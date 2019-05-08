package ru.korchinskiy.bean.impl;


import org.apache.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/ProductQueue")
})
public class MessageEJB implements MessageListener {
    private static Logger logger = Logger.getLogger(ProductEJBImpl.class);

    @Inject
    private PushBean pushBean;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = null;
        try {
            if (message instanceof TextMessage) {
                textMessage = (TextMessage) message;
                String msg = textMessage.getText();
                if (msg.equals("Update")) {
                    pushBean.sendMessage(msg);
                }
            }
        } catch(JMSException e){
            logger.info("getMessageError", e);
        }
    }
}
