package consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;

public class SyncCommit {

    public static void main(String[] args) {
        Consumer<String, String> consumer = ConsumerFactory.getConsumer();
        consumer.subscribe(Collections.singletonList("my-topic"));
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("receiver a message from consumer client:" + record.value());
        }
        consumer.commitSync(Duration.ofMillis(1000));
        consumer.close();
    }

}

