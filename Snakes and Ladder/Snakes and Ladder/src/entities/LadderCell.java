package entities;

public class LadderCell extends Cell{
    int target;
    public LadderCell(int num, int target){
        super(num);
        this.target = target;
    }

    public int getTarget(){
        return this.target;
    }
}
