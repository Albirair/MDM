package events;

import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.party.Organization;

public class OrganizationCreateEvent extends Event {
    public static class Payload {
        public Organization organization;
    }

    private static class OrganizationCreateEventSerializer implements Serializer<OrganizationCreateEvent> {
        @Override
        public byte[] serialize(String arg0, OrganizationCreateEvent arg1) {
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

    private static Producer<String, OrganizationCreateEvent> producer;
    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        producer = new KafkaProducer<>(properties, new StringSerializer(),
                new OrganizationCreateEventSerializer());
    }
    public Payload event;

    public OrganizationCreateEvent() {
        super();
        event = new Payload();
        eventType = "OrganizationCreateEvent";
    }

    public OrganizationCreateEvent(Organization i) {
        this();
        event.organization = i;
    }

    synchronized public void publish() {
        producer.send(new ProducerRecord<String, OrganizationCreateEvent>("OrganizationCreateEvent", this));
    }
}
