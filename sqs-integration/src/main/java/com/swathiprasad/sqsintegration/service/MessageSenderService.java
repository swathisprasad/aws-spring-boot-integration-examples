package com.swathiprasad.sqsintegration.service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class MessageSenderService {

    private final SqsTemplate sqsTemplate;
    private final String queueName;


    public void sendMessage(String message) {
        log.info("Sending message {}", message);
        this.sqsTemplate.send(queueName, MessageBuilder.withPayload(message).build());
    }
}
