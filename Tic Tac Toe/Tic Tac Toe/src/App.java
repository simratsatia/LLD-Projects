
import entities.Piece;
import entities.PieceFactory;
import entities.PieceType;
import entities.Player;
import java.util.Scanner;
import models.MoveRequest;
import models.MoveResponse;
import repositories.InMemoryBoardGame;
import repositories.Repository;
import usecases.DrawTheBoardUseCase;
import usecases.MakeAMoveUseCase;

public class App {

    public static class Controller {
        private MakeAMoveUseCase makeAMoveUseCase;
        private DrawTheBoardUseCase drawTheBoardUseCase;

        public Controller(MakeAMoveUseCase makeAMoveUseCase, DrawTheBoardUseCase drawTheBoardUseCase) {
            this.makeAMoveUseCase = makeAMoveUseCase;
            this.drawTheBoardUseCase = drawTheBoardUseCase;
        }

        public void makeMove(int row, int col, Player player) {
            MoveRequest request = new MoveRequest(row, col, player);
            MoveResponse response = this.makeAMoveUseCase.execute(request);
            // check if player won
            if (response.winner != null) {
                System.out.println(response.message);
                endGame();

            }

            // check if game ended in a draw
            if (response.hasGameEnded) {
                System.out.println(response.message);
                endGame();
            }

            // check if move was successful and game still ongoing
            if (response.isValid) {
                System.out.println(response.message);
            }

            // check if move was invalid
            if (!response.isValid) {
                System.out.println(response.message);
            }
        }

        public void drawBoard() {
            this.drawTheBoardUseCase.execute();
        }

        private void endGame() {
            this.drawBoard();
            System.exit(0);
        }

    }

    private static int[] getValidatedInput(Scanner scanner, String playerName) {
        while (true) {
            try {
                System.out.println(playerName + ", enter row and column (0-2) separated by space, or type 'quit' to exit");
                String input = scanner.nextLine().trim();

                // Check for quit command
                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("Game ended by user. Thanks for playing!");
                    scanner.close();
                    System.exit(0);
                }

                // Parse input
                String[] parts = input.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Invalid input. Please enter exactly two numbers separated by space.");
                    continue;
                }

                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                // Validate bounds
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid coordinates. Row and column must be between 0 and 2.");
                    continue;
                }

                return new int[]{row, col};

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid integers.");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // create piece
        Piece x = PieceFactory.createPiece(PieceType.X);
        Piece o = PieceFactory.createPiece(PieceType.O);

        // create players
        Player player1 = new Player("Player 1", x);
        Player player2 = new Player("Player 2", o);

        // create repository
        Repository repository = new InMemoryBoardGame(3);

        // create usecases
        MakeAMoveUseCase makeAMoveUseCase = new MakeAMoveUseCase(repository);
        DrawTheBoardUseCase drawTheBoardUseCase = new DrawTheBoardUseCase(repository);

        // create controller
        Controller controller = new Controller(makeAMoveUseCase, drawTheBoardUseCase);

        // input scanner
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                controller.drawBoard();
                int[] move1 = getValidatedInput(scanner, "Player 1");
                controller.makeMove(move1[0], move1[1], player1);

                controller.drawBoard();
                int[] move2 = getValidatedInput(scanner, "Player 2");
                controller.makeMove(move2[0], move2[1], player2);
            }
        } finally {
            scanner.close();
        }

    }
}
