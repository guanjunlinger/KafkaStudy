package consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Objects;
import java.util.Properties;

public class ConsumerFactory {

    private static Consumer<String, String> consumer;

    public static Consumer<String, String> getConsumer() {
        if (Objects.nonNull(consumer)) {
            return consumer;
        }
        Properties props = new Properties();
        props.put("bootstrap.servers", "LAPTOP-8UNHPEU7:9092");
        props.put("group.id", "logGroup");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        return consumer;
    }
}
