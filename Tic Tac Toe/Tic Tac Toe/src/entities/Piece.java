package entities;

public class Piece {
    PieceType pieceType;
    public Piece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }
}
