package consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;

public class AsyncCommit {

    public static void main(String[] args) {
        Consumer<String, String> consumer = ConsumerFactory.getConsumer();
        consumer.subscribe(Collections.singletonList("my-topic"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("receiver a message from consumer client:" + record.value());
            }
            if (!records.isEmpty()) {
                consumer.commitAsync(((map, e) -> {
                    if (Objects.isNull(e)) {
                        System.out.println(map);
                    } else {
                        System.out.println(e);
                    }

                }));
            }
        }
    }

}
