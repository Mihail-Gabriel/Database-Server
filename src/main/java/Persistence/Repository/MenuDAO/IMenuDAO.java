package Persistence.Repository.MenuDAO;

import Models.Menu;

import java.util.concurrent.ExecutionException;

public interface IMenuDAO {

    String AddMenuAsync(Menu menu) throws ExecutionException, InterruptedException;
    Object GetMenuAsync(String menuName) throws ExecutionException, InterruptedException;
    Object ModifyMenuAsync(Menu menu, Menu oldMenu) throws ExecutionException, InterruptedException;
    String DeleteMenuAsync(Menu menu) throws ExecutionException, InterruptedException;
}
