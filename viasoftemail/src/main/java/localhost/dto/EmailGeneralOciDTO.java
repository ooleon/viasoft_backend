package localhost.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import static localhost.messages.ValidationMessages.*;

@Data
@Setter
@Getter
public class EmailGeneralOciDTO implements EmailDTO {
    public EmailGeneralOciDTO() {
    }


    //E-mail destinatário: Max: 40 caracteres
    @NotBlank(message = O_EMAIL_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMAIL_DESTINATARIO_DEVE_SER_VALIDO)
    @Size(max = 40, message = "O destinatário não pode ter mais de 40 caracteres")
    private String emailDestinatario;

    //Nome destinatário. Max: 50 caracteres
    @NotBlank(message = O_NOME_DO_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 50, message = "O nome do destinatário não pode ter mais de 50 caracteres")
    private String nomeDestinatario;

    //E-mail remetente. Max: 40 caracteres
    @NotBlank(message = O_REMETENTE_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMIAL_REMETENTE_DEVE_SER_VALIDO)
    @Size(max = 40, message = "O remetente não pode ter mais de 40 caracteres")
    private String emailRemetente;

    //Assunto do e-mail. Max: 100 caracteres
    @NotBlank(message = O_ASSUNTO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 100, message = "O assunto não pode ter mais de 100 caracteres")
    private String asunto;

    //Conteúdo do e-mail. Max: 250 caracteres
    @NotBlank(message = O_CONTEUDO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = MAXMESSAGEOCI, message = "O conteúdo não pode ter mais de " + MAXMESSAGEOCI + " caracteres")
    private String conteudo;

    public EmailGeneralOciDTO(String emailDestinatario, String nomeDestinatario, String emailRemetente, String asunto, String conteudo) {
        this.emailDestinatario = emailDestinatario;
        this.nomeDestinatario = nomeDestinatario;
        this.emailRemetente = emailRemetente;
        this.asunto = asunto;
        this.conteudo = conteudo;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(String emailRemetente) {
        this.emailRemetente = emailRemetente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}