package com.swathiprasad.sqsintegration.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageReceiverService {

    @SqsListener(value= "sqs-demo-queue", pollTimeoutSeconds="20")
    public void queueListener(String message) {
        log.info("Received message {}", message);
    }
}
