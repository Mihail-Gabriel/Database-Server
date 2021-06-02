import Models.Branch;
import Models.Food;
import Models.Users;
import Persistence.Repository.BranchDAO.IBranchDAO;
import Persistence.Repository.BranchDAO.Implementation.BranchDAOImpl;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.Repository.UserDAO.Implementation.UserDAOImpl;
import networking.DatabaseServer;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class RunServer {

    public static void main(String[] args) {

        IBranchDAO branchDAO = new BranchDAOImpl();
        DatabaseServer dbs = new DatabaseServer();

        ///-----------
        /*Branch branch = new Branch();
        branch.setBranchId(5);
        branch.setBranchName("test4");
        branch.setCity("aarhus");
        branch.setTheme("spanish");
        Food food = new Food("pizza",10,branch);


        try {
            branchDAO.AddBranchAsync(branch);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        ///-------------

        try{
            dbs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
            e.printStackTrace();
        }
    }
}
