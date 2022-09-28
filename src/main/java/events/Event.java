package events;

import java.util.Date;
import java.util.UUID;

public class Event {
    // public String correlationId;
    // public String description;
    // public String domain;
    public String eventId;
    public Date eventTime;
    public String eventType;

    // public String fieldPath;
    // public String href;
    // public String id;
    // public String priority;
    // public Date timeOccured;
    // public String title;
    public Event() {
        eventId = UUID.randomUUID().toString();
        eventTime = new Date();
    }
}
