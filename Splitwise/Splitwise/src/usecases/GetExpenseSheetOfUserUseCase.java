package usecases;

import entities.Expense;
import entities.ExpenseEntry;
import entities.User;
import java.util.*;
import repositories.ExpenseManagementRepository;
import repositories.UserManagementRepository;

public class GetExpenseSheetOfUserUseCase {
        private ExpenseManagementRepository expenseManagementRepository;
    private UserManagementRepository userManagementRepository;

    public GetExpenseSheetOfUserUseCase(ExpenseManagementRepository expenseManagementRepository, UserManagementRepository userManagementRepository){
        this.userManagementRepository = userManagementRepository;
        this.expenseManagementRepository = expenseManagementRepository;
    }

    public void execute(User user){
        List<ExpenseEntry> expenses = expenseManagementRepository.getUserExpenseSheet(user);
        repositories.InMemoryExpenseManagementRepository repo =
            (repositories.InMemoryExpenseManagementRepository) expenseManagementRepository;

        for(ExpenseEntry expenseEntry : expenses){
            int amount = expenseEntry.amount;
            Expense expense = expenseEntry.expense;
            User paidBy = expense.paidBy;
            if (paidBy.equals(user)){
                System.out.println("You paid " + amount + " for " + expense.name);
                for(User userInvolved: expense.usersInvolved){
                    if(!userInvolved.equals(user) && !repo.hasUserSettledExpense(expense, userInvolved)){
                        System.out.println("You need to get " + amount/expense.usersInvolved.size() + " from " + userInvolved.name);
                    }
                }
            } else {
                System.out.println(paidBy.name + " paid " + amount + " for " + expense.name);
            }
        }
    }
}
