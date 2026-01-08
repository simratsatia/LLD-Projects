package entities;

import java.util.Random;

public class Die {
    int limit;
    Random rand;
    public Die(int limit){
        this.limit = limit;
        rand = new Random();
    }

    public int rollDie(){
        return this.rand.nextInt(this.limit) + 1;
    }
}
