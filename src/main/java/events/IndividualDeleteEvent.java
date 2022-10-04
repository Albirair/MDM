package events;

import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.party.Individual;

public class IndividualDeleteEvent extends Event {
    public static class Payload {
        public Individual individual;
    }

    private static class IndividualDeleteEventSerializer implements Serializer<IndividualDeleteEvent> {
        @Override
        public byte[] serialize(String arg0, IndividualDeleteEvent arg1) {
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

    private static Producer<String, IndividualDeleteEvent> producer;
    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        producer = new KafkaProducer<>(properties, new StringSerializer(),
                new IndividualDeleteEventSerializer());
    }
    public Payload event;

    public IndividualDeleteEvent() {
        super();
        event = new Payload();
        eventType = "IndividualDeleteEvent";
    }

    public IndividualDeleteEvent(Individual i) {
        this();
        event.individual = i;
    }

    synchronized public void publish() {
        producer.send(new ProducerRecord<String, IndividualDeleteEvent>("IndividualDeleteEvent", this));
    }
}
