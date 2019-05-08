package ru.korchinskiy.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

@Configuration
public class MessageConfig {
    private static final String DEFAULT_BROKER_URL = "tcp://glasscase:61616";
    private static final String PRODUCT_QUEUE = "jms.queue.ProductQueue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("ru.korchinskiy"));
        connectionFactory.setUserName("guest");
        connectionFactory.setPassword("123456");
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(PRODUCT_QUEUE);
        return template;
    }

}
