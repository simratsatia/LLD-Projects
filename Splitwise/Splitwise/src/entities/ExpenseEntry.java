package entities;

public class ExpenseEntry{
    public Expense expense;
    public int amount;
    public ExpenseEntry(Expense expense, int amount){
        this.expense = expense;
        this.amount = amount;
    }
}