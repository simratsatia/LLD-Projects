package repositories;

import entities.Piece;
import entities.PieceType;
import entities.Player;

public class InMemoryBoardGame extends Repository{
    Piece[][] board;

    public InMemoryBoardGame(int dimensions) {
        this.board = new Piece[dimensions][dimensions];
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                this.board[i][j] = new Piece(PieceType.EMPTY);
            }
        }
    }

    public void makeMove(int x, int y, Player player) throws Exception {
        if(this.board[x][y].getPieceType() != PieceType.EMPTY) {
            throw new Exception("Invalid Move");
        }
        this.board[x][y] = player.getPiece();
    }

    public boolean hasGameEnded(Player player){
        for (int i=0;i<this.board.length;i++){
            for (int j=0;j<this.board.length;j++){
                if(this.board[i][j].getPieceType() == PieceType.EMPTY){
                    return false;
                }
            }
        }

        return true;
    }

    //TODO: Can use strategy design pattern to determine how the logic of winning is determined
    public boolean hasPlayerWon(Player player){
        Piece piece = player.getPiece();
        //check all rows
        for (int i = 0; i < this.board.length; i++) {
            boolean hasWon = true;
            for (int j = 0; j < this.board.length; j++) {
                if(this.board[i][j].getPieceType() != piece.getPieceType()) {
                    hasWon = false;
                    break;
                }
            }
            if(hasWon) {
                return true;
            }
        }

        //check all columns
        for (int j=0;j<this.board.length;j++){
            boolean hasWon = true;
            for (int i=0;i<this.board.length;i++){
                if(this.board[i][j].getPieceType() != piece.getPieceType()){
                    hasWon = false;
                    break;
                }
            }
            if(hasWon){
                return true;
            }
        }

        //check diagonal from topLeft to BottomRight
        for (int i=0,j=0;i<this.board.length && j<this.board.length;i++,j++){
            if(this.board[i][j].getPieceType() != piece.getPieceType()){
                break;
            }
            if(i == this.board.length-1){
                return true;
            }
        }

        for (int i=0, j=this.board.length-1;i<this.board.length && j>=0;i++,j--){
            if(this.board[i][j].getPieceType() != piece.getPieceType()){
                break;
            }
            if(i == this.board.length-1){
                return true;
            }
        }

        return false;
    }


    public Piece[][] getBoard() {
        return this.board;
    }

    
}
