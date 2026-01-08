package splitStrategies;
import entities.User;
import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    public Map<String,Integer>  splitBetween(int amount, List<User> users);
}
