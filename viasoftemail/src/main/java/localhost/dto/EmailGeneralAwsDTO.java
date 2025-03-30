package localhost.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import static localhost.messages.ValidationMessages.*;


@Data
@Setter
@Getter
public class EmailGeneralAwsDTO implements EmailDTO {
    public EmailGeneralAwsDTO() {
    }

    //E-mail destinatário: Max: 45 caracteres
    @NonNull
    @NotEmpty(message = O_EMAIL_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMAIL_DESTINATARIO_DEVE_SER_VALIDO)
    @Size(max = 45, message = "O destinatário não pode ter mais de 45 caracteres")
    private String emailDestinatario;

    //Nome destinatário. Max: 60 caracteres
    @NonNull
    @NotEmpty(message = O_NOME_DO_DESTINATARIO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 60, message = "O nome do destinatário não pode ter mais de 60 caracteres")
    private String nomeDestinatario;

    //E-mail remetente. Max: 45 caracteres
    @NonNull
    @NotEmpty(message = O_REMETENTE_NAO_PODE_ESTAR_VAZIO)
    @Email(message = O_EMIAL_REMETENTE_DEVE_SER_VALIDO)
    @Size(max = 45, message = "O remetente não pode ter mais de 45 caracteres")
    private String emailRemetente;

    //Assunto do e-mail. Max: 120 caracteres
    @NonNull
    @NotEmpty(message = O_ASSUNTO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 120, message = "O assunto não pode ter mais de 120 caracteres")
    private String asunto;

    //Conteúdo do e-mail. Max: 256 caracteres
    @NonNull
    @NotEmpty(message = O_CONTEUDO_NAO_PODE_ESTAR_VAZIO)
    @Size(max = 256, message = "O conteúdo não pode ter mais de 256 caracteres")
    private String conteudo;

    public EmailGeneralAwsDTO(@NonNull String emailDestinatario, @NonNull String nomeDestinatario, @NonNull String emailRemetente, @NonNull String asunto, @NonNull String conteudo) {
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

    @NonNull
    public String getEmailRemetente() {
        return emailRemetente;
    }

    public void setEmailRemetente(@NonNull String emailRemetente) {
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
