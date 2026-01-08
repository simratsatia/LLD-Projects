package usecases;

import entities.Message;
import entities.Topic;
import models.PublishMessageToTopicRequest;
import models.PublishMessageToTopicResponse;
import repositories.PublisherRepository;
import repositories.QueueRepository;
import repositories.SubscriberRepository;

public class PublishMessageToTopicUseCase {
    private PublisherRepository publisherRepository;
    private QueueRepository queueRepository;
    private SubscriberRepository subscriberRepository;

    public PublishMessageToTopicUseCase(PublisherRepository publisherRepository, QueueRepository queueRepository, SubscriberRepository subscriberRepository) {
        this.publisherRepository = publisherRepository;
        this.queueRepository = queueRepository;
        this.subscriberRepository = subscriberRepository;
    }
    
    public PublishMessageToTopicResponse execute(PublishMessageToTopicRequest request) {
        // Add message to topic
        Topic topic = request.topic;
        Message message = request.message;
        topic.addMessage(message);
        //notify the consumers

        return new PublishMessageToTopicResponse(true, "Message published successfully to topic " + topic.getName());
    }


    
}
