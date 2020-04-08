package br.com.hbsis.pocakka;

import org.springframework.stereotype.Service;
import scala.Serializable;

@Service
public class MailBox {

    public static class PingMensagem implements Serializable{
        private String mensagem;

    public PingMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

    public static class PongMensagem implements Serializable{
        private String mensagem;

        public PongMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    public static class FilhoMensagem {
        private String mensagem;

        public FilhoMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    public static class Error {
        private NullPointerException mensagem;

        public Error(NullPointerException mensagem) {
            this.mensagem = mensagem;
        }

        public NullPointerException getMensagem() {
            return mensagem;
        }
    }
}
