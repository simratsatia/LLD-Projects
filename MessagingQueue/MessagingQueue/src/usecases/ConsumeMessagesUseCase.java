package usecases;

import entities.Message;
import entities.Subscriber;
import entities.Topic;
import models.ConsumeMessagesRequest;
import models.ConsumeMessagesResponse;
import repositories.SubscriberRepository;

import java.util.ArrayList;
import java.util.List;

public class ConsumeMessagesUseCase {
    private SubscriberRepository subscriberRepository;

    public ConsumeMessagesUseCase(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public ConsumeMessagesResponse execute(ConsumeMessagesRequest request) {
        Subscriber subscriber = request.subscriber;
        Topic topic = request.topic;
        int batchSize = request.batchSize;

        // Check if subscriber is subscribed to the topic
        if (!subscriber.topicIdToOffset.containsKey(topic.getId())) {
            return new ConsumeMessagesResponse(false,
                "Subscriber " + subscriber.getName() + " is not subscribed to topic " + topic.getName(),
                new ArrayList<>(), 0);
        }

        // Get current offset for this subscriber on this topic
        int currentOffset = subscriber.topicIdToOffset.get(topic.getId());

        // Get all messages from the topic
        List<Message> allMessages = topic.getMessages();

        // Check if there are new messages to consume
        if (currentOffset >= allMessages.size()) {
            return new ConsumeMessagesResponse(true,
                "No new messages available for subscriber " + subscriber.getName() + " on topic " + topic.getName(),
                new ArrayList<>(), currentOffset);
        }

        // Get messages from current offset up to batchSize
        List<Message> consumedMessages = new ArrayList<>();
        int endOffset = Math.min(currentOffset + batchSize, allMessages.size());

        for (int i = currentOffset; i < endOffset; i++) {
            consumedMessages.add(allMessages.get(i));
        }

        // Update subscriber's offset
        subscriber.topicIdToOffset.put(topic.getId(), endOffset);
        subscriberRepository.updateSubscriber(subscriber);

        return new ConsumeMessagesResponse(true,
            "Consumed " + consumedMessages.size() + " message(s) from topic " + topic.getName(),
            consumedMessages, endOffset);
    }
}
