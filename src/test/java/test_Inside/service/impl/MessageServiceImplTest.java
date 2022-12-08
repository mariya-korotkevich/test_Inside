package test_Inside.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test_Inside.models.dto.MessageRequestDTO;
import test_Inside.models.dto.MessageResponseDTO;
import test_Inside.models.entity.Message;
import test_Inside.repositories.MessageRepository;
import test_Inside.repositories.UserRepository;
import test_Inside.service.abstracts.MessageService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    private MessageService messageService;

    @BeforeEach
    void initUseCase(){
        messageService = new MessageServiceImpl(messageRepository, userRepository);
    }

    @Test
    void processMessage() {
        when(messageRepository.save(any(Message.class))).thenReturn(
                new Message(){{setText("test message");}});
        MessageResponseDTO messageResponseDTO = messageService.processMessage(
                new MessageRequestDTO(){{setMessage("test message");}});
        assertEquals(1, messageResponseDTO.getMessages().size());
        assertEquals("test message", messageResponseDTO.getMessages().get(0));
    }

    @Test
    void processMessageGetHistory() {
        List<Message> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setText("test message_" + i);
            list.add(message);
        }

        MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
        messageRequestDTO.setMessage("history 10");

        when(messageRepository.findFirst10ByOrderByPersistDateTimeDesc()).thenReturn(list);

        MessageResponseDTO messageResponseDTO = messageService.processMessage(
                new MessageRequestDTO(){{setMessage("history 10");}});

        assertEquals(10, messageResponseDTO.getMessages().size());
        assertTrue(messageResponseDTO.getMessages().contains("test message_0"));
        assertTrue(messageResponseDTO.getMessages().contains("test message_5"));
        assertTrue(messageResponseDTO.getMessages().contains("test message_9"));
    }
}