package localhost.messages;


public final class ValidationMessages {

    public static final String AWS = "AWS";
    public static final String OCI = "OCI";
    public static final String CORREIO_ENVIADO_SUCESSO = "Correio enviado com sucesso.";
    public static final String CONFIGURACAO_INTEGRACAO_INVALIDA = "Configuração de integração inválida.";
    public static final String O_EMAIL_DESTINATARIO_NAO_PODE_ESTAR_VAZIO = "O destinatário não pode estar vazio.";
    public static final String O_EMAIL_DESTINATARIO_DEVE_SER_VALIDO = "O destinatário deve ser um e-mail válido.";
    public static final String PESSOA_NAO_ENCONTRADA = "Pessoa não encontrada";
    public static final String NAO_TEM_DADOS = "Nao tem dados nenhum.";
    public static final String O_NOME_DO_DESTINATARIO_NAO_PODE_ESTAR_VAZIO = "O nome do destinatário não pode estar vazio";
    public static final String O_REMETENTE_NAO_PODE_ESTAR_VAZIO = "O remetente não pode estar vazio";
    public static final String O_EMIAL_REMETENTE_DEVE_SER_VALIDO = "O remetente deve ser um e-mail válido";
    public static final String O_ASSUNTO_NAO_PODE_ESTAR_VAZIO = "O assunto não pode estar vazio";
    public static final String O_CONTEUDO_NAO_PODE_ESTAR_VAZIO = "O conteúdo não pode estar vazio";

    public static final String NAO_PODE_ESTAR_VAZIO = "não pode estar vazio";
    public static final String NOME_DO_DESTINATARIO = "O nome do destinatário ";

    private ValidationMessages() {
    }

    public static final String m45(String m) {
        StringBuffer sb = new StringBuffer(" {" + m + "}");
        return sb.toString();
    }
}