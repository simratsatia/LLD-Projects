package entities;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public int id;
    public String name;
    public List<Message> messages;
    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
        this.messages = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Message> getMessages() {
        return messages;
    }
    public void addMessage(Message message) {
        messages.add(message);
    }
}
