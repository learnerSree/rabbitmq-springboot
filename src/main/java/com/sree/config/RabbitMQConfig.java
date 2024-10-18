package com.sree.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name:queue_demo}")
    private String queueName;

    @Value("${rabbitmq.queue.json.name:queue_json_demo}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name:exchange_demo}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key.string:key_demo}")
    private String routingKeyString;

    @Value("${rabbitmq.routing.key.json:key_demo}")
    private String routingKeyJson;


    //Bean for rabbitMQ Queue - String
    @Bean
    public Queue queue(){

        return new Queue( queueName );
    }


    //Bean for rabbitMQ Queue - Json
    @Bean
    public Queue jsonQueue(){

        return new Queue( jsonQueueName );
    }


    //Bean for rabbitMQ Exchange
    @Bean
    public TopicExchange exchange(){

        return new TopicExchange( topicExchangeName);
    }


    //Bean for rabbitMQ Binding - String messages
    @Bean
    public Binding bindingForString(){

        return  BindingBuilder
                .bind( queue() )
                .to( exchange() )
                .with( routingKeyString );
    }


    //Bean for rabbitMQ Binding - JSON messages
    @Bean Binding bindingForJson(){

        return  BindingBuilder
                .bind( jsonQueue() )
                .to( exchange() )
                .with( routingKeyJson );
    }


    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory ){

        RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory );
        rabbitTemplate.setMessageConverter( messageConverter() );
        return rabbitTemplate;
    }
}
