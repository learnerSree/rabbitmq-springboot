package com.sree.controller;


import com.sree.entity.Employee;
import com.sree.publisher.RabbitMQJsonProducer;
import com.sree.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @Autowired
    RabbitMQJsonProducer rabbitMQJsonProducer;

    @GetMapping("/send/{msg}")
    public ResponseEntity<String> sendMessageToProducer( @PathVariable("msg") String message ){

        rabbitMQProducer.sendMessage( message );
        return new ResponseEntity<>("Message send to RabbitMQ", HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendJsonMessageToProducer( @RequestBody Employee employee ){

        rabbitMQJsonProducer.sendMessage( employee );
        return new ResponseEntity<>( "Message send to RabbitMQ", HttpStatus.OK);
    }
}
