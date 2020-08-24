package com.example.rabbitmq.producer.service;

import com.example.rabbitmq.common.config.ConsumerConfig;
import com.example.rabbitmq.common.model.MessageEntity;
import com.example.rabbitmq.common.model.MessageQueueEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessageToRabbitMQ(final MessageEntity message){
        try {
            log.info("Mensagem recebida PRODUCER: {}", message.toString());
            MessageQueueEntity messageQueueEntity = new MessageQueueEntity(Collections.singletonList(message));
            String json = objectMapper.writeValueAsString(messageQueueEntity);
            rabbitTemplate.convertAndSend(ConsumerConfig.EXCHANGE_NAME, "", json);
//            rabbitTemplate.convertAndSend(ProducerConfig.EXCHANGE_NAME, json);
            log.info("Mensagem enviada para o rabbitMQ com sucesso");
        } catch (JsonProcessingException e) {
            log.error("Deu ruim para converter pra json - {}", e.getMessage());
        }
    }


}
