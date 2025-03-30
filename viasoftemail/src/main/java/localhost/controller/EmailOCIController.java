package localhost.controller;

import jakarta.validation.Valid;
import localhost.config.EmailDTOConfig;
import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailDTO;
import localhost.dto.EmailGeneralOciDTO;
import localhost.dto.EmailOciDTO;

import static localhost.messages.ValidationMessages.*;

import localhost.mapper.EmailMapper;
import localhost.messages.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/email")
@ConditionalOnProperty(name = "mail.integracao", havingValue = OCI, matchIfMissing = true)
public class EmailOCIController {

    @Value("${mail.integracao}")
    String integracao;

//    @Autowired
//    EmailDTO emailDTO;
@Autowired
private EmailDTOConfig emailDTOConfig;

//    @Autowired
//    private SenderService senderService;

//    public EmailController(SenderService senderService) {
//        this.senderService = senderService;
//    }


    @PostMapping("/send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enviarEmail(@RequestBody String emailJson) {
        EmailDTO emailDTO = emailDTOConfig.mapToEmailDTO(emailJson);
        System.out.println("Objeto recebido: " + emailDTO);

        System.out.println(ValidationMessages.CORREIO_ENVIADO_SUCESSO);

    }


    @PostMapping("/sendgeneral")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailGeneralOciDTO emailDTO) {
        System.out.println("mail.integracao = " + integracao);
        System.out.println("Objeto recebido: " + emailDTO);
        EmailOciDTO emailOciDTO = EmailMapper.generalToOciDto(emailDTO);
        System.out.println("Objeto convertido: \n" + emailOciDTO);
        return ResponseEntity.ok(CORREIO_ENVIADO_SUCESSO);
    }


    /*
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enviarEmail(@RequestBody String emailJson) {
        System.out.println("Objeto recebido: " + emailJson);
        EmailDTO emailDTO = emailDTOConfig.mapToEmailDTO(emailJson);
    }

    /*
    //    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
        System.out.println("Objeto recebido: " + emailDTO);
        return ResponseEntity.ok(ValidationMessages.CORREIO_ENVIADO_SUCESSO);
    }

     */
}
