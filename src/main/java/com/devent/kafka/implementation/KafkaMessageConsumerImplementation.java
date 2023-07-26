package com.devent.kafka.implementation;

import com.devent.command.CommandHandlerBuilder;
import com.devent.command.KafkaCommandHandlerBuilder;
import com.devent.comsumer.common.MessageConsumerImplementation;
import com.devent.kafka.processor.HandlerProcessor;
import com.devent.messaging.common.Message;
import com.devent.messaging.common.MessageImpl;
import com.devent.messaging.consumer.MessageHandler;
import com.devent.test.handler.TestHandlerEntity;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.UsePartitionTimeOnInvalidTimestamp;

import java.util.Set;

/**
 * @author wuk
 * @description:
 * @menu
 * @date 2023/7/21 14:22
 */
public class KafkaMessageConsumerImplementation implements MessageConsumerImplementation {
    //TODO 修改为特定的接收器名称
    String purchaseSourceNodeName = "purchase-source-node-name";
    //处理器名称 TODO 修改为特定的处理器名称
    String processorNodeName = "processor-node-name";
    //进行消息订阅
    @Override
    public void subscribe() throws InstantiationException, IllegalAccessException {
        //字符串序列化
        Serde<String> stringSerde = Serdes.String();
        Serializer<String> stringSerializer = stringSerde.serializer();
        Deserializer<String> stringDeserializer = stringSerde.deserializer();

        //创建Kafka Stream
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //创建拓扑
        Topology topology = new Topology();

        //new HandlerProcessor.Builder<,String,String>()
        //拓扑链路
        topology.addSource(Topology.AutoOffsetReset.EARLIEST,purchaseSourceNodeName,
                new UsePartitionTimeOnInvalidTimestamp(),stringDeserializer,stringDeserializer);
                //.addProcessor(processorNodeName,)

    }

}
