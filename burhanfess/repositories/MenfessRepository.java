package burhanfess.repositories;
import burhanfess.menfess.Menfess;
import burhanfess.users.User;
import java.util.List;

public interface MenfessRepository {
    List<Menfess> getAllMenfessesByUser(User user);
    List<Menfess> getAllHiddenMenfesses();
    List<Menfess> getAllUnhiddenMenfesses();
    void addMenfess(Menfess menfess);
    void hideMenfess(Menfess menfess);
    void unhideMenfess(Menfess menfess);
    List<Menfess> getAllMenfessesForUser(User user);
}