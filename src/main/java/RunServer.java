import Persistence.Repository.BranchDAO.IBranchDAO;
import Persistence.Repository.BranchDAO.Implementation.BranchDAOImpl;
import networking.DatabaseServer;

import java.io.IOException;

public class RunServer {

    public static void main(String[] args) {

        IBranchDAO branchDAO = new BranchDAOImpl();
        DatabaseServer dbs = new DatabaseServer();

        ///-----------
        ///-------------

        try{
            dbs.startServer();
        } catch (IOException e) {
            System.out.println("Complete failure to launch");
            e.printStackTrace();
        }
    }
}
