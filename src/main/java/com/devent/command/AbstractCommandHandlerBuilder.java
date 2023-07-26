package com.devent.command;

public interface AbstractCommandHandlerBuilder {

    <C> CommandHandlerBuilder getCommandClassType(Class<C> commandClassType);

}
