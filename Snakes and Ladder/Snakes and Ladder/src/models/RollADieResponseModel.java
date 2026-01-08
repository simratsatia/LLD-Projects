package models;

public class RollADieResponseModel {
    public int position;
    public boolean hasWon;
    public RollADieResponseModel(int position, boolean hasWon){
        this.position = position;
        this.hasWon = hasWon;
    }
}
