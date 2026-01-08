package repositories;

import entities.Group;
import entities.User;

public interface UserManagementRepository {
    void addUser(User user);
    void addUserToGroup(User user, Group group);
}
