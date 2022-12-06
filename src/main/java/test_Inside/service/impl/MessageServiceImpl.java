package test_Inside.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_Inside.models.dto.MessageRequestDTO;
import test_Inside.models.dto.MessageResponseDTO;
import test_Inside.models.entity.Message;
import test_Inside.repositories.MessageRepository;
import test_Inside.service.abstracts.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String HISTORY_10 = "history 10";

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public MessageResponseDTO processMessage(MessageRequestDTO messageDto) {
        if (HISTORY_10.equals(messageDto.getMessage())){
            return new MessageResponseDTO(messageRepository.findFirst10ByOrderByPersistDateTimeDesc());
        }
        Message message = new Message();
        message.setText(messageDto.getMessage());
        return new MessageResponseDTO(messageRepository.save(message));
    }
}
