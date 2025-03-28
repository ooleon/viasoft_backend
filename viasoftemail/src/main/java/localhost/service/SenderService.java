package localhost.service;

import localhost.dto.EmailDTO;
import localhost.entity.EmailEntity;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

//@ConfigurationProperties(prefix = "oci")
//@Profile("oci")
@PropertySource("classpath:application-oci.properties")  // Archivo personalizado
@Service
public interface SenderService {

    public EmailEntity getEmailByName(String EmailName);

    public String integracao();

    public void leerPropiedades();

    public void processEmail(EmailDTO emailDTO);

}
