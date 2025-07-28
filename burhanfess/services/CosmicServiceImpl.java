package burhanfess.services;
import burhanfess.users.User;
import burhanfess.users.Cosmic;
import burhanfess.menfess.*;
import burhanfess.repositories.UserRepository;
import burhanfess.repositories.UserRepositoryImpl;
import burhanfess.repositories.MenfessRepository;
import burhanfess.repositories.MenfessRepositoryImpl;
import burhanfess.exceptions.UserNotFoundException;
import burhanfess.exceptions.SamePasswordException;
import java.util.List;

public class CosmicServiceImpl implements CosmicService {
    private Cosmic cosmic;
    private UserRepository userRepository;
    private MenfessRepository menfessRepository;
    
    public CosmicServiceImpl(Cosmic cosmic) {
        this.cosmic = cosmic;
        this.userRepository = UserRepositoryImpl.getInstance();
        this.menfessRepository = MenfessRepositoryImpl.getInstance();
    }
    
    @Override
    public void sendCurhatFess(String content) {
        CurhatFess curhatFess = new CurhatFess(cosmic, content);
        menfessRepository.addMenfess(curhatFess);
    }
    
    @Override
    public void sendPromosiFess(String content) {
        PromosiFess promosiFess = new PromosiFess(cosmic, content);
        menfessRepository.addMenfess(promosiFess);
    }
    
    @Override
    public void sendConfessFess(String content, String receiverUsername) throws UserNotFoundException {
        User receiver = userRepository.getUserByUsername(receiverUsername);
        if (receiver == null) {
            throw new UserNotFoundException("User dengan username '" + receiverUsername + "' tidak ditemukan");
        }
        
        ConfessFess confessFess = new ConfessFess(cosmic, content, receiver);
        menfessRepository.addMenfess(confessFess);
    }
    
    @Override
    public List<Menfess> getAllUnhiddenMenfesses() {
        return menfessRepository.getAllUnhiddenMenfesses();
    }
    
    @Override
    public List<Menfess> getAllSentMenfesses() {
        return menfessRepository.getAllMenfessesByUser(cosmic);
    }
    
    @Override
    public List<Menfess> getAllReceivedMenfesses() {
        return menfessRepository.getAllMenfessesForUser(cosmic);
    }
    
    @Override
    public void changePassword(String newPassword) throws SamePasswordException {
        if (cosmic.getPassword().equals(newPassword)) {
            throw new SamePasswordException("Password yang dimasukkan tidak boleh sama dengan password sebelumnya");
        }
        userRepository.changePassword(cosmic, newPassword);
    }
    
    @Override
    public void logout() {
        // Logout logic will be handled by display
    }
    
    // Getters and Setters
    public Cosmic getCosmic() { return cosmic; }
    public void setCosmic(Cosmic cosmic) { this.cosmic = cosmic; }
}
