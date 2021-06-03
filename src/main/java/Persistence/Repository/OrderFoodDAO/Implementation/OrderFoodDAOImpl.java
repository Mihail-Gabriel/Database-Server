package Persistence.Repository.OrderFoodDAO.Implementation;

import Models.Food;
import Models.OrderFood;
import Persistence.Repository.OrderFoodDAO.IOrderFoodDAO;
import Persistence.SessionFactoryUtil;
import com.sun.source.doctree.IndexTree;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class OrderFoodDAOImpl implements IOrderFoodDAO {
    private Session session;
    @Override
    public String AddOrderFoodAsync(List<OrderFood> foods) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<String> response = new CompletableFuture<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println("before save");
            for (OrderFood f : foods) {
                session.save(f);
            }
            tx.commit();
            response = CompletableFuture.supplyAsync(() -> "Success");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            response = CompletableFuture.supplyAsync(() -> "Failure");
            Logger.getLogger("con").info("Exception" + e.getMessage());
            e.printStackTrace(System.err);
            return response.get();
        } finally {
            while (!response.isDone()) {

            }
            System.out.println(response.get());
            session.close();
        }
        return response.get();
    }

    @Override
    public List<Object> GetOrderFoodByOrder() throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<List<Object>> response = new CompletableFuture<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sqlQuery = "FROM OrderFood F JOIN fetch F.orders";
            Query q = session.createQuery(sqlQuery);
            response = CompletableFuture.supplyAsync(q::getResultList);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            response.get().add("Failure");
            Logger.getLogger("con").info("Exception" + e.getMessage());
            e.printStackTrace(System.err);
            return response.get();
        } finally {
            while (!response.isDone()) {

            }
            System.out.println(response.get());
            session.close();
        }
        return response.get();
    }
}
