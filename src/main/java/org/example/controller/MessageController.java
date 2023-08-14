package org.example.controller;

import org.example.service.MessageService;
import org.example.model.Message;
import org.example.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages/{vendorId}")
    public List<MessageResponse> getMessagesByVendorId(@PathVariable String vendorId) {
        return messageService.getMessagesByVendorId(vendorId);
    }
    @PutMapping("/messages/{vendorId}")
    public void updateMessagesByVendorId(@PathVariable String vendorId) {
        messageService.updateMessagesByVendorId(vendorId);
    }
    @PostMapping("/messages")
    public Message saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }
}
