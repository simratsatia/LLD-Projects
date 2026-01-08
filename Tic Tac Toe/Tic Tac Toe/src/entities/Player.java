package entities;

public class Player {
    String name;
    Piece piece;
    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public String getName() {
        return this.name;
    }

    public Piece getPiece() {
        return this.piece;
    }

}
