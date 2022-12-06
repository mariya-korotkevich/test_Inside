package test_Inside.service.abstracts;

import test_Inside.models.dto.MessageRequestDTO;
import test_Inside.models.dto.MessageResponseDTO;

public interface MessageService {
    MessageResponseDTO processMessage(MessageRequestDTO messageDto);
}
