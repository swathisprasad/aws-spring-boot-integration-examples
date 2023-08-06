package com.swathiprasad.sqsintegration.controller;

import com.swathiprasad.sqsintegration.service.MessageSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class MessageController {

    private final MessageSenderService messageSenderService;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody final String message){
        messageSenderService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
