package burhanfess.menfess;
import users.User;

public class ConfessFess extends Menfess {
    private User receiver;
    
    public ConfessFess() {
        super();
    }
    
    public ConfessFess(User user, String content, User receiver) {
        super(user, content);
        this.receiver = receiver;
    }
    
    public User getReceiver() {
        return receiver;
    }
    
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    
    @Override
    public String getType() {
        return "Confession";
    }
    
    @Override
    public String toString() {
        return "[" + getType() + "] oleh anonim\n" +
               "ID: " + id + "\n" +
               content + "\n" +
               "Dikirim pada " + timestamp.format(formatter);
    }
}