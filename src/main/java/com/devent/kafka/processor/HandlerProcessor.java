package com.devent.kafka.processor;

import com.devent.messaging.common.Message;
import com.devent.messaging.consumer.CommandMessage;
import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.To;
import org.apache.kafka.streams.processor.api.ContextualProcessor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.processor.internals.ForwardingDisabledProcessorContext;
import org.apache.kafka.streams.processor.internals.InternalProcessorContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @param <KIn>eventId 唯一事件Id
 * @param <VIn>event事件内容 JSON数据格式
 * @param <VOut>event result 执行结果
 */
public class HandlerProcessor<KIn, VIn, VOut> extends ContextualProcessor<KIn, VIn, KIn, VOut> {
    //运行的方法
    private  Function<VIn, VOut> function;

    //子节点名称
    private String childNodeName;

    private HandlerProcessor() {

    }

    private HandlerProcessor(Function<VIn, VOut> function, String childNodeName) {
        this.function = function;
        this.childNodeName = childNodeName;
    }

    // Builder类
    public static class Builder<KIn, VIn, VOut> {
        private  Function<VIn, VOut> function;
        private String childNodeName;

        public Builder() {
            // 初始化默认值
        }
        //设置运行方法
        public Builder<KIn, VIn, VOut> withFunction(Function<VIn, VOut> function) {
            this.function = function;
            return this;
        }

        // 设置childNodeName的方法
        public Builder<KIn, VIn, VOut> withChildNodeName(String childNodeName) {
            this.childNodeName = childNodeName;
            return this;
        }

        // 构建HandlerProcessor对象的方法
        public HandlerProcessor<KIn, VIn, VOut> build() {
            return new HandlerProcessor<>( function, childNodeName);
        }
    }

    //处理器，调用某个类中的方法
    @Override
    public void process(Record<KIn, VIn> record) {
        VOut returnResult = function.apply(record.value());
        context().forward(record.withValue(returnResult), childNodeName);
    }
}
