package com.example.rabbitmq.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageQueueEntity {

    @JsonProperty("messageList")
    private List<MessageEntity> messages;

}
