package burhanfess.users;

public class User {
    private static int idGenerator = 1;
    protected int id;
    protected String username;
    protected String password;
    
    public User() {
        this.id = idGenerator++;
    }
    
    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }
    
    public String getRole() {
        return "User";
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
    
    // Getters and Setters
    public static int getIdGenerator() { return idGenerator; }
    public static void setIdGenerator(int idGenerator) { User.idGenerator = idGenerator; }
    
    public void setId(int id) { this.id = id; }
    
    public void setUsername(String username) { this.username = username; }
    
    public void setPassword(String password) { this.password = password; }
}
