package localhost.controller;

import jakarta.validation.Valid;
import localhost.dto.EmailOciDTO;

import static localhost.messages.ValidationMessages.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
@ConditionalOnProperty(name = "mail.integracao", havingValue = OCI, matchIfMissing = true)
public class EmailOCIController {

    @Value("${mail.integracao}")
    String integracao;

//    @Autowired
//    EmailDTO emailDTO;
//    @Autowired
//    private EmailDTOConfig emailDTOConfig;

//    @Autowired
//    private SenderService senderService;

//    public EmailController(SenderService senderService) {
//        this.senderService = senderService;
//    }


    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailOciDTO emailDTO) {
        System.out.println("mail.integracao = " + integracao);
        System.out.println("Objeto recebido: " + emailDTO);
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
