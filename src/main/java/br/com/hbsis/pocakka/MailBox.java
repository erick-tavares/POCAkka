package br.com.hbsis.pocakka;

import org.springframework.stereotype.Service;
import scala.Serializable;

@Service
public class MailBox {

    public static class PingMensagem {
        private String mensagem;

    public PingMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}

    public static class PongMensagem {
        private String mensagem;

        public PongMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    public static class OlaOk {
        private String mensagem;

        public OlaOk(String mensagem) {
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
