package kl.socialnetwork.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/message")
public class MessageController {

    private final SimpMessagingTemplate template;
    private final MessageService messageService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    @Autowired
    public MessageController(SimpMessagingTemplate template, MessageService messageService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.template = template;
        this.messageService = messageService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }
}
