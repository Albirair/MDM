package events;

import models.Individual;

public class IndividualCreateEvent extends Event {
    private static class Payload
    {
        public Individual individual;
    }
    public IndividualCreateEvent()
    {
        super();
        eventType = "IndividualCreateEvent";
    }
}
