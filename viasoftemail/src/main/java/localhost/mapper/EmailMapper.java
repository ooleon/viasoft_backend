package localhost.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailDTO;
import localhost.dto.EmailGeneralDTO;
import localhost.dto.EmailOciDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Contract;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

//    @Autowired
//    EmailDTO emailDTO;

    public static EmailAwsDTO generalToAwsDto(EmailGeneralDTO dto) {
        EmailAwsDTO awsDTO;
        awsDTO = new EmailAwsDTO();

        awsDTO.setRecipient(dto.getRecipient());
        awsDTO.setRecipientName(dto.getRecipientName());
        awsDTO.setSender(dto.getSender());
        awsDTO.setSubject(dto.getSubject());
        awsDTO.setContent(dto.getContent());

        return awsDTO;
    }


    public static EmailAwsDTO toAwsDto(EmailDTO dto) {
        EmailAwsDTO awsDTO = new EmailAwsDTO();

        /*
        awsDTO.setRecipient(dto.getRecipient());
        awsDTO.setRecipientName(dto.getRecipientName());
        awsDTO.setSender(dto.getSender());
        awsDTO.setSubject(dto.getSubject());
        awsDTO.setContent(dto.getContent());
        */
        return awsDTO;
    }

    public static EmailOciDTO toOciDto(EmailDTO dto) {
        EmailOciDTO ociDTO = new EmailOciDTO();
        /*
        ociDTO.setRecipientEmail(dto.getRecipient());
        ociDTO.setRecipientName(dto.getRecipientName());
        ociDTO.setSenderEmail(dto.getSender());
        ociDTO.setSubject(dto.getSubject());
        ociDTO.setBody(dto.getContent());
        */
        return ociDTO;
    }

    public void logObjectAsJson(Object obj) {
        try {
            String json = new ObjectMapper().writeValueAsString(obj);
//            System.out.println(json);
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao serializar objeto: " + e.getMessage());
        }
    }
}
