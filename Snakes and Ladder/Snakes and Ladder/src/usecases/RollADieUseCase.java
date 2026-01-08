package usecases;

import entities.Die;
import entities.Player;
import models.RollADieRequestModel;
import models.RollADieResponseModel;
import repositories.BoardRepository;

public class RollADieUseCase {
    private BoardRepository repository;
    private Die die;
    public RollADieUseCase(BoardRepository repository, Die die){
        this.repository = repository;
        this.die = die;
    }

    public RollADieResponseModel execute(RollADieRequestModel request){
        Player player = request.player;
        int numOnDie = this.die.rollDie();
        int position = repository.makeAMove(player, numOnDie);
        if (position == this.repository.getBoardSize()){
            return new RollADieResponseModel(position, true);
        }
        return new RollADieResponseModel(position, false);
    }
}
