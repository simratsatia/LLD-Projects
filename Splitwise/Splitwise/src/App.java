
import entities.Expense;
import entities.ExpenseType;
import entities.Group;
import entities.User;
import java.util.*;
import repositories.ExpenseManagementRepository;
import repositories.InMemoryExpenseManagementRepository;
import repositories.InMemoryUserManagementRepository;
import repositories.UserManagementRepository;
import splitStrategies.EqualSplitStrategy;
import splitStrategies.SplitStrategy;
import usecases.AddExpenseByFriendUseCase;
import usecases.AddExpenseToGroupUseCase;
import usecases.AddFriendToGroupUseCase;
import usecases.GetExpenseSheetOfUserUseCase;
import usecases.SettleExpenseUseCase;

public class App {
    public class CLIController {
        AddExpenseByFriendUseCase addExpenseByFriendUseCase;
        AddExpenseToGroupUseCase addExpenseToGroupUseCase;
        AddFriendToGroupUseCase addFriendToGroupUseCase;
        GetExpenseSheetOfUserUseCase getExpenseSheetOfUserUseCase;
        SettleExpenseUseCase settleExpenseUseCase;

        CLIController(
        AddExpenseByFriendUseCase addExpenseByFriendUseCase,
        AddExpenseToGroupUseCase addExpenseToGroupUseCase,
        AddFriendToGroupUseCase addFriendToGroupUseCase,
        GetExpenseSheetOfUserUseCase getExpenseSheetOfUserUseCase,
        SettleExpenseUseCase settleExpenseUseCase){
            this.addExpenseByFriendUseCase = addExpenseByFriendUseCase;
            this.addExpenseToGroupUseCase = addExpenseToGroupUseCase;
            this.addFriendToGroupUseCase = addFriendToGroupUseCase;
            this.getExpenseSheetOfUserUseCase = getExpenseSheetOfUserUseCase;
            this.settleExpenseUseCase = settleExpenseUseCase;

        }

        public void addFriendToGroup(User user, Group group){
            this.addFriendToGroupUseCase.execute(user, group);
        }

        public void addExpenseByFriend(Expense expense){
            this.addExpenseByFriendUseCase.execute(expense);
        }

        public void addExpenseToGroup(Expense expense){
            this.addExpenseToGroupUseCase.execute(expense);
        }

        public void getExpenseSheetOfUser(User user){
            this.getExpenseSheetOfUserUseCase.execute(user);
        }

        public void settleExpense(Expense expense, User user){
            this.settleExpenseUseCase.execute(expense, user);
        }
        
    }
    public static void main(String[] args) throws Exception {
        
        //Create user
        User user1 = new User("1", "u1");
        User user2 = new User("2", "u2");
        User user3 = new User("3", "u3");
        User user4 = new User("4", "u4");
        

        //Create group
        HashSet<User> group1Users = new HashSet<>();
        group1Users.add(user1);
        group1Users.add(user2);
        group1Users.add(user3);
        Group group1 = new Group("1", "g1", group1Users);

        HashSet<User> group2Users = new HashSet<>();
        group2Users.add(user3);
        group2Users.add(user4);
        Group group2 = new Group("2", "g2", group2Users);

        //Create repository
        UserManagementRepository userManagementRepository = new InMemoryUserManagementRepository();
        ExpenseManagementRepository expenseManagementRepository = new InMemoryExpenseManagementRepository();
        userManagementRepository.addUser(user1);
        userManagementRepository.addUser(user2);
        userManagementRepository.addUser(user3);
        userManagementRepository.addUser(user4);

        SplitStrategy equalSplitStrategy = new EqualSplitStrategy();
        //create expenses
        Expense expense1 = new Expense("1", "Food expense", 100, ExpenseType.FOOD, user1, new ArrayList<>(group1.users), equalSplitStrategy);
        //execute usecases
        AddExpenseByFriendUseCase addExpenseByFriendUseCase = new AddExpenseByFriendUseCase(expenseManagementRepository, userManagementRepository);
        AddExpenseToGroupUseCase addExpenseToGroupUseCase = new AddExpenseToGroupUseCase(expenseManagementRepository, userManagementRepository);
        AddFriendToGroupUseCase addFriendToGroupUseCase = new AddFriendToGroupUseCase(userManagementRepository);
        GetExpenseSheetOfUserUseCase getExpenseSheetOfUserUseCase = new GetExpenseSheetOfUserUseCase(expenseManagementRepository, userManagementRepository);
        SettleExpenseUseCase settleExpenseUseCase = new SettleExpenseUseCase(expenseManagementRepository);

        CLIController cliController = new App().new CLIController(addExpenseByFriendUseCase, addExpenseToGroupUseCase, addFriendToGroupUseCase, getExpenseSheetOfUserUseCase, settleExpenseUseCase);

        //emulate the workflow now!
        cliController.addExpenseByFriend(expense1);
        cliController.getExpenseSheetOfUser(user1);
        cliController.settleExpense(expense1, user2);
        cliController.getExpenseSheetOfUser(user1);

    }
}
