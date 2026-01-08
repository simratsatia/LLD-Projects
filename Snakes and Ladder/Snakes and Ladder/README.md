# Snakes and Ladder - Low Level Design Practice

## Project Overview

This is a Java-based implementation of the classic Snakes and Ladder board game, designed as a practice project for Low Level Design (LLD) principles. The project demonstrates Object-Oriented Programming concepts, design patterns, and clean architecture practices commonly used in system design interviews.

## What It Does

The application simulates a Snakes and Ladder game where:
- Players can roll a die to move across a board
- Landing on a snake cell moves the player down to a lower position
- Landing on a ladder cell moves the player up to a higher position
- Landing on a generic cell keeps the player at that position
- The game tracks player positions and determines when a player wins (reaches the final cell)
- The board state can be displayed to show current player positions

## Project Structure

The codebase follows Clean Architecture principles with clear separation of concerns:

```
src/
├── App.java                          # Entry point of the application
├── entities/                         # Core business entities
│   ├── Cell.java                     # Base class for all board cells
│   ├── GenericCell.java              # Regular cells with no special behavior
│   ├── SnakeCell.java                # Snake cells that move players down
│   ├── LadderCell.java               # Ladder cells that move players up
│   ├── Player.java                   # Player entity with id and name
│   └── Die.java                      # Die entity for rolling random numbers
├── models/                           # Data transfer objects
│   ├── RollADieRequestModel.java     # Request model for rolling die
│   └── RollADieResponseModel.java    # Response model with position and win status
├── usecases/                         # Business logic / Use cases
│   ├── RollADieUseCase.java          # Handles die rolling and player movement
│   └── DisplayTheBoardUseCase.java   # Displays the current board state
└── repositories/                     # Data access layer
    └── BoardRepository.java          # Interface for board operations
```

### Layer Responsibilities

**Entities Layer**: Contains core domain objects that encapsulate business rules
- `Cell` hierarchy uses inheritance to model different cell types
- `Player` represents game participants
- `Die` encapsulates random number generation logic

**Models Layer**: Data Transfer Objects (DTOs) that carry data between layers
- Separates internal entities from external contracts
- Request/Response models for use case communication

**Use Cases Layer**: Application business rules and orchestration
- Each use case represents a specific user action
- Depends on repository interfaces, not implementations (Dependency Inversion)
- Returns response models instead of raw data

**Repositories Layer**: Data access abstractions
- Defines interfaces for board operations
- Decouples business logic from data storage details

## Clean Code Patterns

### 1. **Single Responsibility Principle (SRP)**
Each class has one reason to change:
- `Die` only handles rolling dice
- `Player` only stores player information
- `RollADieUseCase` only handles the die rolling workflow

### 2. **Inheritance & Polymorphism**
Cell hierarchy demonstrates proper inheritance:
```
Cell (base)
├── GenericCell
├── SnakeCell
└── LadderCell
```
Different cell types extend the base `Cell` class, allowing polymorphic behavior.

### 3. **Dependency Inversion Principle (DIP)**
Use cases depend on the `BoardRepository` interface, not concrete implementations. This allows for:
- Easy testing with mock repositories
- Swapping implementations without changing business logic

### 4. **Separation of Concerns**
Clear boundaries between:
- Domain logic (entities)
- Application logic (use cases)
- Data access (repositories)
- Data transfer (models)

### 5. **Clean Architecture**
Dependencies flow inward:
```
App → Use Cases → Repositories (Interface) ← Repositories (Implementation)
         ↓
     Entities
```
Inner layers don't depend on outer layers.

### 6. **Request-Response Pattern**
Use cases communicate through well-defined request and response models:
- `RollADieRequestModel` → `RollADieUseCase` → `RollADieResponseModel`

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Java compiler (javac)

### Compilation

From the project root directory:

```bash
javac -d bin src/**/*.java src/*.java
```

This compiles all Java files and places the `.class` files in the `bin` directory.

### Execution

Run the application:

```bash
java -cp bin App
```

### Using an IDE

If using VS Code with Java extensions:
1. Open the project folder in VS Code
2. The Java extension will auto-detect the project structure (configured in `.vscode/settings.json`)
3. Press F5 or use the "Run" button to execute `App.java`

## Design Patterns Used

1. **Repository Pattern**: `BoardRepository` interface abstracts data access
2. **Factory Pattern**: Cell types can be created based on requirements
3. **Strategy Pattern**: Different cell behaviors through inheritance
4. **Command Pattern**: Use cases act as commands encapsulating requests

## Future Enhancements

- Implement concrete `BoardRepository` (e.g., `InMemoryBoardRepository`)
- Add game initialization logic in `App.java`
- Implement turn-based gameplay loop
- Add unit tests for use cases
- Add multiple player support
- Implement game state persistence

## Learning Objectives

This project demonstrates:
- Clean Architecture principles
- SOLID principles in Java
- Design patterns for game development
- Separation between business logic and infrastructure
- Test-friendly architecture through dependency inversion
