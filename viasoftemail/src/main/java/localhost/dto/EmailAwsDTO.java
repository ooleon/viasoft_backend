package localhost.dto;

import static localhost.messages.ValidationMessages.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Contract;
import org.springframework.lang.NonNull;


@Data
@Setter
@Getter
public class EmailAwsDTO implements EmailDTO {
    public EmailAwsDTO() {
    }

    //E-mail destinatário: Max: 45 caracteres
    @NotEmpty(message = O_EMAIL_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMAIL_DESTINATARIO_DEVE_SER_VALIDO)
    @Size(max = 45, message = "O destinatário não pode ter mais de 45 caracteres")
    private String recipient;

    //Nome destinatário. Max: 60 caracteres
    @NotEmpty(message = O_NOME_DO_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 60, message = "O nome do destinatário não pode ter mais de 60 caracteres")
    private String recipientName;

    //E-mail remetente. Max: 45 caracteres
    @NonNull
    @NotEmpty(message = O_REMETENTE_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMIAL_REMETENTE_DEVE_SER_VALIDO)
    @Size(max = 45, message = "O remetente não pode ter mais de 45 caracteres")
    private String sender;

    //Assunto do e-mail. Max: 120 caracteres
    @NotEmpty(message = O_ASSUNTO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 120, message = "O assunto não pode ter mais de 120 caracteres")
    private String subject;

    //Conteúdo do e-mail. Max: 256 caracteres
    @NotEmpty(message = O_CONTEUDO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 256, message = "O conteúdo não pode ter mais de 256 caracteres")
    private String content;

    public EmailAwsDTO(String recipient, String recipientName, String sender, String subject, String content) {
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
