package events;

import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.party.PartyRole;

public class PartyRoleAttributeValueChangeEvent extends Event {
    public static class Payload {
        public PartyRole partyRole;
    }

    private static class PartyRoleAttributeValueChangeEventSerializer
            implements Serializer<PartyRoleAttributeValueChangeEvent> {
        @Override
        public byte[] serialize(String arg0, PartyRoleAttributeValueChangeEvent arg1) {
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

    private static Producer<String, PartyRoleAttributeValueChangeEvent> producer;
    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        producer = new KafkaProducer<>(properties, new StringSerializer(),
                new PartyRoleAttributeValueChangeEventSerializer());
    }
    public Payload event;

    public PartyRoleAttributeValueChangeEvent() {
        super();
        event = new Payload();
        eventType = "PartyRoleAttributeValueChangeEvent";
    }

    public PartyRoleAttributeValueChangeEvent(PartyRole i) {
        this();
        event.partyRole = i;
    }

    synchronized public void publish() {
        producer.send(new ProducerRecord<String, PartyRoleAttributeValueChangeEvent>(
                "PartyRoleAttributeValueChangeEvent", this));
    }
}
