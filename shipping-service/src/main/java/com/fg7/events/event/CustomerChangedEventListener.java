package com.fg7.events.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(CustomerEventInputChannel.class)
public class CustomerChangedEventListener {

    @StreamListener(CustomerEventInputChannel.INPUT)
    public void customerChangedEventListener(Message<CustomerChangedEvent> message){
        log.info("Received event {} of type {} ", message.getPayload(), message.getHeaders().get("content_type") );
    }
}
