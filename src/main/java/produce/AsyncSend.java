package produce;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Objects;


public class AsyncSend {

    public static void main(String[] args) {
        Producer<String, String> producer = ProducerFactory.getProducer();
        producer.send(new ProducerRecord<>("my-topic", "1111", "1233"),
                (recordMetadata, e) -> {
                    if (Objects.isNull(e)) {
                        System.out.println(recordMetadata.toString());
                    }
                    else{
                        System.out.println(e);
                    }
                });
        producer.close();
    }
}
