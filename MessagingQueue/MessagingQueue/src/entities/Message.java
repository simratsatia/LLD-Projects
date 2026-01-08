package entities;

public class Message {
    int id;
    String content;
    public Message(int id, String content) {
        this.id = id;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    
    
}
