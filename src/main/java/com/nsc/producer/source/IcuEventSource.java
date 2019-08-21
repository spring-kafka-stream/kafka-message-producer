package com.nsc.producer.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface IcuEventSource {
    String OUTPUT = "icuEventOut";

    @Output(OUTPUT)
    MessageChannel output();
}
