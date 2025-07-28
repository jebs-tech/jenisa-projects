package burhanfess.repositories;
import users.User;
import menfess.Menfess;
import menfess.ConfessFess;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MenfessRepositoryImpl implements MenfessRepository {
    private List<Menfess> menfesses;
    private static MenfessRepository menfessRepository;
    
    private MenfessRepositoryImpl() {
        this.menfesses = new ArrayList<>();
    }
    
    public static MenfessRepository getInstance() {
        if (menfessRepository == null) {
            menfessRepository = new MenfessRepositoryImpl();
        }
        return menfessRepository;
    }
    
    @Override
    public List<Menfess> getAllMenfessesByUser(User user) {
        return menfesses.stream()
                .filter(m -> m.getUser().equals(user))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Menfess> getAllMenfessesForUser(User user) {
        return menfesses.stream()
                .filter(m -> m instanceof ConfessFess ? 
                    ((ConfessFess) m).getReceiver().equals(user) : false)
                .collect(Collectors.toList());
    }
    
    @Override
    public void unhideMenfess(Menfess menfess) {
        menfess.unhide();
    }
    
    @Override
    public void hideMenfess(Menfess menfess) {
        menfess.hide();
    }
    
    @Override
    public void addMenfess(Menfess menfess) {
        menfesses.add(menfess);
    }
    
    @Override
    public List<Menfess> getAllUnhiddenMenfesses() {
        return menfesses.stream()
                .filter(m -> !m.isHidden())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Menfess> getAllHiddenMenfesses() {
        return menfesses.stream()
                .filter(m -> m.isHidden())
                .collect(Collectors.toList());
    }
}