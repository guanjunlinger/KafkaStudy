package produce;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.Objects;

public class CounterProducerInterceptor implements ProducerInterceptor {
    private int successCount;
    private int errorCount;

    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (Objects.isNull(e)) {
            successCount++;
        } else {
            errorCount++;
        }
    }

    @Override
    public void close() {
        System.out.println("successCount:" + successCount);
        System.out.println("errorCount:" + errorCount);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
