package models;

import entities.Player;

public class RollADieRequestModel {
    public Player player;
    public RollADieRequestModel(Player p){
        this.player = p;
    }
}
