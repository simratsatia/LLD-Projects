package entities;

public class SnakeCell extends Cell{
    int target;
    public SnakeCell(int num, int target){
        super(num);
        this.target = target;
    }

    public int getTarget(){
        return this.target;
    }
}
