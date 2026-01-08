package entities;

public class Cell {
    int num;
    Player player;
    public Cell(int num){
        this.num = num;
        this.player = null;
    }

    public int getNum(){
        return this.num;
    }

    public void addPlayer(Player p){
        this.player = p;
    }

    public Player getPlayer(){
        return this.player;
    }
}

