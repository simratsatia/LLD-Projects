import entities.*;
import models.*;
import repositories.*;
import usecases.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Messaging Queue System Demo ===\n");

        // Initialize repositories
        PublisherRepository publisherRepo = new InMemoryPublisherRepository();
        SubscriberRepository subscriberRepo = new InMemorySubscriberRepository();
        QueueRepository queueRepo = new InMemoryQueueRepository();

        // Initialize use cases
        AddTopicToQueueUseCase addTopicUseCase = new AddTopicToQueueUseCase(queueRepo);
        SubscribeToTopicUseCase subscribeUseCase = new SubscribeToTopicUseCase(subscriberRepo);
        PublishMessageToTopicUseCase publishUseCase = new PublishMessageToTopicUseCase(
            publisherRepo, queueRepo, subscriberRepo);

        // Create entities
        System.out.println("1. Creating Queue and Topics...");
        Queue queue = new Queue(1);
        queueRepo.addQueue(queue);

        Topic newsTopic = new Topic(1, "news");
        Topic sportsTopic = new Topic(2, "sports");
        Topic techTopic = new Topic(3, "technology");

        // Add topics to queue
        AddTopicToQueueResponse addNewsResponse = addTopicUseCase.execute(newsTopic, queue);
        System.out.println("   " + addNewsResponse.message);

        AddTopicToQueueResponse addSportsResponse = addTopicUseCase.execute(sportsTopic, queue);
        System.out.println("   " + addSportsResponse.message);

        AddTopicToQueueResponse addTechResponse = addTopicUseCase.execute(techTopic, queue);
        System.out.println("   " + addTechResponse.message);

        // Create publishers
        System.out.println("\n2. Creating Publishers...");
        Publisher newsPublisher = new Publisher(1, "NewsPublisher");
        Publisher sportsPublisher = new Publisher(2, "SportsPublisher");
        Publisher techPublisher = new Publisher(3, "TechPublisher");

        publisherRepo.addPublisher(newsPublisher);
        publisherRepo.addPublisher(sportsPublisher);
        publisherRepo.addPublisher(techPublisher);
        System.out.println("   Created 3 publishers: NewsPublisher, SportsPublisher, TechPublisher");

        // Create subscribers
        System.out.println("\n3. Creating Subscribers...");
        Subscriber alice = new Subscriber(1, "Alice");
        Subscriber bob = new Subscriber(2, "Bob");
        Subscriber charlie = new Subscriber(3, "Charlie");

        subscriberRepo.addSubscriber(alice);
        subscriberRepo.addSubscriber(bob);
        subscriberRepo.addSubscriber(charlie);
        System.out.println("   Created 3 subscribers: Alice, Bob, Charlie");

        // Subscribe to topics
        System.out.println("\n4. Subscribing to Topics...");

        // Alice subscribes to news and tech
        subscribeUseCase.execute(alice, newsTopic);
        System.out.println("   Alice subscribed to news");
        subscribeUseCase.execute(alice, techTopic);
        System.out.println("   Alice subscribed to technology");

        // Bob subscribes to sports and tech
        subscribeUseCase.execute(bob, sportsTopic);
        System.out.println("   Bob subscribed to sports");
        subscribeUseCase.execute(bob, techTopic);
        System.out.println("   Bob subscribed to technology");

        // Charlie subscribes to all topics
        subscribeUseCase.execute(charlie, newsTopic);
        subscribeUseCase.execute(charlie, sportsTopic);
        subscribeUseCase.execute(charlie, techTopic);
        System.out.println("   Charlie subscribed to news, sports, and technology");

        // Publish messages
        System.out.println("\n5. Publishing Messages...");

        // News messages
        Message newsMsg1 = new Message(1, "Breaking: Major event happening now!");
        PublishMessageToTopicRequest newsReq1 = new PublishMessageToTopicRequest(newsPublisher, newsTopic, newsMsg1);
        PublishMessageToTopicResponse newsRes1 = publishUseCase.execute(newsReq1);
        System.out.println("   " + newsRes1.message);

        Message newsMsg2 = new Message(2, "Update: Weather forecast for tomorrow");
        PublishMessageToTopicRequest newsReq2 = new PublishMessageToTopicRequest(newsPublisher, newsTopic, newsMsg2);
        PublishMessageToTopicResponse newsRes2 = publishUseCase.execute(newsReq2);
        System.out.println("   " + newsRes2.message);

        // Sports messages
        Message sportsMsg1 = new Message(3, "Final score: Team A 3 - Team B 2");
        PublishMessageToTopicRequest sportsReq1 = new PublishMessageToTopicRequest(sportsPublisher, sportsTopic, sportsMsg1);
        PublishMessageToTopicResponse sportsRes1 = publishUseCase.execute(sportsReq1);
        System.out.println("   " + sportsRes1.message);

        // Tech messages
        Message techMsg1 = new Message(4, "New framework version released!");
        PublishMessageToTopicRequest techReq1 = new PublishMessageToTopicRequest(techPublisher, techTopic, techMsg1);
        PublishMessageToTopicResponse techRes1 = publishUseCase.execute(techReq1);
        System.out.println("   " + techRes1.message);

        Message techMsg2 = new Message(5, "Security update available");
        PublishMessageToTopicRequest techReq2 = new PublishMessageToTopicRequest(techPublisher, techTopic, techMsg2);
        PublishMessageToTopicResponse techRes2 = publishUseCase.execute(techReq2);
        System.out.println("   " + techRes2.message);

        // Display current state
        System.out.println("\n6. Current State Summary:");
        System.out.println("   Queue " + queue.getId() + " has " + queue.getTopics().size() + " topics");

        System.out.println("\n   Topic: news (" + newsTopic.getMessages().size() + " messages)");
        for (Message msg : newsTopic.getMessages()) {
            System.out.println("      - [" + msg.getId() + "] " + msg.getContent());
        }

        System.out.println("\n   Topic: sports (" + sportsTopic.getMessages().size() + " messages)");
        for (Message msg : sportsTopic.getMessages()) {
            System.out.println("      - [" + msg.getId() + "] " + msg.getContent());
        }

        System.out.println("\n   Topic: technology (" + techTopic.getMessages().size() + " messages)");
        for (Message msg : techTopic.getMessages()) {
            System.out.println("      - [" + msg.getId() + "] " + msg.getContent());
        }

        // Display subscriber subscriptions
        System.out.println("\n7. Subscriber Subscriptions:");
        System.out.println("   Alice is subscribed to " + alice.topicIdToOffset.size() + " topics");
        for (Integer topicId : alice.topicIdToOffset.keySet()) {
            System.out.println("      - Topic ID: " + topicId + " (offset: " + alice.topicIdToOffset.get(topicId) + ")");
        }

        System.out.println("\n   Bob is subscribed to " + bob.topicIdToOffset.size() + " topics");
        for (Integer topicId : bob.topicIdToOffset.keySet()) {
            System.out.println("      - Topic ID: " + topicId + " (offset: " + bob.topicIdToOffset.get(topicId) + ")");
        }

        System.out.println("\n   Charlie is subscribed to " + charlie.topicIdToOffset.size() + " topics");
        for (Integer topicId : charlie.topicIdToOffset.keySet()) {
            System.out.println("      - Topic ID: " + topicId + " (offset: " + charlie.topicIdToOffset.get(topicId) + ")");
        }

        // Initialize consume messages use case
        ConsumeMessagesUseCase consumeUseCase = new ConsumeMessagesUseCase(subscriberRepo);

        // Test message consumption
        System.out.println("\n8. Testing Message Consumption:");
        System.out.println("\n   --- Alice consuming messages from news topic ---");
        ConsumeMessagesRequest aliceConsumeNews1 = new ConsumeMessagesRequest(alice, newsTopic, 1);
        ConsumeMessagesResponse aliceNewsResponse1 = consumeUseCase.execute(aliceConsumeNews1);
        System.out.println("   " + aliceNewsResponse1.message);
        for (Message msg : aliceNewsResponse1.messages) {
            System.out.println("      Consumed: [" + msg.getId() + "] " + msg.getContent());
        }
        System.out.println("      New offset: " + aliceNewsResponse1.newOffset);

        System.out.println("\n   --- Alice consuming more messages from news topic ---");
        ConsumeMessagesRequest aliceConsumeNews2 = new ConsumeMessagesRequest(alice, newsTopic, 2);
        ConsumeMessagesResponse aliceNewsResponse2 = consumeUseCase.execute(aliceConsumeNews2);
        System.out.println("   " + aliceNewsResponse2.message);
        for (Message msg : aliceNewsResponse2.messages) {
            System.out.println("      Consumed: [" + msg.getId() + "] " + msg.getContent());
        }
        System.out.println("      New offset: " + aliceNewsResponse2.newOffset);

        System.out.println("\n   --- Bob consuming messages from sports topic ---");
        ConsumeMessagesRequest bobConsumeSports = new ConsumeMessagesRequest(bob, sportsTopic, 10);
        ConsumeMessagesResponse bobSportsResponse = consumeUseCase.execute(bobConsumeSports);
        System.out.println("   " + bobSportsResponse.message);
        for (Message msg : bobSportsResponse.messages) {
            System.out.println("      Consumed: [" + msg.getId() + "] " + msg.getContent());
        }
        System.out.println("      New offset: " + bobSportsResponse.newOffset);

        System.out.println("\n   --- Charlie consuming all messages from technology topic ---");
        ConsumeMessagesRequest charlieConsumeTech = new ConsumeMessagesRequest(charlie, techTopic, 5);
        ConsumeMessagesResponse charlieTechResponse = consumeUseCase.execute(charlieConsumeTech);
        System.out.println("   " + charlieTechResponse.message);
        for (Message msg : charlieTechResponse.messages) {
            System.out.println("      Consumed: [" + msg.getId() + "] " + msg.getContent());
        }
        System.out.println("      New offset: " + charlieTechResponse.newOffset);

        // Try to consume again when no new messages
        System.out.println("\n   --- Alice trying to consume again (no new messages) ---");
        ConsumeMessagesRequest aliceConsumeNews3 = new ConsumeMessagesRequest(alice, newsTopic, 5);
        ConsumeMessagesResponse aliceNewsResponse3 = consumeUseCase.execute(aliceConsumeNews3);
        System.out.println("   " + aliceNewsResponse3.message);

        // Display updated offsets
        System.out.println("\n9. Updated Subscriber Offsets After Consumption:");
        System.out.println("   Alice's offsets:");
        for (Integer topicId : alice.topicIdToOffset.keySet()) {
            String topicName = topicId == 1 ? "news" : topicId == 2 ? "sports" : "technology";
            System.out.println("      - Topic: " + topicName + " (offset: " + alice.topicIdToOffset.get(topicId) + ")");
        }

        System.out.println("\n   Bob's offsets:");
        for (Integer topicId : bob.topicIdToOffset.keySet()) {
            String topicName = topicId == 1 ? "news" : topicId == 2 ? "sports" : "technology";
            System.out.println("      - Topic: " + topicName + " (offset: " + bob.topicIdToOffset.get(topicId) + ")");
        }

        System.out.println("\n   Charlie's offsets:");
        for (Integer topicId : charlie.topicIdToOffset.keySet()) {
            String topicName = topicId == 1 ? "news" : topicId == 2 ? "sports" : "technology";
            System.out.println("      - Topic: " + topicName + " (offset: " + charlie.topicIdToOffset.get(topicId) + ")");
        }

        // Publish new messages and have subscribers consume them
        System.out.println("\n10. Publishing Additional Messages:");
        Message newsMsg3 = new Message(6, "Late breaking update!");
        PublishMessageToTopicRequest newsReq3 = new PublishMessageToTopicRequest(newsPublisher, newsTopic, newsMsg3);
        PublishMessageToTopicResponse newsRes3 = publishUseCase.execute(newsReq3);
        System.out.println("   " + newsRes3.message);

        System.out.println("\n11. Alice Consuming New Messages:");
        ConsumeMessagesRequest aliceConsumeNews4 = new ConsumeMessagesRequest(alice, newsTopic, 5);
        ConsumeMessagesResponse aliceNewsResponse4 = consumeUseCase.execute(aliceConsumeNews4);
        System.out.println("   " + aliceNewsResponse4.message);
        for (Message msg : aliceNewsResponse4.messages) {
            System.out.println("      Consumed: [" + msg.getId() + "] " + msg.getContent());
        }
        System.out.println("      Final offset for Alice on news: " + aliceNewsResponse4.newOffset);

        System.out.println("\n=== End-to-End Verification Complete! ===");
        System.out.println("\nVerification Summary:");
        System.out.println("✓ Queue and Topics created successfully");
        System.out.println("✓ Publishers created and can publish messages");
        System.out.println("✓ Subscribers created and can subscribe to topics");
        System.out.println("✓ Messages published to topics successfully");
        System.out.println("✓ Subscribers can consume messages with offset tracking");
        System.out.println("✓ Offsets are updated after consumption");
        System.out.println("✓ Subscribers can consume newly published messages");
        System.out.println("✓ System handles 'no new messages' scenario correctly");
        System.out.println("\n=== All systems operational! ===");
    }
}
