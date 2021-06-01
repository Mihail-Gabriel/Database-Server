package Persistence.Repository.FoodDAO.Implementation;

import Models.Food;
import Persistence.Repository.FoodDAO.IFoodDAO;
import Persistence.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class FoodDAOImpl implements IFoodDAO {
    private Session session;
    @Override
    public String AddFoodAsync(List<Food> foods) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<String> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            System.out.println("before save");
            for (Food f: foods)
            {
                session.save(f);
            }
            tx.commit();
            response = CompletableFuture.supplyAsync(() -> "Success");
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            response = CompletableFuture.supplyAsync(() ->"Failure");
            Logger.getLogger("con").info("Exception" + e.getMessage());
            e.printStackTrace(System.err);
            return response.get();
        }
        finally
        {
            while(!response.isDone())
            {

            }
            System.out.println(response.get());
            session.close();
        }
        return response.get();
    }

    @Override
    public List<Object> GetFoodByBranchId(int id) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<List<Object>> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sqlQuery = "FROM Food U WHERE U.branch.branchId = :ids";
            Query q = session.createQuery(sqlQuery);
            q.setParameter("ids",id);
            response.get().add(CompletableFuture.supplyAsync(q::getResultList));
        }
        catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            response.get().add("Failure");
            Logger.getLogger("con").info("Exception" + e.getMessage());
            e.printStackTrace(System.err);
            return response.get();
        }
        finally
        {
            while(!response.isDone())
            {

            }
            System.out.println(response.get());
            session.close();
        }
        return response.get();
    }
}
