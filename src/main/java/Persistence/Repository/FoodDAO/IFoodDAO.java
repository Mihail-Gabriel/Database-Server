package Persistence.Repository.FoodDAO;

import Models.Food;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IFoodDAO {

    String AddFoodAsync(List<Food> foods) throws ExecutionException, InterruptedException;
    List<Object> GetFoodByBranchAsync() throws ExecutionException, InterruptedException;
}
