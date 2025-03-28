package localhost.mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailGeneralDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
class EmailMapperTest {
    private final Logger log = LoggerFactory.getLogger(EmailMapperTest.class);

    static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void generalToAwsDto() {

        EmailGeneralDTO emailDTO = new EmailGeneralDTO();
        emailDTO.setRecipient("john.doe@example.com");
        emailDTO.setRecipientName("John Doe");
//        emailDTO.setSender("sender@empresa.com");
        emailDTO.setSubject("Assunto Teste");
        emailDTO.setContent("Este é um e-mail de teste.");
        EmailAwsDTO emailAwsDTO = EmailMapper.generalToAwsDto(emailDTO);
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(EmailMapper.generalToAwsDto(emailDTO));

        System.out.println(emailAwsDTO);
    }

    //    @Test
    void toAwsDto() {
    }

    //    @Test
    void toOciDto() {
    }

    //    @Test
    void logObjectAsJson() {
    }
}