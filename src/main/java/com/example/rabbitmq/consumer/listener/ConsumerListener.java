package com.example.rabbitmq.consumer.listener;

import com.example.rabbitmq.common.config.ConsumerConfig;
import com.example.rabbitmq.common.model.MessageEntity;
import com.example.rabbitmq.common.config.WebSocketConfig;
import com.example.rabbitmq.common.model.MessageQueueEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConsumerListener {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = ConsumerConfig.QUEUE)
    public void listener(Message message){
        try {
            log.info("Mensagem recebida!");
            MessageQueueEntity messageEntity = objectMapper.readValue(message.getBody(), MessageQueueEntity.class);
            log.info("Mensagem convertida - {}", messageEntity.toString());
//            simpMessagingTemplate.convertAndSend(WebSocketConfig.BROKER, new String(message.getBody()));
//            log.info("Mensagem enviada para o socket");
        } catch (IOException e) {
            log.error("Deu ruim no objectMapper.readValue!! - {}", e.getMessage());
            e.printStackTrace();
        }
    }

}
