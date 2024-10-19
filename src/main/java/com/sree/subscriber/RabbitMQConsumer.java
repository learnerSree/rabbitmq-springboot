package com.sree.subscriber;


import com.sree.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger( RabbitMQConsumer.class );

    @RabbitListener( queues = { "${rabbitmq.queue.name}"} )
    public void consumeStringMessages( String message ){

        LOGGER.info( String.format( "Message received -> %S", message ) );
    }

    @RabbitListener( queues = { "${rabbitmq.queue.json.name}"} )
    public void consumeJsonMessages( Employee employee ) {

        LOGGER.info(String.format("Message received -> %s", employee));
    }

}
