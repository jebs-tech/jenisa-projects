package burhanfess.users.comparators;
import burhanfess.users.User;
import java.util.Comparator;

public class UserIdComparator implements Comparator<User> {
    
    public UserIdComparator() {}
    
    @Override
    public int compare(User user, User other) {
        return Integer.compare(user.getId(), other.getId());
    }
}