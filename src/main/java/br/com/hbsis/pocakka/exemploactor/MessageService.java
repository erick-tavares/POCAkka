package br.com.hbsis.pocakka.exemploactor;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public void print (String msg){
        System.out.println(msg);
    }
}
