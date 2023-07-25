package com.devent.comsumer.common;

import com.devent.messaging.consumer.MessageHandler;

import java.util.Set;

public interface MessageConsumerImplementation {
    void subscribe(String subscriberId, Set<String> channels, MessageHandler handler);
}
