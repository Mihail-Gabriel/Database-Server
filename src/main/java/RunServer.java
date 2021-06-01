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

       /* Branch branch = new Branch();
        branch.setBranchId(2);
        branch.setBranchName("test2");
        branch.setCity("horsens");
        branch.setTheme("asian");
        Food food = new Food("pasta",10,branch);
        Set<Food> set = new HashSet<>();
        set.add(food);
        branch.setFoodSet(set);

        try {
            branchDAO.AddBranchAsync(branch);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        try{
            dbs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
            e.printStackTrace();
        }
    }
}
