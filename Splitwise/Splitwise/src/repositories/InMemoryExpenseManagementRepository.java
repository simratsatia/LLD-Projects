package repositories;

import entities.Expense;
import entities.ExpenseEntry;
import entities.User;
import java.util.*;

public class InMemoryExpenseManagementRepository implements ExpenseManagementRepository{
    HashMap<String, List<ExpenseEntry>> userToExpensesMapping = new HashMap<>();
    HashMap<String, List<ExpenseEntry>> userToMoneyOwedMapping = new HashMap<>();
    HashMap<String, HashSet<String>> expenseToSettledUsersMapping = new HashMap<>();

    private void addExpenseInternal(Expense expense, Map<String, Integer> userToAmountMapping) {
        User paidByUser = expense.paidBy;
        //we need to calcuate 2 things
        //1. Expenses paid by user for himself and others
        //2. Expenses paid by others for user in the usersInvolvedList
        for(String userId: userToAmountMapping.keySet()){
            if(userId.equals(paidByUser.id)){
                if(userToExpensesMapping.containsKey(userId)){
                    userToExpensesMapping.get(userId).add(new ExpenseEntry(expense, userToAmountMapping.get(userId)));
                }else{
                    List<ExpenseEntry> ExpenseEntry = new ArrayList<>();
                    ExpenseEntry.add(new ExpenseEntry(expense, userToAmountMapping.get(userId)));
                    userToExpensesMapping.put(userId, ExpenseEntry);
                }
            }else{
                if(userToMoneyOwedMapping.containsKey(userId)){
                    userToMoneyOwedMapping.get(userId).add(new ExpenseEntry(expense, userToAmountMapping.get(userId)));
                }else{
                    List<ExpenseEntry> ExpenseEntry = new ArrayList<>();
                    ExpenseEntry.add(new ExpenseEntry(expense, userToAmountMapping.get(userId)));
                    userToMoneyOwedMapping.put(userId, ExpenseEntry);
                }
            }
        }
    }

    @Override
    public void addExpenseToFriend(Expense expense, Map<String, Integer> userToAmountMapping) {
        addExpenseInternal(expense, userToAmountMapping);
    }

    @Override
    public void addExpenseToGroup(Expense expense, Map<String, Integer> userToAmountMapping) {
        addExpenseInternal(expense, userToAmountMapping);
    }

    @Override
    public void settleExpense(Expense expense, User userPayingOwedAmount) {
        List<ExpenseEntry> ExpenseEntrys = userToMoneyOwedMapping.get(userPayingOwedAmount.id);
        if(ExpenseEntrys != null){
            ExpenseEntrys.removeIf(entry -> entry.expense.equals(expense));
        }

        // Track which users have settled this expense
        expenseToSettledUsersMapping.computeIfAbsent(expense.id, k -> new HashSet<>()).add(userPayingOwedAmount.id);
    }

    public boolean hasUserSettledExpense(Expense expense, User user) {
        HashSet<String> settledUsers = expenseToSettledUsersMapping.get(expense.id);
        return settledUsers != null && settledUsers.contains(user.id);
    }

    @Override
    public List<ExpenseEntry> getUserExpenseSheet(User user) {
        List<ExpenseEntry> expenses = new ArrayList<>();
        for(ExpenseEntry p: userToExpensesMapping.getOrDefault(user.id, new ArrayList<>())){
            expenses.add(p);
        }

        for(ExpenseEntry p: userToMoneyOwedMapping.getOrDefault(user.id, new ArrayList<>())){
            expenses.add(p);
        }

        return expenses;
    }

    
}
