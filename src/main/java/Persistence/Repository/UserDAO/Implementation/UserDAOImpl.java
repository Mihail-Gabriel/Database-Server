package Persistence.Repository.UserDAO.Implementation;

import Models.Users;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;


public class UserDAOImpl implements IUserDAO {

    private Session session;


    public UserDAOImpl()
    {
    }

    @Override
    public String RegisterUserAsync(Users users) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> response = new CompletableFuture<>();
            session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(users);
                tx.commit();
                response = CompletableFuture.supplyAsync(() -> "Success");

            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                response = CompletableFuture.supplyAsync(() -> "Failure");
                System.out.println(response.get());
                Logger.getLogger("con").info("Exception" + e.getMessage());
                e.printStackTrace(System.err);
                return (String) response.get();
            }
            finally
            {
                while(response.isDone())
                {

                }
                System.out.println(response.get());
                session.close();
            }
        return (String) response.get();
    }

    @Override
    public Object ValidateUserAsync(String usr, String pass) throws ExecutionException, InterruptedException {
            session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
            CompletableFuture<Object> response = new CompletableFuture<>();
            Transaction tx = null;
            try{
                tx= session.beginTransaction();
                String queryString = "FROM Users as U Where U.username= :usrn and U.password = :passw ";
                Query query = session.createQuery(queryString);
                query.setParameter("usrn",usr);
                query.setParameter("passw",pass);
                response = CompletableFuture.supplyAsync(()-> query.getSingleResult());
            } catch (HibernateException e) {
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

                session.close();
            }
        return response.get();
    }

    @Override
    public String DeleteUserAsync(Users users) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<String> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx= session.beginTransaction();
            session.delete(users);
            tx.commit();
        } catch (HibernateException e) {
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
        return "Success";
    }

    @Override
    public Object UpdateUserAsync(Users users, Users oldUser) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<Object> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(users);
            session.delete(oldUser);
            response = CompletableFuture.supplyAsync(() -> users);
            tx.commit();
        } catch (HibernateException e) {
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
        return response;
    }
}
