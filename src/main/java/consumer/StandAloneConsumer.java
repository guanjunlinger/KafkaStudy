package consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneConsumer {

    public static void main(String[] args) {
        Consumer<String, String> consumer = ConsumerFactory.getConsumer();
        List<TopicPartition> partitionList = consumer.partitionsFor("my-topic").stream().map(partitionInfo -> {
            return new TopicPartition(partitionInfo.topic(), partitionInfo.partition());


        }).collect(Collectors.toList());
        consumer.assign(partitionList);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("receiver a message  key:" + record.key() + ",value:" + record.value());
            }
            consumer.commitSync();
        }

    }

}
