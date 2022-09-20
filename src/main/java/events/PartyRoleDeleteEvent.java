package events;

import models.PartyRole;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PartyRoleDeleteEvent extends Event {
    public static class Payload {
        public PartyRole partyRole;
    }

    private static class PartyRoleDeleteEventSerializer implements Serializer<PartyRoleDeleteEvent> {
        @Override
        public byte[] serialize(String arg0, PartyRoleDeleteEvent arg1) {
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

    private static Producer<String, PartyRoleDeleteEvent> producer;
    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        producer = new KafkaProducer<>(properties, new StringSerializer(),
                new PartyRoleDeleteEventSerializer());
    }
    public Payload event;

    public PartyRoleDeleteEvent() {
        super();
        event = new Payload();
        eventType = "PartyRoleDeleteEvent";
    }

    public PartyRoleDeleteEvent(PartyRole i) {
        this();
        event.partyRole = i;
    }

    synchronized public void publish() {
        producer.send(new ProducerRecord<String, PartyRoleDeleteEvent>("PartyRoleDeleteEvent", this));
    }
}
