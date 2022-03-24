package br.com.hbsis.pocakka.exemploabstractactor;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public void print (String msg){
        System.out.println(msg);
    }
}
