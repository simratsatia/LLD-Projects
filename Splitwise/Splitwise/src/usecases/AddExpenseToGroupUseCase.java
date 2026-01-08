package usecases;

import entities.Expense;
import java.util.Map;
import repositories.ExpenseManagementRepository;
import repositories.UserManagementRepository;

public class AddExpenseToGroupUseCase {
    private ExpenseManagementRepository expenseManagementRepository;
    private UserManagementRepository userManagementRepository;

    public AddExpenseToGroupUseCase(ExpenseManagementRepository expenseManagementRepository, UserManagementRepository userManagementRepository){
        this.userManagementRepository = userManagementRepository;
        this.expenseManagementRepository = expenseManagementRepository;
    }

    public void execute(Expense expense) {
        Map<String, Integer> userToAmountMapping = expense.splitStrategy.splitBetween(expense.amount, expense.usersInvolved);
        expenseManagementRepository.addExpenseToGroup(expense, userToAmountMapping);
    }
}
