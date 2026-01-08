# Messaging Queue System

A Low-Level Design (LLD) implementation of a Pub-Sub Messaging Queue system in Java, demonstrating clean architecture principles and design patterns.

## Table of Contents
- [About the Project](#about-the-project)
- [What It Does](#what-it-does)
- [Project Structure](#project-structure)
- [Clean Code Architecture](#clean-code-architecture)
- [How to Run](#how-to-run)
- [Known Issues](#known-issues)

## About the Project

This is a practice project for implementing Low-Level System Design (LLD) concepts in Java. It demonstrates a simplified messaging queue system similar to Kafka or RabbitMQ, where publishers can send messages to topics, and subscribers can consume messages from topics they're subscribed to.

The project focuses on:
- Object-Oriented Design principles
- Clean Architecture patterns
- Repository pattern for data access
- Use case driven design

## What It Does

The Messaging Queue System is a **fully functional pub-sub messaging system** similar to Apache Kafka or RabbitMQ, providing complete message queue functionality with offset-based consumption.

### Core Features
1. **Queue Management**: Create and manage message queues
2. **Topic Management**: Add topics to queues for content categorization
3. **Publisher Operations**: Publishers can publish messages to specific topics
4. **Subscriber Operations**: Subscribers can subscribe to topics and consume messages with offset tracking
5. **Offset Tracking**: Each subscriber maintains independent offsets per topic (like Kafka consumer groups)
6. **Batch Consumption**: Subscribers can consume messages in configurable batch sizes
7. **Independent Consumption**: Multiple subscribers can consume from the same topic at different rates
8. **Real-time Updates**: New messages are immediately available for consumption

### Use Cases Implemented
- `PublishMessageToTopicUseCase`: Allows publishers to send messages to a topic
- `SubscribeToTopicUseCase`: Enables subscribers to subscribe to topics
- `AddTopicToQueueUseCase`: Adds topics to message queues
- `ConsumeMessagesUseCase`: Enables subscribers to consume messages with offset tracking

## Project Structure

The project follows a layered architecture with clear separation of concerns:

```
src/
├── entities/               # Core domain entities
│   ├── Message.java       # Represents a message with id and content
│   ├── Publisher.java     # Publisher entity
│   ├── Queue.java         # Queue entity containing topics
│   ├── Subscriber.java    # Subscriber with topic offset tracking
│   └── Topic.java         # Topic entity containing messages
│
├── usecases/              # Business logic layer
│   ├── AddTopicToQueueUseCase.java
│   ├── ConsumeMessagesUseCase.java
│   ├── PublishMessageToTopicUseCase.java
│   └── SubscribeToTopicUseCase.java
│
├── repositories/          # Data access layer
│   ├── PublisherRepository.java           # Interface
│   ├── InMemoryPublisherRepository.java   # Implementation
│   ├── QueueRepository.java               # Interface
│   ├── InMemoryQueueRepository.java       # Implementation
│   ├── SubscriberRepository.java          # Interface
│   └── InMemorySubscriberRepository.java  # Implementation
│
├── models/                # Request/Response DTOs
│   ├── PublishMessageToTopicRequest.java
│   ├── PublishMessageToTopicResponse.java
│   ├── SubscribeToTopicRequest.java
│   ├── SubscribeToTopicResponse.java
│   ├── AddTopicToQueueRequest.java
│   ├── AddTopicToQueueResponse.java
│   ├── ConsumeMessagesRequest.java
│   └── ConsumeMessagesResponse.java
│
└── App.java              # Main application entry point
```

## Clean Code Architecture

This project demonstrates **Clean Architecture** principles:

### 1. Separation of Concerns
- **Entities**: Pure business objects with minimal logic (Message, Publisher, Subscriber, Queue, Topic)
- **Use Cases**: Application-specific business rules
- **Repositories**: Data access abstraction
- **Models**: Data transfer objects for use case inputs/outputs

### 2. Dependency Inversion Principle
- Use cases depend on repository interfaces, not implementations
- This allows easy swapping of in-memory storage with database implementations
- Example: `PublishMessageToTopicUseCase` depends on `PublisherRepository` interface

### 3. Interface Segregation
- Each repository has a well-defined interface:
  - `PublisherRepository`: Manages publisher CRUD operations
  - `SubscriberRepository`: Manages subscriber operations and subscriptions
  - `QueueRepository`: Manages queue and topic associations

### 4. Single Responsibility Principle
- Each class has one reason to change:
  - Entities only contain state and minimal behavior
  - Use cases contain specific business logic
  - Repositories handle data persistence

### 5. Repository Pattern
All data access is abstracted through repository interfaces with in-memory implementations:
- Allows easy testing with mock repositories
- Enables future migration to database storage without changing business logic

### Design Highlights
- **Request/Response Pattern**: Use cases accept request objects and return response objects
- **Immutable IDs**: Entities are identified by integer IDs
- **Offset Management**: Subscribers track reading position using HashMap<TopicId, Offset>

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal or Command Prompt

### Compilation

Navigate to the project root directory and compile the source files:

```bash
cd /Users/simratsatia/Desktop/Projects/LLD/MessagingQueue/MessagingQueue
find src -name "*.java" | xargs javac -d bin
```

This will compile all Java files and place the `.class` files in a `bin` directory.

Note: The `find` command is used because some shells don't expand `**` glob patterns correctly.

### Execution

Run the main application:

```bash
cd bin
java App
```

### Running the Demo

The project includes a comprehensive demo in `App.java` that demonstrates all features. Simply run:

```bash
cd bin
java App
```

### What the Demo Shows

The demo demonstrates a complete end-to-end workflow:

1. **Queue & Topic Creation**: Creates 1 queue with 3 topics (news, sports, technology)
2. **Publisher Setup**: Creates 3 publishers for different content types
3. **Subscriber Setup**: Creates 3 subscribers (Alice, Bob, Charlie) with different subscription preferences
4. **Message Publishing**: Publishes 5 messages across different topics
5. **Message Consumption**: Demonstrates subscribers consuming messages with offset tracking
6. **Offset Management**: Shows how offsets are updated as messages are consumed
7. **Real-time Updates**: Publishes new messages and shows subscribers consuming them
8. **Edge Cases**: Handles "no new messages" scenario correctly

The demo validates all aspects of the pub-sub pattern with independent offset tracking per subscriber.

## System Status

### ✓ Fully Functional
The messaging queue system is **complete and operational** with all core features implemented and tested:

- ✓ Queue and Topic management
- ✓ Publisher operations
- ✓ Subscriber operations with subscription management
- ✓ Message publishing to topics
- ✓ Message consumption with offset tracking
- ✓ Independent offset management per subscriber
- ✓ Batch consumption support
- ✓ "No new messages" detection
- ✓ Real-time message updates
- ✓ End-to-end verification complete

### TODO
- **Message Repository**: Messages are currently stored in Topic entities. For a production system, consider implementing a separate MessageRepository for better separation of concerns and query capabilities.

## Features

### Publishing Flow
- Multiple publishers can publish to different topics
- Messages are stored in topics correctly
- Message IDs and content are preserved

### Subscription Flow
- Subscribers can subscribe to multiple topics
- Subscription state is maintained in the repository
- Initial offset is set to 0 for new subscriptions

### Consumption Flow
- Subscribers can consume messages in batches
- Offset tracking prevents message duplication
- Each subscriber maintains independent offsets per topic
- System correctly handles "caught up" state (no new messages)
- New messages published after consumption are available to consume

### Multi-Subscriber Scenario
Verified with 3 subscribers (Alice, Bob, Charlie) having different subscription patterns:
- Alice subscribed to 2 topics, consumed messages independently
- Bob subscribed to 2 topics with overlap, maintained separate offsets
- Charlie subscribed to all topics, consumed at different rates
- All offsets tracked independently and correctly

## Future Enhancements
- Add thread safety for concurrent publishers/subscribers
- Implement message acknowledgment system
- Implement dead letter queue for failed messages
- Add message filtering and routing
- Implement consumer groups for load balancing
- Add metrics and monitoring
- Implement message retention policies
- Add support for message priorities

## License
This is a practice project for learning Low-Level Design patterns.
