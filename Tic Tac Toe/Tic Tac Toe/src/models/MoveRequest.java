package models;

import entities.Player;

public class MoveRequest {
    public int row;
    public int col;
    public Player player;
    public MoveRequest(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }
}
