package models;

import entities.Queue;
import entities.Topic;

public class AddTopicToQueueRequest {
    public Topic topic;
    public Queue queue;
    AddTopicToQueueRequest(Topic topic, Queue queue) {
        this.topic = topic;
        this.queue = queue;
    }
}
