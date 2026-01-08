package repositories;

import entities.Expense;
import entities.ExpenseEntry;
import entities.User;
import java.util.List;
import java.util.Map;

public interface ExpenseManagementRepository {
    public void addExpenseToFriend(Expense expense, Map<String, Integer> userToAmountMapping);
    public void addExpenseToGroup(Expense expense, Map<String, Integer> userToAmountMapping);
    public void settleExpense(Expense expense, User userPayingOwedAmount);
    public List<ExpenseEntry> getUserExpenseSheet(User user);
}
