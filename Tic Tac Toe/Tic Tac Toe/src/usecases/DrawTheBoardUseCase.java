package usecases;

import entities.Piece;
import repositories.Repository;

public class DrawTheBoardUseCase {
    Repository repository;
    public DrawTheBoardUseCase(Repository repository){
        this.repository = repository;
    }

    public void execute(){
        Piece[][] board = this.repository.getBoard();
        System.out.println();
        for(int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){  
                System.out.print("| " + board[i][j].getPieceType().toString() + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    
}
