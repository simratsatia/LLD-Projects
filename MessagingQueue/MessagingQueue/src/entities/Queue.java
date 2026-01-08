package entities;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    int id;
    List<Topic> topics;
    public Queue(int id) {
        this.id = id;
        topics = new ArrayList<>();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public int getId() {
        return id;
    }
    
}
