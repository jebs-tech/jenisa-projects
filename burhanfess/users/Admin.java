package burhanfess.users;
public class Admin extends User {
    
    public Admin() {
        super();
    }
    
    public Admin(String username, String password) {
        super(username, password);
    }
    
    @Override
    public String getRole() {
        return "Admin";
    }
    
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
