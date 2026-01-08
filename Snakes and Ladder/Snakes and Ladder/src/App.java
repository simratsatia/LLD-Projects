import entities.Die;
import entities.Player;
import models.RollADieRequestModel;
import models.RollADieResponseModel;
import repositories.BoardRepository;
import repositories.InMemoryBoardRepository;
import usecases.DisplayTheBoardUseCase;
import usecases.RollADieUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Welcome to Snakes and Ladders ===\n");

        // Initialize board (10x10 = 100 cells)
        InMemoryBoardRepository boardRepository = new InMemoryBoardRepository(10, 10);

        // Add snakes (from -> to)
        boardRepository.addSnake(99, 54);
        boardRepository.addSnake(70, 55);
        boardRepository.addSnake(52, 42);
        boardRepository.addSnake(25, 2);
        boardRepository.addSnake(95, 72);

        // Add ladders (from -> to)
        boardRepository.addLadder(6, 25);
        boardRepository.addLadder(11, 40);
        boardRepository.addLadder(60, 85);
        boardRepository.addLadder(46, 90);
        boardRepository.addLadder(17, 69);

        // Initialize die
        Die die = new Die(6);

        // Initialize use cases
        RollADieUseCase rollADieUseCase = new RollADieUseCase(boardRepository, die);
        DisplayTheBoardUseCase displayTheBoardUseCase = new DisplayTheBoardUseCase(boardRepository);

        // Create players
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Invalid number of players. Setting to 2.");
            numPlayers = 2;
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            Player player = new Player("P" + (i + 1), name);
            players.add(player);
            boardRepository.addPlayer(player);
        }

        System.out.println("\n=== Game Started ===");
        System.out.println("Board: 10x10 (100 cells)");
        System.out.println("Snakes at: 99->54, 70->55, 52->42, 25->2, 95->72");
        System.out.println("Ladders at: 6->25, 11->40, 60->85, 46->90, 17->69\n");

        // Game loop
        boolean gameWon = false;
        int currentPlayerIndex = 0;

        while (!gameWon) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn (Position: "
                + boardRepository.getPlayerPosition(currentPlayer) + ")");
            System.out.print("Press Enter to roll the die...");
            scanner.nextLine();

            // Roll die and make move
            RollADieRequestModel request = new RollADieRequestModel(currentPlayer);
            RollADieResponseModel response = rollADieUseCase.execute(request);

            System.out.println(currentPlayer.getName() + " rolled and moved to position: " + response.position);

            // Check if player won
            if (response.hasWon) {
                System.out.println("\n*** " + currentPlayer.getName() + " has WON the game! ***");
                gameWon = true;
            } else {
                // Move to next player
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }

            // Display positions of all players
            System.out.println("\nCurrent Positions:");
            for (Player p : players) {
                System.out.println("  " + p.getName() + ": " + boardRepository.getPlayerPosition(p));
            }
        }

        System.out.println("\n=== Game Over ===");
        scanner.close();
    }
}
