package burhanfess.menfess;
import users.User;

public class CurhatFess extends Menfess {
    
    public CurhatFess() {
        super();
    }
    
    public CurhatFess(User user, String content) {
        super(user, content);
    }
    
    @Override
    public String getType() {
        return "Curhat";
    }
}