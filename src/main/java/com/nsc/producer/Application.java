package com.nsc.producer;

import com.nsc.producer.sender.IcuEventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private IcuEventSender icuEventSender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public void generateMessage() {
        System.out.println("Post construct called");
        icuEventSender.send();
    }
}
