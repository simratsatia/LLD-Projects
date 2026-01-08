package repositories;

import entities.Queue;
import java.util.ArrayList;

public class InMemoryQueueRepository implements QueueRepository{
    ArrayList<Queue> queues;
    public InMemoryQueueRepository(){
        queues = new ArrayList<>();
    }
    
    public void addQueue(Queue queue) {
        queues.add(queue);
    }

    public Queue getQueueById(int id) {
        for(Queue queue : queues){
            if(queue.getId() == id){
                return queue;
            }
        }
        return null;
    }

    public void addTopicToQueue(entities.Topic topic, Queue queue) {
        queue.addTopic(topic);
    }
    
    
}
