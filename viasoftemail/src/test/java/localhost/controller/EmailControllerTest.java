package localhost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import localhost.dto.EmailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(EmailAWSController.class) // Carga solo el contexto del controlador
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // Para convertir objetos Java a JSON

    @Test
    void enviarEmail_DeveRetornarNoContent_QuandoDadosValidos() throws Exception {
        /*
        var emailDTO = new EmailDTO();
        emailDTO.setRecipient("john.doe@example.com");
        emailDTO.setRecipientName("John Doe");
        emailDTO.setSender("sender@empresa.com");
        emailDTO.setSubject("Assunto Teste");
        emailDTO.setContent("Este é um e-mail de teste.");

        mockMvc.perform(post("/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isNoContent()); // Espera 204 No Content
    */
    }

    @Test
    void enviarEmail_DeveRetornarBadRequest_QuandoDadosInvalidos() throws Exception {
        /*
        var emailDTO = new EmailDTO(); // Faltando campos obrigatórios

        mockMvc.perform(post("/api/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isBadRequest())  // Espera 400 Bad Request
                .andExpect(content().string("{\"erro\":\"O destinatário é obrigatório\"}"));
                */
    }

    @Test
    void enviarEmail_DeveRetornarInternalServerError_QuandoErroInterno() throws Exception {
        /*
        var emailDTO = new EmailDTO();
        emailDTO.setRecipient("john.doe@example.com");
        emailDTO.setRecipientName("John Doe")         ;
        emailDTO.setSender("sender@empresa.com");
        emailDTO.setSubject("Assunto Teste");
        emailDTO.setContent("Este é um e-mail de teste.");

        mockMvc.perform(post("/api/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isInternalServerError()); // Espera 500 Internal Server Error

         */
    }
}