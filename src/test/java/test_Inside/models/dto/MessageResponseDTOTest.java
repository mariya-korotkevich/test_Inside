package test_Inside.models.dto;

import org.junit.jupiter.api.Test;
import test_Inside.models.entity.Message;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseDTOTest {

    @Test
    void toDtoWithOneMessage() {
        Message message = new Message();
        message.setText("test text");
        MessageResponseDTO messageResponseDTO = MessageResponseDTO.toDto(message);
        assertEquals(1, messageResponseDTO.getMessages().size());
        assertEquals("test text", messageResponseDTO.getMessages().get(0));
    }

    @Test
    void toDtoWithListOfMessage() {
        Message message1 = new Message();
        message1.setText("1 test text");

        Message message2 = new Message();
        message2.setText("2 test text");

        Message message3 = new Message();
        message3.setText("3 test text");

        MessageResponseDTO messageResponseDTO = MessageResponseDTO.toDto(
                List.of(message1, message2, message3));
        assertEquals(3, messageResponseDTO.getMessages().size());
        assertTrue(messageResponseDTO.getMessages().contains(message1.getText()));
        assertTrue(messageResponseDTO.getMessages().contains(message2.getText()));
        assertTrue(messageResponseDTO.getMessages().contains(message3.getText()));
    }
}