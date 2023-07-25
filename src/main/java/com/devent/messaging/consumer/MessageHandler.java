package com.devent.messaging.consumer;

import com.devent.messaging.common.Message;

import java.util.function.Consumer;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/21 14:24
 */
public interface MessageHandler extends Consumer<Message> {
}
