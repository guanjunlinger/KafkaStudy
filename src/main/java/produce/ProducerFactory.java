package produce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ProducerFactory {
    private static Producer<String, String> producer;

    public static Producer<String, String> getProducer() {
        if (Objects.nonNull(producer)) {
            return producer;
        }
        Properties properties = new Properties();
        properties.put("partitioner.class", "produce.MyPartitioner");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", "LAPTOP-8UNHPEU7:9092");
        properties.put("acks", "1");

        List<String> interceptors = new ArrayList<>();
        interceptors.add("produce.MyProducerInterceptor");
        interceptors.add("produce.CounterProducerInterceptor");
        properties.put("interceptor.classes", interceptors);
        producer = new KafkaProducer<>(properties);
        return producer;
    }

}
