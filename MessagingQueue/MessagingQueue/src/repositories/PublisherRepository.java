package repositories;

import entities.Publisher;

public interface PublisherRepository {
    void addPublisher(Publisher publisher);
    Publisher getPublisherById(int id);
    
}
