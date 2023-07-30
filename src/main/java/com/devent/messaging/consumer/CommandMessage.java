package com.devent.messaging.consumer;

import com.devent.messaging.common.Message;

import java.util.Map;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/30 16:32
 */
public class CommandMessage <T>{

    //消息Id
    private String messageId;

    //
    private T command;

    //头信息
    private Map<String,String> headInfo;


    private Message message;

    public CommandMessage(String messageId, T command, Map<String, String> headInfo, Message message) {
        this.messageId = messageId;
        this.command = command;
        this.headInfo = headInfo;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public T getCommand() {
        return command;
    }

    public Map<String, String> getHeadInfo() {
        return headInfo;
    }
}
