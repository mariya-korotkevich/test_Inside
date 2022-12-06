package test_Inside.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test_Inside.models.dto.MessageRequestDTO;
import test_Inside.models.dto.MessageResponseDTO;
import test_Inside.service.abstracts.MessageService;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<MessageResponseDTO> processMessage(@RequestBody MessageRequestDTO messageDto){
        return ResponseEntity.ok(messageService.processMessage(messageDto));
    }
}
