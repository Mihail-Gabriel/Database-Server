package Persistence.Repository.UserDAO;

import Models.Users;

import java.util.concurrent.ExecutionException;

public interface IUserDAO {
    String RegisterUserAsync(Users users) throws ExecutionException, InterruptedException;
    Object ValidateUserAsync(String username, String password) throws ExecutionException, InterruptedException;
    String DeleteUserAsync(Users users) throws ExecutionException, InterruptedException;
    Object UpdateUserAsync(Users users, Users oldUser) throws ExecutionException, InterruptedException;
}
