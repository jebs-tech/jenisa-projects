package burhanfess.services;
import burhanfess.users.User;
import burhanfess.users.Admin;
import burhanfess.menfess.Menfess;
import burhanfess.repositories.UserRepository;
import burhanfess.repositories.UserRepositoryImpl;
import burhanfess.repositories.MenfessRepository;
import burhanfess.repositories.MenfessRepositoryImpl;
import burhanfess.exceptions.UsernameAlreadyExistsException;
import burhanfess.exceptions.UserNotFoundException;
import burhanfess.exceptions.SamePasswordException;
import burhanfess.exceptions.MenfessNotFoundException;
import java.util.List;
import java.util.Comparator;

public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;
    private MenfessRepository menfessRepository;
    
    public AdminServiceImpl() {
        this.userRepository = UserRepositoryImpl.getInstance();
        this.menfessRepository = MenfessRepositoryImpl.getInstance();
    }
    
    public AdminServiceImpl(UserRepository userRepository, MenfessRepository menfessRepository) {
        this.userRepository = userRepository;
        this.menfessRepository = menfessRepository;
    }
    
    @Override
    public List<User> getAllUsers(Comparator<User> comparator) {
        List<User> users = userRepository.getAllUsers();
        users.sort(comparator);
        return users;
    }
    
    @Override
    public void addAdmin(String username, String password) throws UsernameAlreadyExistsException {
        // Check if username already exists
        User existingUser = userRepository.getUserByUsername(username);
        if (existingUser != null) {
            throw new UsernameAlreadyExistsException("User dengan username '" + username + "' sudah ada");
        }
        
        // Add new admin
        Admin newAdmin = new Admin(username, password);
        userRepository.addUser(newAdmin);
    }
    
    @Override
    public void resetPassword(String username, String newPassword) throws UserNotFoundException, SamePasswordException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User dengan username '" + username + "' tidak ditemukan");
        }
        
        if (user.getPassword().equals(newPassword)) {
            throw new SamePasswordException("Password baru tidak boleh sama dengan password lama");
        }
        
        userRepository.changePassword(user, newPassword);
    }
    
    @Override
    public List<Menfess> getAllHiddenMenfesses() {
        return menfessRepository.getAllHiddenMenfesses();
    }
    
    @Override
    public List<Menfess> getAllUnhiddenMenfesses() {
        return menfessRepository.getAllUnhiddenMenfesses();
    }
    
    @Override
    public void hideMenfess(int menfessId) throws MenfessNotFoundException {
        List<Menfess> allMenfesses = menfessRepository.getAllUnhiddenMenfesses();
        Menfess targetMenfess = allMenfesses.stream()
                .filter(m -> m.getId() == menfessId)
                .findFirst()
                .orElse(null);
        
        if (targetMenfess == null) {
            throw new MenfessNotFoundException("Menfess dengan ID " + menfessId + " tidak ditemukan");
        }
        
        menfessRepository.hideMenfess(targetMenfess);
    }
    
    @Override
    public void unhideMenfess(int menfessId) throws MenfessNotFoundException {
        List<Menfess> allMenfesses = menfessRepository.getAllHiddenMenfesses();
        Menfess targetMenfess = allMenfesses.stream()
                .filter(m -> m.getId() == menfessId)
                .findFirst()
                .orElse(null);
        
        if (targetMenfess == null) {
            throw new MenfessNotFoundException("Menfess dengan ID " + menfessId + " tidak ditemukan");
        }
        
        menfessRepository.unhideMenfess(targetMenfess);
    }
    
    @Override
    public void logout() {
        // Logout logic handled by display
    }
    
    // Getters and Setters
    public UserRepository getUserRepository() { return userRepository; }
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }
    
    public MenfessRepository getMenfessRepository() { return menfessRepository; }
    public void setMenfessRepository(MenfessRepository menfessRepository) { this.menfessRepository = menfessRepository; }
}
