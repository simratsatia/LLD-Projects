# Splitwise - Low Level Design Implementation

A Java-based implementation of Splitwise expense-sharing application demonstrating clean architecture principles and design patterns. This project was created as a practice exercise for Low-Level System Design (LLD) interviews.

## Table of Contents
- [What is this Project?](#what-is-this-project)
- [Features](#features)
- [Project Structure](#project-structure)
- [Clean Code Patterns](#clean-code-patterns)
- [How to Run](#how-to-run)
- [Example Usage](#example-usage)

## What is this Project?

This is a command-line implementation of a Splitwise-like expense management system. Splitwise is a popular application that helps groups of people track shared expenses and settle debts efficiently. This implementation showcases:

- Object-oriented design principles
- Common design patterns (Strategy, Repository, Use Case)
- Clean architecture with separation of concerns
- SOLID principles in practice
- Dependency injection for testability

## Features

The application supports the following operations:

1. **User Management**
   - Create and manage users
   - Organize users into groups

2. **Expense Management**
   - Add expenses between friends
   - Add expenses for groups
   - Track who paid and who owes money
   - Support multiple expense types (FOOD, DRINKS, HOME, FUEL, HEALTH, OTHER)

3. **Expense Splitting Strategies**
   - **Equal Split**: Divide expenses equally among all participants
   - **Percentage Split**: Divide expenses based on predefined percentages
   - Extensible design allows easy addition of new strategies

4. **Expense Tracking**
   - View expense sheets showing:
     - Expenses paid by a user
     - Money owed to others
   - Track outstanding balances

5. **Settlement**
   - Mark expenses as settled between users
   - Update balance sheets accordingly

## Project Structure

```
src/
├── App.java                          # Main entry point with CLIController
│
├── entities/                         # Domain models
│   ├── User.java                     # User entity with id and name
│   ├── Group.java                    # Group entity containing users
│   ├── Expense.java                  # Expense with amount, payer, and split strategy
│   ├── ExpenseEntry.java             # Links expense to amount owed
│   └── ExpenseType.java              # Enum for expense categories
│
├── repositories/                     # Data access layer (Repository Pattern)
│   ├── UserManagementRepository.java # Interface for user/group operations
│   ├── InMemoryUserManagementRepository.java
│   ├── ExpenseManagementRepository.java # Interface for expense operations
│   └── InMemoryExpenseManagementRepository.java
│
├── splitStrategies/                  # Expense splitting algorithms (Strategy Pattern)
│   ├── SplitStrategy.java            # Strategy interface
│   ├── EqualSplitStrategy.java       # Equal division implementation
│   └── PercentageSplitStrategy.java  # Percentage-based implementation
│
└── usecases/                         # Business logic orchestration (Use Case Pattern)
    ├── AddExpenseByFriendUseCase.java
    ├── AddExpenseToGroupUseCase.java
    ├── AddFriendToGroupUseCase.java
    ├── GetExpenseSheetOfUserUseCase.java
    └── SettleExpenseUseCase.java
```

### Architecture Layers

The project follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────┐
│    App.java (CLI Controller)        │  ← Presentation Layer
├─────────────────────────────────────┤
│    Use Cases                        │  ← Business Logic Layer
├─────────────────────────────────────┤
│    Repositories + Split Strategies  │  ← Data & Algorithm Layer
├─────────────────────────────────────┤
│    Entities (Domain Models)         │  ← Domain Layer
└─────────────────────────────────────┘
```

## Clean Code Patterns

This implementation demonstrates several software engineering best practices:

### 1. SOLID Principles

#### Single Responsibility Principle (SRP)
- Each class has one reason to change
- `User`, `Group`, `Expense`: Represent single domain concepts
- `EqualSplitStrategy`: Only handles equal splitting logic
- Each use case handles one specific workflow

#### Open/Closed Principle (OCP)
- **Open for extension**: New split strategies can be added without modifying existing code
- **Closed for modification**: Adding `CustomSplitStrategy` doesn't require changes to `Expense` or use cases
- New repository implementations (e.g., database-backed) can be added without changing use cases

#### Liskov Substitution Principle (LSP)
- All `SplitStrategy` implementations are interchangeable
- All repository implementations can be substituted without breaking functionality
- `Expense` works with any `SplitStrategy` implementation

#### Interface Segregation Principle (ISP)
- Focused interfaces: `UserManagementRepository` and `ExpenseManagementRepository` are separate
- `SplitStrategy` has a single method: `splitBetween()`
- Clients only depend on interfaces they use

#### Dependency Inversion Principle (DIP)
- High-level modules (use cases) depend on abstractions (repository interfaces)
- Low-level modules (concrete repositories) implement those abstractions
- Dependencies are injected, not created internally

### 2. Design Patterns

#### Strategy Pattern
**Location**: `splitStrategies/` package

Encapsulates different expense-splitting algorithms, allowing runtime selection:

```java
// In Expense.java
public SplitStrategy splitStrategy;

// Usage in use case
Map<String, Integer> splits = expense.splitStrategy.splitBetween(
    expense.amount,
    expense.usersInvolved
);
```

**Benefits**:
- Easy to add new splitting strategies
- Behavior can be changed at runtime
- Each strategy is independently testable

#### Repository Pattern
**Location**: `repositories/` package

Abstracts data persistence, enabling in-memory or database implementations:

```java
// Use cases depend on interfaces
public class AddExpenseByFriendUseCase {
    private ExpenseManagementRepository expenseRepo;
    private UserManagementRepository userRepo;
}
```

**Benefits**:
- Data access logic is centralized
- Easy to swap persistence mechanisms
- Simplifies testing with mock repositories

#### Use Case Pattern (Clean Architecture)
**Location**: `usecases/` package

Each use case encapsulates a single business workflow:

```java
public class AddExpenseByFriendUseCase {
    public void execute(Expense expense) {
        // 1. Split the expense using strategy
        // 2. Store in repository
    }
}
```

**Benefits**:
- Business logic is isolated from presentation and data layers
- Each workflow is independently testable
- Clear entry points for business operations

#### Dependency Injection
All dependencies are constructor-injected:

```java
public AddExpenseByFriendUseCase(
    ExpenseManagementRepository expenseRepo,
    UserManagementRepository userRepo) {
    this.expenseRepo = expenseRepo;
    this.userRepo = userRepo;
}
```

**Benefits**:
- Loose coupling between components
- Easier to test with mock objects
- Clear declaration of dependencies

### 3. Other Clean Code Practices

- **Separation of Concerns**: Business logic, data access, and presentation are clearly separated
- **Clear Package Structure**: Organized by responsibility (entities, repositories, use cases, strategies)
- **Consistent Naming**: Descriptive class and method names that reveal intent
- **Interface-Based Design**: Program to interfaces, not implementations

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt

### Compilation

1. Navigate to the project directory:
```bash
cd Splitwise
```

2. Compile the Java source files:
```bash
javac -d bin src/**/*.java src/*.java
```

This creates compiled `.class` files in the `bin/` directory.

### Execution

Run the compiled application:
```bash
java -cp bin App
```

### Expected Output

```
You paid 33 for Food expense
You need to get 11 from u3
You need to get 11 from u2
You paid 33 for Food expense
```

This output demonstrates:
1. User1 paid 100 rupees for a food expense split among 3 people (u1, u2, u3)
2. User1 paid their share (33) and needs to collect 11 from u2 and 11 from u3
3. After settling with u2, the second output shows u2 is no longer listed

## Example Usage

The main method in `App.java` demonstrates a typical workflow:

```java
// 1. Create users
User user1 = new User("1", "u1");
User user2 = new User("2", "u2");
User user3 = new User("3", "u3");

// 2. Create a group
HashSet<User> groupUsers = new HashSet<>();
groupUsers.add(user1);
groupUsers.add(user2);
groupUsers.add(user3);
Group group1 = new Group("1", "g1", groupUsers);

// 3. Create repositories
UserManagementRepository userRepo = new InMemoryUserManagementRepository();
ExpenseManagementRepository expenseRepo = new InMemoryExpenseManagementRepository();

// 4. Register users
userRepo.addUser(user1);
userRepo.addUser(user2);
userRepo.addUser(user3);

// 5. Create expense with equal split strategy
SplitStrategy strategy = new EqualSplitStrategy();
Expense expense = new Expense(
    "1",
    "Food expense",
    100,
    ExpenseType.FOOD,
    user1,  // payer
    new ArrayList<>(groupUsers),  // participants
    strategy
);

// 6. Initialize use cases with dependency injection
AddExpenseByFriendUseCase addExpense =
    new AddExpenseByFriendUseCase(expenseRepo, userRepo);
GetExpenseSheetOfUserUseCase getExpenseSheet =
    new GetExpenseSheetOfUserUseCase(expenseRepo, userRepo);
SettleExpenseUseCase settleExpense =
    new SettleExpenseUseCase(expenseRepo);

// 7. Execute operations via CLI controller
CLIController controller = new App().new CLIController(
    addExpense, addExpenseToGroup, addFriendToGroup,
    getExpenseSheet, settleExpense
);

controller.addExpenseByFriend(expense);
controller.getExpenseSheetOfUser(user1);
controller.settleExpense(expense, user2);
controller.getExpenseSheetOfUser(user1);
```

## Extending the Application

### Adding a New Split Strategy

1. Create a new class implementing `SplitStrategy`:
```java
public class CustomSplitStrategy implements SplitStrategy {
    @Override
    public Map<String, Integer> splitBetween(int amount, List<User> users) {
        // Your custom logic here
    }
}
```

2. Use it when creating an expense:
```java
SplitStrategy customStrategy = new CustomSplitStrategy();
Expense expense = new Expense(..., customStrategy);
```

### Adding Database Persistence

1. Create a new repository implementation:
```java
public class DatabaseUserManagementRepository
    implements UserManagementRepository {
    // Implement methods using database operations
}
```

2. Inject it into use cases:
```java
UserManagementRepository repo = new DatabaseUserManagementRepository();
AddExpenseByFriendUseCase useCase = new AddExpenseByFriendUseCase(..., repo);
```

## Future Enhancements

Potential improvements for this project:
- Add validation and error handling
- Implement `equals()` and `hashCode()` for entities
- Add unit tests for all components
- Support for exact amount splits (custom per-user amounts)
- Simplify settlements (minimize number of transactions)
- Add persistent storage (database integration)
- Implement a REST API or web interface
- Add expense editing and deletion
- Support for multiple currencies
- Transaction history and audit logs

## Author

Created as a Low-Level Design practice project for interview preparation.

## License

This is a practice project and is free to use for educational purposes.
