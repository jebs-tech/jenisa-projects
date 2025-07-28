package burhanfess.menfess;
import burhanfess.users.User;

public class PromosiFess extends Menfess {
    
    public PromosiFess() {
        super();
    }
    
    public PromosiFess(User user, String content) {
        super(user, content);
    }
    
    @Override
    public String getType() {
        return "Promosi";
    }
}