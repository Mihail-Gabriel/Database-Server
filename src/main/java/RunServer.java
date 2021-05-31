import Models.Users;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import networking.DatabaseServer;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RunServer {

    public static void main(String[] args) {
        IUserDAO userDAO = new UserDAOImpl();
        Users user = new Users();
        user.setAddress("yes");
        user.setCity("yes");
        user.setPassword("yes");
        user.setRole("yes");
        user.setTelephoneNo("0234234");
        user.setUsername("maybe");
        DatabaseServer dbs = new DatabaseServer();
        try {
            userDAO.RegisterUserAsync(user);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        try{
            dbs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
            e.printStackTrace();
        }
    }
}
