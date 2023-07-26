package com.devent.command;

public class CommandHandlerBuilder<C>{

    private KafkaCommandHandlerBuilder kafkaCommandHandlerBuilder;

    private Class<C> classType;

    private C classFunction;

    public CommandHandlerBuilder(KafkaCommandHandlerBuilder kafkaCommandHandlerBuilder,Class<C> classType){
        this.kafkaCommandHandlerBuilder = kafkaCommandHandlerBuilder;
        this.classType = classType;
    }

    public Class<C> getClassType() {
        return classType;
    }

    public C getClassFunction() throws InstantiationException, IllegalAccessException {
        this.classFunction = classType.newInstance();
        return this.classFunction;
    }
}
