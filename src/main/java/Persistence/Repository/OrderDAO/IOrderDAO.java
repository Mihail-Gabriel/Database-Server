package Persistence.Repository.OrderDAO;

import Models.Orders;

import java.util.concurrent.ExecutionException;

public interface IOrderDAO {

    String AddOrderAsync(Orders order) throws ExecutionException, InterruptedException;
    Object GetOrderByUser(String username) throws ExecutionException, InterruptedException;
}
