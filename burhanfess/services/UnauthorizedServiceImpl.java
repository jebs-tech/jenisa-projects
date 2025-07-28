package burhanfess.services;
import burhanfess.exceptions.InvalidInputException;
import burhanfess.exceptions.UsernameAlreadyExistsException;
import burhanfess.repositories.UserRepository;
import burhanfess.repositories.UserRepositoryImpl;
import burhanfess.users.Cosmic;
import burhanfess.users.User;

public class UnauthorizedServiceImpl implements UnauthorizedService {
    private UserRepository userRepository;
    
    public UnauthorizedServiceImpl() {
        this.userRepository = UserRepositoryImpl.getInstance();
    }
    
    @Override
    public void register(String username, String password) throws UsernameAlreadyExistsException {
        // Check if username already exists
        User existingUser = userRepository.getUserByUsername(username);
        if (existingUser != null) {
            throw new UsernameAlreadyExistsException("User dengan username '" + username + "' sudah ada");
        }
        
        // Add new cosmic user
        Cosmic newUser = new Cosmic(username, password);
        userRepository.addUser(newUser);
    }
    
    @Override
    public User login(String username, String password) throws InvalidInputException {
        User user = userRepository.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidInputException("Username atau password salah");
        }
        return user;
    }
    
    @Override
    public void exit() {
        // Exit logic handled by display
    }
    
    // Getters and Setters
    public UserRepository getUserRepository() { return userRepository; }
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }
}
