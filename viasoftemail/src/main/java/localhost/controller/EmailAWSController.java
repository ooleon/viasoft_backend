package localhost.controller;

import jakarta.validation.Valid;
import localhost.config.EmailDTOConfig;
import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailDTO;
import localhost.dto.EmailGeneralDTO;
import localhost.mapper.EmailMapper;
import localhost.service.SenderService;
import localhost.messages.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/email")
@ConditionalOnProperty(name = "mail.integracao", havingValue = "AWS", matchIfMissing = true)
public class EmailAWSController {

//    @Autowired
//    EmailDTO emailDTO;
//    @Autowired
//    private EmailDTOConfig emailDTOConfig;

//    @Autowired
//    private EmailMapper em;


    @Autowired
    private EmailDTOConfig emailDTOConfig;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enviarEmail(@RequestBody String emailJson) {
        EmailDTO emailDTO = emailDTOConfig.mapToEmailDTO(emailJson);
        System.out.println("Objeto recebido: " + emailDTO);

        System.out.println(ValidationMessages.CORREIO_ENVIADO_SUCESSO);

    }

    /*
    // Manejo de errores personalizados para validaciones
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
    */

    /*
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailGeneralDTO emailDTO) {
        mappeador(emailDTO);
        System.out.println("Objeto recebido: " + emailDTO);
        return ResponseEntity.ok(ValidationMessages.CORREIO_ENVIADO_SUCESSO);
    }

    private void mappeador(@Valid EmailGeneralDTO emailDTO) {

        EmailAwsDTO emailAwsDTO = EmailMapper.generalToAwsDto(emailDTO);

    }
    */


    /*
     Ok 200
     @PostMapping("/send") public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailAwsDTO emailDTO) {
     System.out.println("Objeto recebido: " + emailDTO);
     return ResponseEntity.ok(ValidationMessages.CORREIO_ENVIADO_SUCESSO);
     }
     */


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
