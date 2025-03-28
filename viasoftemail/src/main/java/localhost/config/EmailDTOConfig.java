package localhost.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.*;
import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailDTO;
import localhost.dto.EmailOciDTO;

import static localhost.messages.ValidationMessages.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class EmailDTOConfig {

    @Value("${mail.integracao}")
    private String mailIntegracao;


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public EmailDTO mapToEmailDTO(String json) {
        try {
            EmailDTO emailDTO;

            if (AWS.equalsIgnoreCase(mailIntegracao)) {
                emailDTO = objectMapper.readValue(json, EmailAwsDTO.class);
            } else if (OCI.equalsIgnoreCase(mailIntegracao)) {
                EmailAwsDTO awsDTO = objectMapper.readValue(json, EmailAwsDTO.class);

                EmailOciDTO ociDTO = new EmailOciDTO();
                ociDTO.setRecipientEmail(awsDTO.getRecipient());
                ociDTO.setRecipientName(awsDTO.getRecipientName());
                ociDTO.setSenderEmail(awsDTO.getSender());
                ociDTO.setSubject(awsDTO.getSubject());
                ociDTO.setBody(awsDTO.getContent());

                emailDTO = ociDTO;
            } else {
                throw new IllegalArgumentException(CONFIGURACAO_INTEGRACAO_INVALIDA);
            }

            // ✅ Validar el objeto después de mapearlo
            validateEmailDTO(emailDTO);

            return emailDTO;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao mapear o JSON para a classe DTO", e);
        }
    }

    private void validateEmailDTO(EmailDTO emailDTO) {
        Set<ConstraintViolation<EmailDTO>> violations = validator.validate(emailDTO);

        if (!violations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder("Errores de validação:\n");
            for (ConstraintViolation<EmailDTO> violation : violations) {
                errorMessages.append("- ").append(violation.getPropertyPath()).append(": ")
                        .append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(errorMessages.toString());
        }
    }




    /*
    public EmailDTOConfig() {
        // Se crea el validador de forma manual
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

     */


    /*
    @Bean
    public EmailDTO mapToEmailDTO(String json) {
        try {
            if ("AWS".equalsIgnoreCase(mailIntegracao)) {
                return objectMapper.readValue(json, EmailAwsDTO.class);
            } else if ("OCI".equalsIgnoreCase(mailIntegracao)) {
                // Mapeo manual de campos debido a diferencias en nombres
                EmailAwsDTO awsDTO = objectMapper.readValue(json, EmailAwsDTO.class);

                EmailOciDTO ociDTO = new EmailOciDTO();
                ociDTO.setRecipientEmail(awsDTO.getRecipient());
                ociDTO.setRecipientName(awsDTO.getRecipientName());
                ociDTO.setSenderEmail(awsDTO.getSender());
                ociDTO.setSubject(awsDTO.getSubject());
                ociDTO.setBody(awsDTO.getContent());

                return ociDTO;
            } else {
                throw new IllegalArgumentException("Configuração inválida para 'mail.integracao'. Use 'AWS' ou 'OCI'.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao mapear o JSON para a classe DTO", e);
        }
    }

    @Bean
    public EmailDTO emailDTO() {
        if ("AWS".equalsIgnoreCase(mailIntegracao)) {
            return new EmailAwsDTO();
        } else if ("OCI".equalsIgnoreCase(mailIntegracao)) {
            return new EmailOciDTO();
        } else {
            throw new IllegalArgumentException(ValidationMessages.CONFIGURACAO_INTEGRACAO_AWS_OCI);
        }
    }

     */

}

