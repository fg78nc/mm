package com.fg7.events.event;

import com.fg7.utils.context.ContextCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Source.class)
public class CustomerChangedSourceBean {

    private final MessageChannel channel;

    public CustomerChangedSourceBean(Source source) {
        this.channel = source.output();
    }

    public void publishCustomerChangedEvent(String eventType, Long customerId){
        log.info("Publishing message for event: {} customerId {}", eventType, customerId);
        CustomerChangedEvent customerChangedEvent
                = new CustomerChangedEvent(CustomerChangedEvent.class.getTypeName(), eventType, customerId, ContextCache.getTokenID());
        Message<CustomerChangedEvent> message =
                MessageBuilder
                        .withPayload(customerChangedEvent)
                        .setHeader("content_type", "application/json")
                        .build();
        this.channel.send(message);
    }
}
