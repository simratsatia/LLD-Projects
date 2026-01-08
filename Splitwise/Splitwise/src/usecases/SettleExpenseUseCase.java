package usecases;

import entities.Expense;
import entities.User;
import repositories.ExpenseManagementRepository;

public class SettleExpenseUseCase {
        private ExpenseManagementRepository expenseManagementRepository;

    public SettleExpenseUseCase(ExpenseManagementRepository expenseManagementRepository){
        this.expenseManagementRepository = expenseManagementRepository;
    }

    public void execute(Expense expense, User user) {
        expenseManagementRepository.settleExpense(expense, user);
    }
}
