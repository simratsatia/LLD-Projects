package usecases;

import entities.Cell;
import repositories.BoardRepository;

public class DisplayTheBoardUseCase {
    private BoardRepository repository;

    public DisplayTheBoardUseCase(BoardRepository repository) {
        this.repository = repository;
    }

    public void execute(){
        Cell[][] board = this.repository.getBoard();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                String data = board[i][j].getPlayer() == null ? "[ ]" : "[" + board[i][j].getPlayer().getName() + "]";
                System.out.print(data + " ");
            }
            System.out.println();
        }

    }
}
