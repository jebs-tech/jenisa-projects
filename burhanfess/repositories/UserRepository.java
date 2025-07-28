package burhanfess.repositories;
import burhanfess.users.User;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserByUsername(String username);
    void addUser(User user);
    void changePassword(User user, String newPassword);
}
