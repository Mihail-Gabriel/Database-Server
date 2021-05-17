package Persistence.Repository.UserDAO;

import Models.Users;

public interface IUserDAO {
    void RegisterUserAsync(Users users);
    Users ValidateUserAsync(String username, String password);
}
