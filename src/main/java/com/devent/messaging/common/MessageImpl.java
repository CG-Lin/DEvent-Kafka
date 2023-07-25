package com.devent.messaging.common;

import com.devent.messaging.consumer.MessageHandler;

import java.util.Map;
import java.util.Optional;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/21 14:28
 */
public class MessageImpl implements Message {
    private String payload;
    private Map<String, String> headers;

    public MessageImpl() {
    }

    public MessageImpl(String payload, Map<String, String> headers) {
        this.payload = payload;
        this.headers = headers;
    }

    @Override
    public String getId() {
        return getRequiredHeader(Message.ID);
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }


    public String getPayload() {
        return payload;
    }

    @Override
    public Optional<String> getHeader(String name) {
        return Optional.ofNullable(headers.get(name));
    }

    @Override
    public String getRequiredHeader(String name) {
        String s = headers.get(name);
        if (s == null)
            throw new RuntimeException("No such header: " + name + " in this message " + this);
        else
            return s;
    }
}
