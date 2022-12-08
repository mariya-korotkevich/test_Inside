package test_Inside.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import test_Inside.models.dto.MessageRequestDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @WithMockUser(value = "spring")
    @Test
    public void testReturn200() throws Exception {

        MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
        messageRequestDTO.setName("test user");
        messageRequestDTO.setMessage("test message");

        mockMvc.perform(post("/message")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(messageRequestDTO)))
                .andExpect(status().isOk());
    }
}