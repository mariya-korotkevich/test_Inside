package test_Inside.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test_Inside.models.entity.Message;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponseDTO {
    private List<String> messages;

    public MessageResponseDTO(List<Message> messagesList) {
        this.messages = messagesList.stream()
                .map(Message::getText)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO(Message message) {
        messages = List.of(message.getText());
    }
}
