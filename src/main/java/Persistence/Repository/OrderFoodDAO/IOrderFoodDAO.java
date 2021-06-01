package Persistence.Repository.OrderFoodDAO;

import Models.OrderFood;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IOrderFoodDAO {
    String AddOrderFoodAsync(List<OrderFood> foods) throws ExecutionException, InterruptedException;;
    List<Object> GetOrderFoodByOrder() throws ExecutionException, InterruptedException;;
}
