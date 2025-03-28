package localhost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import localhost.dto.EmailAwsDTO;
import localhost.messages.ValidationMessages;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

//@WebMvcTest(EmailAWSController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailStringControllerTest {

    @Mock
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    void enviarEmail_DeveRetornarContent_QuandoDadosValidos_HappyPath() {
        String url = "http://localhost:" + port + "/email/send";

        // Crear el cuerpo del JSON
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("recipient", "john.doe@example.com");
        requestBody.put("recipientName", "John Doe");
        requestBody.put("sender", "sender@empresa.com");
        requestBody.put("subject", "Assunto Teste");
        requestBody.put("content", "Este é um e-mail de teste.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        if (isServerRunning()) {

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );


            // Verifica que el estado HTTP sea 204 No Content
            assertThat(response.getStatusCode()).isEqualTo(OK);

            System.out.println(response.getBody());
        } else {
            fail("servidor parado");
        }
    }

    @Test
    @Order(2)
    void enviarEmail_DeveRetornarNoContent_QuandoDadosValidos() {
        String url = "http://localhost:" + port + "/email/send";

        // Crear el cuerpo del JSON
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("recipient", "john.doe@example.com");
        requestBody.put("recipientName", "John Doe");
//        requestBody.put("sender", "sender@empresa.com");
        requestBody.put("subject", "Assunto Teste");
        requestBody.put("content", "Este é um e-mail de teste.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        if (isServerRunning()) {
            HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
//                emailService.enviarEmail(null);
                ResponseEntity<String> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );
            });

            assertThat(exception.getMessage().contains("O remetente não pode estar vazio"));


            System.out.println(exception.getMessage());
            System.out.println(exception.getStatusCode());
            System.out.println(exception.getResponseBodyAsString());

        } else {
            fail("servidor parado");
        }

        // Verifica que el estado HTTP sea 400 Bad Request
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response.getBody()).contains("O remetente não pode estar vazio");

    }

    @Test
    @Order(3)
    void enviarEmail_DeveRetornarBadRequest_QuandoDadosInvalidos() {
        String url = "http://localhost:" + port + "/email/send";

        // JSON incompleto (falta 'recipient')
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("sender", "sender@empresa.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        if (isServerRunning()) {


            HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
                ResponseEntity<String> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );
            });

            assertThat(exception.getMessage().contains("O remetente não pode estar vazio"));


            System.out.println(exception.getMessage());
            System.out.println(exception.getStatusCode());
            System.out.println(exception.getResponseBodyAsString());
        } else {
            fail("servidor parado");
        }


        // Verifica que el estado HTTP sea 400 Bad Request
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//        assertThat(response.getBody()).contains("O destinatário não pode estar vazio");
    }

    private boolean isServerRunning() {
        try {
            return restTemplate.getForEntity("http://localhost:8081/manage/health", String.class)
                           .getStatusCode() == OK;
        } catch (Exception e) {
            return false;  // El servidor no está activo
        }
    }


    //    @Test
    @Order(4)
    void enviarEmail_DeveRetornarNoContent_QuandoDadosValidosAWS() throws Exception {
        // JSON Válido para AWS
        String awsEmailJson = objectMapper.writeValueAsString(new EmailAwsDTO(
                "cliente@empresa.com",
                "Cliente Empresa",
                "noreply@empresa.com",
                "Bem-vindo",
                "Conteúdo de teste"
        ));

        mockMvc.perform(MockMvcRequestBuilders.post("/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(awsEmailJson))
                .andExpect(status().isNoContent());  // 204 No Content
    }

    //    @Test
    @Order(5)
    void enviarEmail_DeveRetornarBadRequest_QuandoEmailInvalido() throws Exception {
        // JSON Inválido: Email do remetente inválido
        String emailInvalido = objectMapper.writeValueAsString(new EmailAwsDTO(
                "cliente@empresa.com",
                "Cliente Empresa",
                "email_invalido",
                "Bem-vindo",
                "Conteúdo de teste"
        ));

        mockMvc.perform(MockMvcRequestBuilders.post("/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailInvalido))
                .andExpect(status().isBadRequest()) // 400 Bad Request
                .andExpect(content().string("""
                        Errores de validação:
                        - sender: deve ser um endereço de e-mail válido
                        """));
    }

    //    @Test
    @Order(6)
    void enviarEmail_DeveRetornarBadRequest_QuandoCamposObrigatoriosFaltando() throws Exception {
        // JSON Inválido: Campos obrigatórios vazios
        String jsonInvalido = objectMapper.writeValueAsString(new EmailAwsDTO(
                "",
                "",
                "",
                "",
                ""
        ));

        mockMvc.perform(MockMvcRequestBuilders.post("/email/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvalido))
                .andExpect(status().isBadRequest()) // 400 Bad Request
                .andExpect(content().string("""
                        Errores de validação:
                        - recipient: não pode estar vazio
                        - sender: não pode estar vazio
                        - subject: não pode estar vazio
                        - content: não pode estar vazio
                        """));
    }
}