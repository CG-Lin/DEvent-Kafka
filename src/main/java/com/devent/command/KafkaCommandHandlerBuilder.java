package com.devent.command;

public class KafkaCommandHandlerBuilder implements AbstractCommandHandlerBuilder {

    @Override
    public <C> CommandHandlerBuilder<C> getCommandClassType(Class<C> classType) {
        return new CommandHandlerBuilder<C>(this,classType);
    }
}
