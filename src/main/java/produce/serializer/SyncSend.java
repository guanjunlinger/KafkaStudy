package produce.serializer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class SyncSend {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Producer<String, Student> producer = ProducerFactory.getProducer();
        Student student = new Student();
        student.setAddress("bei jin");
        student.setName("liu cong");
        student.setAge(23);
        RecordMetadata recordMetadata = producer.send(new ProducerRecord<>("my-topic", "111", student)).get();
        System.out.println(recordMetadata);
        producer.close();
    }
}
