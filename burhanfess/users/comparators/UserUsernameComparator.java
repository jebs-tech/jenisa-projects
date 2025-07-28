package burhanfess.users.comparators;
import burhanfess.users.User;
import java.util.Comparator;

public class UserUsernameComparator implements Comparator<User> {
    
    public UserUsernameComparator() {}
    
    @Override
    public int compare(User user, User other) {
        if (user.getUsername() == null && other.getUsername() == null) return 0;
        if (user.getUsername() == null) return -1;
        if (other.getUsername() == null) return 1;
        return user.getUsername().compareTo(other.getUsername());
    }
}
