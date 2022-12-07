package test_Inside.service.abstracts;

import test_Inside.models.dto.MessageRequestDTO;
import test_Inside.models.dto.MessageResponseDTO;
import test_Inside.models.entity.Message;

public interface MessageService {
    MessageResponseDTO processMessage(MessageRequestDTO messageDto);
    Message save(MessageRequestDTO messageRequestDTO);
}
