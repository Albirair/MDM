package events;

import java.text.SimpleDateFormat;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventSerializer implements Serializer<Event<?>> {
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
	}

	@Override
	public byte[] serialize(String topic, Event<?> data) {
		byte[] retVal = null;
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
