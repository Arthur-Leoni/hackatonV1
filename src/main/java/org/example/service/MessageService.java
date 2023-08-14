package org.example.service;

import org.example.model.Message;
import org.example.model.MessageResponse;
import org.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<MessageResponse> getMessagesByVendorId(String vendorId) {
        List<MessageResponse> messageList = new ArrayList<>();

        messageRepository.findByVendorId(vendorId).forEach(message -> {
            MessageResponse messageResponse = new MessageResponse();

            if(StringUtils.isEmpty(message.getStatus())){
                message.setStatus(Boolean.TRUE);
            }
            messageResponse.setMessage(message.getMessage());
            messageResponse.setStatus(message.getStatus());
            messageList.add(messageResponse);
        });
        return messageList;
    }

    public void updateMessagesByVendorId(String vendorId) {
        List<Message> messagesToUpdate = messageRepository.findByVendorId(vendorId);
        messagesToUpdate.stream().forEach(message -> {
            message.setStatus(Boolean.FALSE);
        });
        messageRepository.saveAll(messagesToUpdate);
    }
}
