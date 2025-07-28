package burhanfess.services;
import burhanfess.users.User;
import burhanfess.menfess.Menfess;
import burhanfess.exceptions.UsernameAlreadyExistsException;
import burhanfess.exceptions.UserNotFoundException;
import burhanfess.exceptions.SamePasswordException;
import burhanfess.exceptions.MenfessNotFoundException;
import java.util.List;
import java.util.Comparator;

public interface AdminService {
    List<User> getAllUsers(Comparator<User> comparator);
    void addAdmin(String username, String password) throws UsernameAlreadyExistsException;
    void resetPassword(String username, String newPassword) throws UserNotFoundException, SamePasswordException;
    List<Menfess> getAllHiddenMenfesses();
    List<Menfess> getAllUnhiddenMenfesses();
    void hideMenfess(int menfessId) throws MenfessNotFoundException;
    void unhideMenfess(int menfessId) throws MenfessNotFoundException;
    void logout();
}