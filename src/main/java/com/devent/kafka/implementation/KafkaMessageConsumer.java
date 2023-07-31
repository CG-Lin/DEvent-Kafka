package com.devent.kafka.implementation;

import com.devent.command.KafkaCommandHandlerBuilder;
import com.devent.kafka.processor.HandlerProcessor;
import com.devent.messaging.common.Message;
import com.devent.messaging.consumer.CommandMessage;
import com.devent.serde.StreamsSerde;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.UsePartitionTimeOnInvalidTimestamp;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.List;
import java.util.function.Function;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/21 14:22
 */
public class KafkaMessageConsumer {

    private Topology topology;

    private String topicName;

    public KafkaMessageConsumer(){

    }

    public KafkaMessageConsumer fromTopic(String topicName){
        this.topicName = topicName;
        return this;
    }

    private KafkaMessageConsumer createTopology(){
        this.topology = new Topology();
        return this;
    }
    //TODO 修改为特定的接收器名称
    //进行消息订阅
    //@Override
    public <C> KafkaMessageConsumer subscribe(Class<C> clazz, Function<C, Message> function) {
        //字符串序列化
        Serde<String> stringSerde = Serdes.String();
        Serializer<String> stringSerializer = stringSerde.serializer();
        Serde<C> clazzSerde = StreamsSerde.createSerde(clazz);

        //反序列化
        Deserializer<String> stringDeserializer = stringSerde.deserializer();
        JsonDeserializer<C> clazzDeserializer= new JsonDeserializer<C>();

        //创建Kafka Stream
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //创建拓扑
        //Topology topology = new Topology();

        //返
        String simpleName = clazz.getSimpleName();
        HandlerProcessor<String, C, Message> processor = new HandlerProcessor.Builder<String, C, Message>()
                .withFunction(function)
                .withChildNodeName(simpleName).build();

        //拓扑链路,回到saga的编排器
        topology.addSource(Topology.AutoOffsetReset.EARLIEST, topicName,
                new UsePartitionTimeOnInvalidTimestamp(), stringDeserializer, clazzDeserializer,simpleName)
                .addProcessor(simpleName+"Processor",()->processor,simpleName)
                .addSink(simpleName+"Sink",topicName,stringSerializer,clazzSerde.serializer(),simpleName+"Processor");

        return this;
    }

}