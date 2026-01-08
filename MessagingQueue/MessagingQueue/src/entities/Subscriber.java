package entities;

import java.util.HashMap;

public class Subscriber {
    int id;
    String name;
    public HashMap<Integer, Integer> topicIdToOffset;
    
    public Subscriber(int id, String name) {
        this.id = id;
        this.name = name;
        topicIdToOffset = new HashMap<>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void subscribeToTopic(Topic topic) {
        topicIdToOffset.put(topic.getId(), 0);
    }
    
    
}
