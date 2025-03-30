package localhost.mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailGeneralAwsDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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

        EmailGeneralAwsDTO emailDTO = new EmailGeneralAwsDTO();
        emailDTO.setEmailDestinatario("john.doe@example.com");
        emailDTO.setNomeDestinatario("John Doe");
//        emailDTO.setEmailRemetente("sender@empresa.com");
        emailDTO.setAsunto("Assunto Teste");
        emailDTO.setConteudo("Este é um e-mail de teste.");
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