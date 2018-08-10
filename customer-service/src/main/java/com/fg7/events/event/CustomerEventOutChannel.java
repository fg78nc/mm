package com.fg7.events.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerEventOutChannel {

    String OUTPUT = "customerOutChannel";

    @Output(CustomerEventOutChannel.OUTPUT)
    MessageChannel customerOutChannel();

}
