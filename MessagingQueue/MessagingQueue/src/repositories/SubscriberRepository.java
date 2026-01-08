package repositories;

import entities.Subscriber;
import entities.Topic;
import java.util.List;

public interface SubscriberRepository {
    public void addSubscriber(Subscriber subscriber);
    public void updateSubscriber(Subscriber subscriber);
    public Subscriber getSubscriberById(int id);
    public List<Subscriber> getSubscribersSubscribedToTopic(Topic topic);
    public void addTopicToSubscriber(Subscriber subscriber, Topic topic);
}
