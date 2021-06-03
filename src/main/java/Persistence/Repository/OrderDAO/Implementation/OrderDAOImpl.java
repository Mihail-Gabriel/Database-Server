package Persistence.Repository.OrderDAO.Implementation;

import Models.Orders;
import Persistence.Repository.OrderDAO.IOrderDAO;
import Persistence.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class OrderDAOImpl implements IOrderDAO {

    private Session session ;

    @Override
    public String AddOrderAsync(Orders order) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<String> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(order);
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
    public Object GetOrderByUser(String username) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<Object> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sqlQuery = "FROM Orders U WHERE U.username = :ids";
            Query q = session.createQuery(sqlQuery);
            q.setParameter("ids",username);
            response = CompletableFuture.supplyAsync(q::getSingleResult);
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

}

