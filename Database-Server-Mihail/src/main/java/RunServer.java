import Models.Users;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import networking.DatabaseServer;

import java.io.IOException;

public class RunServer {

    public static void main(String[] args) {
        IUserDAO userDAO = new UserDAOImpl();
        DatabaseServer dbs = new DatabaseServer();
        try{
            dbs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
            e.printStackTrace();
        }
    }
}
