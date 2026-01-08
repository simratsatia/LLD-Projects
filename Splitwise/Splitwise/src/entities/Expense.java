package entities;

import java.util.*;
import splitStrategies.SplitStrategy;

public class Expense {
    public String id;
    public String name;
    public int amount;
    public ExpenseType expenseType;
    public User paidBy;
    public List<User> usersInvolved;
    public boolean isSettled;
    public SplitStrategy splitStrategy;
    public Expense(String id, String name, int amount, ExpenseType type, User paidBy, List<User> usersInvolved, SplitStrategy splitStrategy){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expenseType = type;
        this.paidBy = paidBy;
        this.usersInvolved = usersInvolved;
        this.isSettled = false;
        this.splitStrategy = splitStrategy;
    }
}
