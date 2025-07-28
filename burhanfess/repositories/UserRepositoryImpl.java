package burhanfess.repositories;
import burhanfess.users.User;
import burhanfess.users.Admin;
import burhanfess.users.Cosmic;
import java.util.List;
import java.util.ArrayList;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users;
    private static UserRepository userRepository;
    
    private UserRepositoryImpl() {
        this.users = new ArrayList<>();
        // Add default admin
        users.add(new Admin("admin", "admin"));
    }
    
    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }
    
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public void addUser(User user) {
        users.add(user);
    }
    
    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }
    
    // Getters and Setters
    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }
}