package repositories;

import entities.Publisher;
import java.util.ArrayList;
import java.util.List;

public class InMemoryPublisherRepository implements PublisherRepository {
    List<Publisher> publishers;
    public InMemoryPublisherRepository(){
        publishers = new ArrayList<>();
    }

    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public Publisher getPublisherById(int id) {
        for(Publisher publisher : publishers){
            if(publisher.getId() == id){
                return publisher;
            }
        }
        return null;
    }
}
