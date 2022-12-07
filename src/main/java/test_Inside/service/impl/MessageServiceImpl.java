package test_Inside.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test_Inside.models.dto.MessageRequestDTO;
import test_Inside.models.dto.MessageResponseDTO;
import test_Inside.models.entity.Message;
import test_Inside.models.entity.User;
import test_Inside.repositories.MessageRepository;
import test_Inside.repositories.UserRepository;
import test_Inside.service.abstracts.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private static final String HISTORY_10 = "history 10";

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public MessageResponseDTO processMessage(MessageRequestDTO messageDto) {
        if (HISTORY_10.equals(messageDto.getMessage())){
            return new MessageResponseDTO(messageRepository.findFirst10ByOrderByPersistDateTimeDesc());
        }
        return new MessageResponseDTO(save(messageDto));
    }

    @Override
    @Transactional
    public Message save(MessageRequestDTO messageRequestDTO) {
        Message message = new Message();
        message.setText(messageRequestDTO.getMessage());
        message.setUser(userRepository.findByName(messageRequestDTO.getName())
                .orElseGet(() -> {
                    User user = new User();
                    user.setName(messageRequestDTO.getName());
                    return user;
                }));
        return messageRepository.save(message);
    }
}
