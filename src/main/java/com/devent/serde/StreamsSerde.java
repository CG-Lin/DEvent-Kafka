package com.devent.serde;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/6/12 23:43
 */
public class StreamsSerde{

    public static <T> Serde<T> createSerde(Class<T> targetType){
        return new GenericSerde<>(targetType);
    }

    public static final class GenericSerde<T> extends Serdes.WrapperSerde<T> {
        public GenericSerde(Class<T> targetType) {
            super(new JsonSerializer<>(), new JsonDeserializer<>(targetType));
        }
    }
}
