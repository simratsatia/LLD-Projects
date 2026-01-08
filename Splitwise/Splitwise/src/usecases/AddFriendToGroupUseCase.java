package usecases;

import entities.Group;
import entities.User;
import repositories.UserManagementRepository;

public class AddFriendToGroupUseCase {
    private UserManagementRepository userManagementRepository;

    public AddFriendToGroupUseCase(UserManagementRepository userManagementRepository){
        this.userManagementRepository = userManagementRepository;

    }

    public void execute(User user, Group group) {
        userManagementRepository.addUserToGroup(user, group);
    }
}
