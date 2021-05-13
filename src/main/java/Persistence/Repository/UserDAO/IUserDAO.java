package Persistence.Repository.UserDAO;

import Models.User;

public interface IUserDAO {
    void RegisterUserAsync(User user);
    User ValidateUserAsync(String username, String password);
}
