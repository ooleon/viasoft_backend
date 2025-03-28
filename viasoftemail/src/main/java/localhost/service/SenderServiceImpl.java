package localhost.service;

import localhost.dto.EmailAwsDTO;
import localhost.dto.EmailDTO;
import localhost.dto.EmailOciDTO;
import localhost.entity.EmailEntity;
import localhost.mapper.EmailMapper;
import localhost.messages.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

//@ConfigurationProperties(prefix = "oci")
//@Profile("oci")
@PropertySource("classpath:/application.properties")
@Service
public class SenderServiceImpl implements SenderService {


    @Value("${mail.integracao}")
    String integracao;

    @Autowired
    private EmailMapper emailMapper;

//    public SenderServiceImpl(EmailMapper emailMapper) {
//        this.emailMapper = emailMapper;
//    }

    @Override
    public void processEmail(EmailDTO emailDTO) {
        if ("AWS".equalsIgnoreCase(integracao)) {
            EmailAwsDTO awsDTO = emailMapper.toAwsDto(emailDTO);
            emailMapper.logObjectAsJson(awsDTO);
        } else if ("OCI".equalsIgnoreCase(integracao)) {
            EmailOciDTO ociDTO = emailMapper.toOciDto(emailDTO);
            emailMapper.logObjectAsJson(ociDTO);
        } else {
            throw new IllegalArgumentException(ValidationMessages.CONFIGURACAO_INTEGRACAO_INVALIDA);
//            throw new IllegalArgumentException("Configuração de integração inválida.");
        }
    }

    /*
    @Autowired
    public EmailServiceImpl(Environment env) {
        this.env = env;
    }
    */
    @Override
    public EmailEntity getEmailByName(String EmailName) {
        EmailEntity e = new EmailEntity();
        return e;
    }

    @Override
    public String integracao() {
        return integracao;
    }

    @Override
    public void leerPropiedades() {
//        String valor = env.getProperty("mail.integracao");
//        System.out.println("integracao: " + valor);
    }

}
