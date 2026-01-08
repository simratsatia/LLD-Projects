package models;

import entities.Subscriber;
import entities.Topic;

public class SubscribeToTopicRequest {
    Subscriber subscriber;
    Topic topic;
    public SubscribeToTopicRequest(Subscriber subscriber, Topic topic) {
        this.subscriber = subscriber;
        this.topic = topic;
    }
}
