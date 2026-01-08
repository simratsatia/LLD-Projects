package repositories;

import entities.Cell;
import entities.Player;

public interface BoardRepository {
    public int makeAMove(Player p, int numOnDie);
    public int getPlayerPosition(Player p);
    public int getBoardSize();
    public Cell[][] getBoard();
}
