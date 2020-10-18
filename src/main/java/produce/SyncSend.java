package produce;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class SyncSend {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Producer<String, String> producer = ProducerFactory.getProducer();
        RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("my-topic", "1222","1233")).get();
        System.out.println(recordMetadata);
        producer.close();
    }
}
