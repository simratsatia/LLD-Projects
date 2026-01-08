package models;

import entities.Message;
import entities.Publisher;
import entities.Topic;

public class PublishMessageToTopicRequest {
    public Publisher publisher;
    public Topic topic;
    public Message message;

    public PublishMessageToTopicRequest(Publisher publisher, Topic topic, Message message) {
        this.publisher = publisher;
        this.topic = topic;
        this.message = message;
    }
    
}
