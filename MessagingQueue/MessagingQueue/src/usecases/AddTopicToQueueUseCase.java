//Creation of entities and adding them to the repository is not the usecase of the application
//The usecase should hold the business logic only
package usecases;

import entities.Queue;
import entities.Topic;
import models.AddTopicToQueueResponse;
import repositories.QueueRepository;

public class AddTopicToQueueUseCase {
    private QueueRepository queueRepository;

    public AddTopicToQueueUseCase(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    public AddTopicToQueueResponse execute(Topic topic, Queue queue) {
        try {
            queueRepository.addTopicToQueue(topic, queue);
            return new AddTopicToQueueResponse(true, String.format("Topic %s added to queue %d successfully", topic.getName(), queue.getId()));
        } catch (Exception e) {
            return new AddTopicToQueueResponse(false, e.getMessage());
        }
    }
    
}
