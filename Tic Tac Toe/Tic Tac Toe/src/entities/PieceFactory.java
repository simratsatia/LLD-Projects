package entities;

public class PieceFactory {
    public static Piece createPiece(PieceType pieceType){
        switch(pieceType){
            case X:
                return new Piece(PieceType.X);
            case O:
                return new Piece(PieceType.O);
            case EMPTY:
                return new Piece(PieceType.EMPTY);
        }
        return null;
    }
}
