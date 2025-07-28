package burhanfess.menfess;
import burhanfess.users.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menfess {
    private static int idGenerator = 0;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    protected int id;
    protected LocalDateTime timestamp;
    protected String content;
    protected boolean isHidden;
    protected User user;
    
    public Menfess() {
        this.id = idGenerator++;
        this.timestamp = LocalDateTime.now();
        this.isHidden = false;
    }
    
    public Menfess(User user, String content) {
        this();
        this.user = user;
        this.content = content;
    }
    
    public User getUser() {
        return user;
    }
    
    public boolean isHidden() {
        return isHidden;
    }
    
    public int getId() {
        return id;
    }
    
    public void hide() {
        this.isHidden = true;
    }
    
    public void unhide() {
        this.isHidden = false;
    }
    
    public String getType() {
        return "Menfess";
    }
    
    public String getContent() {
        return content;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "[" + getType() + "] oleh " + (user != null ? user.getUsername() : "anonim") + "\n" +
               "ID: " + id + "\n" +
               content + "\n" +
               "Dikirim pada " + timestamp.format(formatter);
    }
}