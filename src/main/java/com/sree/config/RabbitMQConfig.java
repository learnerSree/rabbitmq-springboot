package com.sree.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name:queue_demo}")
    private String queueName;

    @Value("${rabbitmq.exchange.name:exchange_demo}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key:key_demo}")
    private String routingKey;


    //Bean for rabbitMQ Queue
    @Bean
    public Queue queue(){

        return new Queue( queueName );
    }


    //Bean for rabbitMQ Exchange
    @Bean
    public TopicExchange exchange(){

        return new TopicExchange( topicExchangeName);
    }


    //Bean for rabbitMQ Binding
    @Bean
    public Binding binding(){

        return  BindingBuilder
                .bind( queue() )
                .to( exchange() )
                .with( routingKey );
    }


    //Bean for Connection Factory
    //Bean for template
    //Bean for admin
}
