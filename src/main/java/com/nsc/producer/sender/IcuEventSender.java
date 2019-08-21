package com.nsc.producer.sender;

import com.nsc.producer.source.IcuEventSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@EnableBinding(IcuEventSource.class)
public class IcuEventSender {

    @Autowired
    private IcuEventSource icuEventSource;

    public void send() {
        Runnable runnable = () -> {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
            Message<String> message = MessageBuilder
                    .withPayload(timeStamp)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, timeStamp.getBytes())
                    .build();
            icuEventSource.output().send(message);
        };

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}
