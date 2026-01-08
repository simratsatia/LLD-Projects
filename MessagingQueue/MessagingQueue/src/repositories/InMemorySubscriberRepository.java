package repositories;
import entities.Subscriber;
import entities.Topic;
import java.util.*;

public class InMemorySubscriberRepository implements SubscriberRepository{
    HashMap<Integer, Subscriber> subscribers;
    public InMemorySubscriberRepository(){
        subscribers = new HashMap<>();
    }

    //add unimplemented methods placehodler
    public void addSubscriber(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
        
    }

    public void updateSubscriber(Subscriber subscriber) {
        subscribers.put(subscriber.getId(), subscriber);
        
    }

    public Subscriber getSubscriberById(int id) {
        return subscribers.get(id);
    }

    public List<Subscriber> getSubscribersSubscribedToTopic(Topic topic) {
        List<Subscriber> subscribersSubscribedToTopic = new ArrayList<>();
        for(Subscriber subscriber : subscribers.values()){
            if(subscriber.topicIdToOffset.containsKey(topic.getId())){
                subscribersSubscribedToTopic.add(subscriber);
            }
        }
        return subscribersSubscribedToTopic;
    }

    public void addTopicToSubscriber(Subscriber subscriber, Topic topic) {
        subscriber.topicIdToOffset.put(topic.getId(), 0);
        subscribers.put(subscriber.getId(), subscriber);
    }
    
    
}
