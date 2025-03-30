package localhost.dto;

import static localhost.messages.ValidationMessages.*;

import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

@Data
@Setter
@Getter
public class EmailOciDTO implements EmailDTO {
    public EmailOciDTO() {
    }


    //E-mail destinatário: Max: 40 caracteres
    @NotBlank(message = O_EMAIL_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMAIL_DESTINATARIO_DEVE_SER_VALIDO)
    @Size(max = 40, message = "O destinatário não pode ter mais de 40 caracteres")
    private String recipientEmail;

    //Nome destinatário. Max: 50 caracteres
    @NotBlank(message = O_NOME_DO_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 50, message = "O nome do destinatário não pode ter mais de 50 caracteres")
    private String recipientName;

    //E-mail remetente. Max: 40 caracteres
    @NotBlank(message = O_REMETENTE_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMIAL_REMETENTE_DEVE_SER_VALIDO)
    @Size(max = 40, message = "O remetente não pode ter mais de 40 caracteres")
    private String senderEmail;

    //Assunto do e-mail. Max: 100 caracteres
    @NotBlank(message = O_ASSUNTO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 100, message = "O assunto não pode ter mais de 100 caracteres")
    private String subject;

    //Conteúdo do e-mail. Max: 250 caracteres
    @NotBlank(message = O_CONTEUDO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = MAXMESSAGEOCI, message = "O conteúdo não pode ter mais de " + MAXMESSAGEOCI + " caracteres")
    private String body;

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}