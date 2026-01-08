package usecases;

import entities.Subscriber;
import entities.Topic;
import models.SubscribeToTopicResponse;
import repositories.SubscriberRepository;

public class SubscribeToTopicUseCase {
    private SubscriberRepository subscriberRepository;
    public SubscribeToTopicUseCase(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public SubscribeToTopicResponse execute(Subscriber subscriber, Topic topic) {
        subscriberRepository.addTopicToSubscriber(subscriber, topic);
        return new SubscribeToTopicResponse(true, "Subscriber" + subscriber.getName() + "to topic " + topic.getName());
    }
    
}
