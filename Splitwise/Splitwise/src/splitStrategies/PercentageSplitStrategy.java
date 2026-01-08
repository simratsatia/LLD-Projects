package splitStrategies;

import entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy{
    private final Map<User, Integer> userShares;
    public PercentageSplitStrategy(Map<User, Integer> userShares){
        this.userShares = userShares;
    }
    @Override
    public Map<String, Integer> splitBetween(int amount, List<User> users) {
        Map<String, Integer> retval = new HashMap<>();
        for(User user: users){
            retval.put(user.id, this.userShares.get(user) * amount);
        }

        return retval;
    }
    
}
