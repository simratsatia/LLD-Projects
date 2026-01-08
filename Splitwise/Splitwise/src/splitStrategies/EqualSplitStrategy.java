package splitStrategies;

import entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    public Map<String, Integer> splitBetween (int amount, List<User> users){
        Map<String, Integer> retval = new HashMap<>();
        int splitAmount = amount / users.size();
        for (User user: users){
            retval.put(user.id, splitAmount);
        }
        return retval;
    }
}
