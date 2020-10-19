package produce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;

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

        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "produce.MyPartitioner");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "LAPTOP-8UNHPEU7:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "1");


        List<String> interceptors = new ArrayList<>();
        interceptors.add("produce.MyProducerInterceptor");
        interceptors.add("produce.CounterProducerInterceptor");
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
        producer = new KafkaProducer<>(properties);
        return producer;
    }

}
