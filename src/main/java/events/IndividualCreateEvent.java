package events;

import models.Party.Individual;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IndividualCreateEvent extends Event {
    public static class Payload {
        public Individual individual;
    }

    private static class IndividualCreateEventSerializer implements Serializer<IndividualCreateEvent> {
        @Override
        public byte[] serialize(String arg0, IndividualCreateEvent arg1) {
            byte[] retVal = null;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
            try {
                retVal = objectMapper.writeValueAsString(arg1).getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return retVal;
        }
    }

    private static Producer<String, IndividualCreateEvent> producer;
    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        producer = new KafkaProducer<>(properties, new StringSerializer(),
                new IndividualCreateEventSerializer());
    }
    public Payload event;

    public IndividualCreateEvent() {
        super();
        event = new Payload();
        eventType = "IndividualCreateEvent";
    }

    public IndividualCreateEvent(Individual i) {
        this();
        event.individual = i;
    }

    synchronized public void publish() {
        producer.send(new ProducerRecord<String, IndividualCreateEvent>("IndividualCreateEvent", this));
    }
}
