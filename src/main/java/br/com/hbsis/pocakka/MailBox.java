package br.com.hbsis.pocakka;

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
}
