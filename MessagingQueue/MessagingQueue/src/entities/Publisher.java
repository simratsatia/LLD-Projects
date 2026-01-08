package entities;

public class Publisher {
    int id;
    String name;
    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
}
