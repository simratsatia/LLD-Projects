# Tic Tac Toe - Low Level Design Practice

A Java implementation of the classic Tic Tac Toe game, built to demonstrate clean architecture principles and low-level system design patterns.

## What This Project Is About

This is a console-based Tic Tac Toe game developed as a practice project for learning and demonstrating Low Level Design (LLD) concepts. The project showcases how to structure a simple game application using proper software engineering principles, design patterns, and architectural layers.

## What It Does

The application provides a two-player Tic Tac Toe game with the following features:

- **3x3 Game Board**: Classic tic-tac-toe grid where players take turns
- **Turn-Based Gameplay**: Players alternate placing X and O pieces
- **Win Detection**: Automatically detects when a player wins by completing a row, column, or diagonal
- **Draw Detection**: Recognizes when the board is full with no winner
- **Move Validation**: Prevents players from placing pieces on occupied cells
- **Console Interface**: Text-based UI displaying the board and accepting user input

## Project Structure

The codebase follows a layered architecture with clear separation of concerns:

```
src/
├── App.java                          # Main entry point and Controller
├── entities/                         # Domain models
│   ├── Piece.java                    # Represents a game piece (X, O, or EMPTY)
│   ├── PieceFactory.java             # Factory for creating Piece instances
│   ├── PieceType.java                # Enum defining piece types
│   └── Player.java                   # Represents a player with name and piece
├── models/                           # Data transfer objects
│   ├── MoveRequest.java              # Encapsulates move input data
│   └── MoveResponse.java             # Encapsulates move execution results
├── repositories/                     # Data access layer
│   ├── Repository.java               # Abstract base class for board operations
│   └── InMemoryBoardGame.java        # In-memory board implementation
└── usecases/                         # Business logic layer
    ├── MakeAMoveUseCase.java         # Handles move execution and validation
    └── DrawTheBoardUseCase.java      # Renders the board to console
```

### Component Responsibilities

**Entity Layer** (`entities/`)
- Contains core domain models that represent the game's fundamental concepts
- `Player`: Associates a player name with their game piece
- `Piece`: Represents a single cell's state on the board
- `PieceType`: Enum defining X, O, and EMPTY states
- `PieceFactory`: Creates Piece objects using the Factory pattern

**Model Layer** (`models/`)
- Data Transfer Objects (DTOs) that carry data between layers
- `MoveRequest`: Bundles row, column, and player information for a move
- `MoveResponse`: Returns move result with validation status, winner info, and messages

**Repository Layer** (`repositories/`)
- Abstracts data storage and retrieval logic
- `Repository`: Defines the contract for board game operations
- `InMemoryBoardGame`: Implements game logic with a 2D array, including move validation and win/draw detection

**Use Case Layer** (`usecases/`)
- Encapsulates business logic as discrete operations
- `MakeAMoveUseCase`: Orchestrates move execution, win checking, and result formatting
- `DrawTheBoardUseCase`: Handles board visualization

**Presentation Layer** (`App.java`)
- `Controller`: Coordinates user input, use case execution, and output
- `main()`: Application entry point that wires up all dependencies

## Clean Code Patterns

This project demonstrates several clean code principles and design patterns:

### 1. Separation of Concerns
Each layer has a distinct responsibility:
- Entities: Domain knowledge
- Repositories: Data management
- Use Cases: Business rules
- Controller: User interaction

### 2. Design Patterns

**Factory Pattern** (`PieceFactory`)
- Centralizes object creation logic for Piece instances
- Makes it easy to modify piece creation without affecting client code

**Repository Pattern** (`Repository`, `InMemoryBoardGame`)
- Abstracts data storage details from business logic
- Allows swapping between in-memory and persistent storage implementations
- Provides a clean interface for board operations

**Use Case Pattern** (Clean Architecture)
- Each use case represents a single business operation
- Dependencies point inward toward entities (Dependency Inversion)
- Use cases are independent and reusable

**DTO Pattern** (`MoveRequest`, `MoveResponse`)
- Decouples internal data structures from external APIs
- Provides clear contracts for data exchange between layers

**Controller Pattern** (`App.Controller`)
- Centralizes application flow control
- Coordinates between user input and business logic

### 3. Dependency Inversion Principle
- `Repository` is abstract, allowing `InMemoryBoardGame` to implement details
- Use cases depend on abstractions (Repository interface) not concrete implementations
- Makes the codebase flexible and testable

### 4. Single Responsibility Principle
- Each class has one clear purpose:
  - `MakeAMoveUseCase`: Execute moves
  - `DrawTheBoardUseCase`: Render board
  - `InMemoryBoardGame`: Manage board state
  - `Controller`: Handle user interaction flow

### 5. Open/Closed Principle
- The game board size is configurable (3x3 by default)
- Win detection logic could be extended using Strategy pattern (noted in TODO comment at InMemoryBoardGame.java:38)
- New piece types can be added to `PieceType` enum without modifying existing code

### 6. Clean Code Practices
- Meaningful variable and method names (e.g., `hasPlayerWon`, `makeMove`)
- Small, focused methods
- Proper encapsulation with private fields and public methods
- Exception handling with meaningful error messages

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A terminal or command prompt

### Compilation

Navigate to the project root directory and compile all source files:

```bash
cd "Tic Tac Toe"
javac -d bin -sourcepath src src/App.java
```

### Execution

Run the application from the project root:

```bash
java -cp bin App
```

### Gameplay Instructions

1. The game starts with an empty 3x3 board
2. Player 1 (X) goes first
3. When prompted, enter the row and column (0-2) separated by a space
   - Example: `0 0` places a piece in the top-left corner
   - Example: `1 1` places a piece in the center
   - Example: `2 2` places a piece in the bottom-right corner
4. The board updates and displays after each move
5. Player 2 (O) takes their turn using the same input format
6. The game continues until:
   - A player wins (three in a row horizontally, vertically, or diagonally)
   - The board is full (draw)
7. The game ends automatically and displays the result

### Board Coordinate System

```
     0   1   2
   +---+---+---+
0  |   |   |   |
   +---+---+---+
1  |   |   |   |
   +---+---+---+
2  |   |   |   |
   +---+---+---+
```

## Technologies Used

- **Language**: Java
- **Build Tool**: None (manual compilation with javac)
- **IDE**: Visual Studio Code (with Java extensions)
- **Version Control**: Git

## Future Enhancements (Potential)

The codebase includes a TODO comment suggesting the use of Strategy pattern for win condition logic (InMemoryBoardGame.java:38), which would allow different game modes (e.g., 4x4 board with different win conditions).

Other possible enhancements:
- Add input validation for out-of-bounds coordinates
- Implement undo/redo functionality
- Add AI opponent with difficulty levels
- Persistent game state (save/load games)
- Multiplayer over network
