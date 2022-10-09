package events;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class Event<Payload> {
	// public String correlationId;
	// public String description;
	// public String domain;
	public String eventId;
	public Date eventTime;
	public Type eventType;
	public Payload payload;
	// public String fieldPath;
	// public String href;
	// public String id;
	// public String priority;
	// public Date timeOccured;
	// public String title;
	private static Producer<String, Event<?>> producer;
	static {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		producer = new KafkaProducer<>(properties, new StringSerializer(),
				new EventSerializer());
	}

	public Event(Payload p, Type type) {
		eventId = UUID.randomUUID().toString();
		eventTime = new Date();
		payload = p;
		eventType = type;
	}

	synchronized public void publish() {
		producer.send(new ProducerRecord<String, Event<?>>(
				String.format("%s%sEvent", payload.getClass().getSimpleName(), eventType), this));
	}
}
