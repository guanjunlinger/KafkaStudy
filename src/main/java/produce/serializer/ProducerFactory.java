package produce.serializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Objects;
import java.util.Properties;

public class ProducerFactory {
    private static Producer<String, Student> producer;

    public static Producer<String, Student> getProducer() {
        if (Objects.nonNull(producer)) {
            return producer;
        }
        Properties properties = new Properties();
        properties.put("partitioner.class","produce.MyPartitioner");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "produce.serializer.MySerializer");
        properties.put("bootstrap.servers", "BZD21333-PC.kingsoft.cn:9092");
        properties.put("acks","1");

        producer = new KafkaProducer<>(properties);
        return producer;
    }
}
