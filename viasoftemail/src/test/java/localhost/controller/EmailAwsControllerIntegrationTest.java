package localhost.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

//@SpringBootTest
//@IfProfileValue(name = "server.running", value = "true")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmailAwsControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(3)
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
            assertThat(response.getStatusCode()).isEqualTo(NO_CONTENT);

            System.out.println(response.getBody());
        } else {
            fail("servidor parado");
        }
    }

    @Test
    @Order(1)
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
    @Order(2)
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
}
