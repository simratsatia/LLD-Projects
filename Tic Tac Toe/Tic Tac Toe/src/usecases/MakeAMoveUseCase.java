package usecases;

import models.MoveRequest;
import models.MoveResponse;
import repositories.Repository;

public class MakeAMoveUseCase {
    Repository repository;
    public MakeAMoveUseCase(Repository repository){
        this.repository = repository;
    }

    public MoveResponse execute(MoveRequest request){
        try {
            this.repository.makeMove(request.row, request.col, request.player);
            if(this.repository.hasPlayerWon(request.player)){
                return new MoveResponse(true, request.player, true, "Player: " + request.player.getName() + " has won");
            }
            if (this.repository.hasGameEnded(request.player)){
                return new MoveResponse(true, null, true, "Game has ended in a draw");
            }
            return new MoveResponse(true, null, false, "Move successful");
            
        } catch (Exception e) {
            return new MoveResponse(false, null, false, e.getMessage());
        }
    }
}
