package br.com.hbsis.pocakka;

import scala.Serializable;

public class MailBox implements Serializable {

    public static class PingMensagem implements Serializable {
        private String mensagem;

        public PingMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

    public static class PongMensagem implements Serializable {
        private String mensagem;

        public PongMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }
}
