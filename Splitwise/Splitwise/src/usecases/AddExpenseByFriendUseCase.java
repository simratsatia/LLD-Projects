package usecases;

import entities.Expense;
import entities.User;
import java.util.Map;
import repositories.ExpenseManagementRepository;
import repositories.UserManagementRepository;
import splitStrategies.SplitStrategy;

public class AddExpenseByFriendUseCase {
    private ExpenseManagementRepository expenseManagementRepository;
    private UserManagementRepository userManagementRepository;

    public AddExpenseByFriendUseCase(ExpenseManagementRepository expenseManagementRepository, UserManagementRepository userManagementRepositorys){
        this.userManagementRepository = userManagementRepository;
        this.expenseManagementRepository = expenseManagementRepository;
    }

    public void execute(Expense expense){
        Map<String, Integer> userToAmountMapping = expense.splitStrategy.splitBetween(expense.amount, expense.usersInvolved);
        expenseManagementRepository.addExpenseToFriend(expense, userToAmountMapping);
    }
}
