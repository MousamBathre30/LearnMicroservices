package com.rabbitMQ.controller;

import com.rabbitMQ.dto.Users;
import com.rabbitMQ.publisher.RabbitMQJsonPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonPublisher rabbitMQJsonPublisher;


    public MessageJsonController(RabbitMQJsonPublisher rabbitMQJsonPublisher) {
        this.rabbitMQJsonPublisher = rabbitMQJsonPublisher;
    }

   // http://localhost:8080/api/v1/publish
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Users user){
        rabbitMQJsonPublisher.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sent to RabbitMQ...");

    }
}
