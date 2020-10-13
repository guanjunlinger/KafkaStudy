package produce.serializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class MySerializer implements Serializer<Student> {

    public byte[] serialize(String s, Student student) {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = null;
        try {
            System.out.println("start serialize:" + student);
            bytes = objectMapper.writeValueAsBytes(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
