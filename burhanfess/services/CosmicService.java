package burhanfess.services;
import burhanfess.exceptions.SamePasswordException;
import burhanfess.exceptions.UserNotFoundException;
import burhanfess.menfess.Menfess;
import java.util.List;

public interface CosmicService {
    void sendCurhatFess(String content);
    void sendPromosiFess(String content);
    void sendConfessFess(String content, String receiverUsername) throws UserNotFoundException;
    List<Menfess> getAllUnhiddenMenfesses();
    List<Menfess> getAllSentMenfesses();
    List<Menfess> getAllReceivedMenfesses();
    void changePassword(String newPassword) throws SamePasswordException;
    void logout();
}