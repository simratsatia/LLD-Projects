package models;

import entities.Player;

public class MoveResponse {
    public boolean isValid;
    public Player winner;
    public boolean hasGameEnded;
    public String message;
    public MoveResponse(boolean isValid, Player winner, boolean hasGameEnded, String message) {
        this.isValid = isValid;
        this.winner = winner;
        this.hasGameEnded = hasGameEnded;
        this.message = message;
    }
}
