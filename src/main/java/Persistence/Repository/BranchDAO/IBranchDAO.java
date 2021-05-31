package Persistence.Repository.BranchDAO;

import Models.Branch;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IBranchDAO {
    String AddBranchAsync(Branch branch) throws ExecutionException, InterruptedException;
    Object GetBranchAsync(int id) throws ExecutionException, InterruptedException;
    List<Branch> GetAllBranches();
    String RemoveBranchAsync(int id) throws ExecutionException, InterruptedException;

}
