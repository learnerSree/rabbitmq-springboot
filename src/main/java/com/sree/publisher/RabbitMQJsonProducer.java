package com.sree.publisher;


import com.sree.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.json}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger( RabbitMQJsonProducer.class );


    private RabbitTemplate rabbitTemplate;


    @Autowired
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Employee employee ){

        LOGGER.info( String.format( "Message send -> %s", employee.toString() ));
        rabbitTemplate.convertAndSend( exchange, routingKey, employee );
    }
}
