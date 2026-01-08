package repositories;

import entities.Group;
import entities.User;
import java.util.*;

public class InMemoryUserManagementRepository implements UserManagementRepository{
    HashSet<User> userSet;
    HashMap<Group, List<User>> groupToUserListMap;
    public InMemoryUserManagementRepository(){
        this.userSet = new HashSet<>();
        this.groupToUserListMap = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        // TODO Auto-generated method stub
        userSet.add(user);
    }

    @Override
    public void addUserToGroup(User user, Group group) {
        // TODO Auto-generated method stub
        List<User> userList = groupToUserListMap.getOrDefault(group, new ArrayList<>());
        userList.add(user);
        groupToUserListMap.put(group, userList);
    }
    
}
