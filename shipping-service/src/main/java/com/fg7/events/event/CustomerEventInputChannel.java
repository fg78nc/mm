package com.fg7.events.event;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomerEventInputChannel {

    String INPUT = "customerInEventChannel";

    @Input(CustomerEventInputChannel.INPUT)
    SubscribableChannel customerInChannel();
}
