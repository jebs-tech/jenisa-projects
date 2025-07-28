package burhanfess.users;

public class Cosmic extends User {
    
    public Cosmic() {
        super();
    }
    
    public Cosmic(String username, String password) {
        super(username, password);
    }
    
    @Override
    public String getRole() {
        return "Cosmic";
    }
    
    @Override
    public String toString() {
        return "Cosmic{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}