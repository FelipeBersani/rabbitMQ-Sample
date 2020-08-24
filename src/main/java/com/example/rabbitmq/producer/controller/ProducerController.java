package com.example.rabbitmq.producer.controller;

import com.example.rabbitmq.common.model.MessageEntity;
import com.example.rabbitmq.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/sendMessage")
    public ResponseEntity postMessage(@RequestBody MessageEntity message){
        producerService.sendMessageToRabbitMQ(message);
        return ResponseEntity.noContent().build();
    }
}
