package repositories;

import entities.Queue;
import entities.Topic;

public interface QueueRepository {
    void addQueue(Queue queue);
    Queue getQueueById(int id);
    void addTopicToQueue(Topic topic, Queue queue);
}
