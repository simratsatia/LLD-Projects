package models;

import entities.Subscriber;
import entities.Topic;

public class ConsumeMessagesRequest {
    public Subscriber subscriber;
    public Topic topic;
    public int batchSize;

    public ConsumeMessagesRequest(Subscriber subscriber, Topic topic, int batchSize) {
        this.subscriber = subscriber;
        this.topic = topic;
        this.batchSize = batchSize;
    }
}
