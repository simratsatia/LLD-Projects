package repositories;

import entities.Piece;
import entities.Player;

public abstract class Repository {

    public abstract void makeMove(int x, int y, Player player) throws Exception;

    public abstract boolean hasGameEnded(Player player);

    public abstract boolean hasPlayerWon(Player player);

    public abstract Piece[][] getBoard();
}