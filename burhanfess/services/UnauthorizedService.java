package burhanfess.services;
import burhanfess.exceptions.InvalidInputException;
import burhanfess.exceptions.UsernameAlreadyExistsException;
import burhanfess.users.User;

public interface UnauthorizedService {
    void register(String username, String password) throws UsernameAlreadyExistsException;
    User login(String username, String password) throws InvalidInputException;
    void exit();
}