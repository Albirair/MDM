package events;

import models.Organization;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrganizationDeleteEvent extends Event {
    public static class Payload {
        public Organization organization;
    }

    private static class OrganizationDeleteEventSerializer implements Serializer<OrganizationDeleteEvent> {
        @Override
        public byte[] serialize(String arg0, OrganizationDeleteEvent arg1) {
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

    private static Producer<String, OrganizationDeleteEvent> producer;
    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        producer = new KafkaProducer<>(properties, new StringSerializer(),
                new OrganizationDeleteEventSerializer());
    }
    public Payload event;

    public OrganizationDeleteEvent() {
        super();
        event = new Payload();
        eventType = "OrganizationDeleteEvent";
    }

    public OrganizationDeleteEvent(Organization i) {
        this();
        event.organization = i;
    }

    synchronized public void publish() {
        producer.send(new ProducerRecord<String, OrganizationDeleteEvent>("OrganizationDeleteEvent", this));
    }
}
