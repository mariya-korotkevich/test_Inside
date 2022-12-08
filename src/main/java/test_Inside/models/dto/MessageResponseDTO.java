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
    public static MessageResponseDTO toDto(Message message){
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessages(List.of(message.getText()));
        return messageResponseDTO;
    }

    public static MessageResponseDTO toDto(List<Message> messages){
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        messageResponseDTO.setMessages(messages.stream()
                .map(Message::getText).collect(Collectors.toList()));
        return messageResponseDTO;
    }
}
