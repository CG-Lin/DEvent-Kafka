package com.devent.kafka.implementation;

import com.devent.comsumer.common.MessageConsumerImplementation;
import com.devent.messaging.consumer.MessageHandler;

import java.util.Set;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/21 14:22
 */
public class KafkaMessageConsumerImplementation implements MessageConsumerImplementation {
    @Override
    public void subscribe(String subscriberId, Set<String> channels, MessageHandler handler) {

    }
}
