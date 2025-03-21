package com.rabbitMQ.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitMQ.dto.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonPublisher.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson object mapper

    public RabbitMQJsonPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Users user) {
        try {
            // Convert Users object to JSON string
            String jsonMessage = objectMapper.writeValueAsString(user);

            // Log JSON message instead of object reference
            LOGGER.info("Json message sent -> {}", jsonMessage);

            // Send JSON message to RabbitMQ
            rabbitTemplate.convertAndSend(exchange, routingJsonKey, jsonMessage);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting Users object to JSON", e);
        }
    }
}
