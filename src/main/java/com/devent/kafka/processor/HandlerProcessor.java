package com.devent.kafka.processor;

import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.To;
import org.apache.kafka.streams.processor.api.ContextualProcessor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.processor.internals.ForwardingDisabledProcessorContext;
import org.apache.kafka.streams.processor.internals.InternalProcessorContext;

import java.util.Objects;
import java.util.function.Function;

public class HandlerProcessor<KIn, VIn, VOut> extends ContextualProcessor<KIn, VIn, KIn, VOut> {
    //运行的方法
    private  Function<VIn, VOut> inputFunction;

    //运行传参
    private VIn inputArgument;

    //子节点名称
    private String childNodeName;


    private HandlerProcessor(Function<VIn, VOut> inputFunction, VIn inputArgument,String childNodeName){
        this.inputFunction = inputFunction;
        this.inputArgument = inputArgument;
        this.childNodeName = childNodeName;
    }

    // Builder类
    public static class Builder<KIn, VIn, VOut> {
        private Function<VIn, VOut> inputFunction;
        private VIn inputArgument;
        private String childNodeName;

        // 设置inputFunction的方法
        public Builder<KIn, VIn, VOut> setInputFunction(Function<VIn, VOut> inputFunction) {
            this.inputFunction = inputFunction;
            return this;
        }

        // 设置inputArgument的方法
        public Builder<KIn, VIn, VOut> setInputArgument(VIn inputArgument) {
            this.inputArgument = inputArgument;
            return this;
        }

        // 设置childNodeName的方法
        public Builder<KIn, VIn, VOut> setChildNodeName(String childNodeName) {
            this.childNodeName = childNodeName;
            return this;
        }

        // 构建HandlerProcessor对象的方法
        public HandlerProcessor<KIn, VIn, VOut> build() {
            return new HandlerProcessor<>(inputFunction, inputArgument, childNodeName);
        }
    }

    //处理器，调用某个类中的方法
    @Override
    public void process(Record<KIn, VIn> record) {
        VOut returnResult = inputFunction.apply(inputArgument);
        context().forward(record.withValue(returnResult), childNodeName);
    }
}
