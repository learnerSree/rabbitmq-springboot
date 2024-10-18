package com.sree.controller;


import com.sree.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @GetMapping("/send/{msg}")
    public ResponseEntity<String> sendMessageToProducer(@PathVariable("msg") String message ){

        rabbitMQProducer.sendMessage( message );
        return new ResponseEntity<>("Message send to RabbitMQ", HttpStatus.OK);
    }
}
