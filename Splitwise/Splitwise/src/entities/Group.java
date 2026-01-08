package entities;

import java.util.Set;

public class Group {
    public String id;
    public String name;
    public Set<User> users;
    public Group(String id, String name, Set<User> users){
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public void addUserToGroup(User user){
        this.users.add(user);
    }
    
    public void removeUserFromGroup(User user){
        this.users.remove(user);
    }

}
