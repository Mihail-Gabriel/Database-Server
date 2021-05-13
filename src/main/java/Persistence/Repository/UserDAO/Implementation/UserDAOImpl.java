package Persistence.Repository.UserDAO.Implementation;

import Models.User;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;


public class UserDAOImpl implements IUserDAO {

    private Session session;


    @Override
    public void RegisterUserAsync(User user) {
        Thread newThread = new Thread(() -> {
            session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(user);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }

                Logger.getLogger("con").info("Exception" + e.getMessage());
                e.printStackTrace(System.err);
            }
            finally
            {
                session.close();
            }
        });
        newThread.start();

    }

    @Override
    public User ValidateUserAsync(String username, String password) {
        AtomicReference<User> user = new AtomicReference<>();
        Thread newThread = new Thread(() ->{
            session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
            Transaction tx = null;
            try{
                tx= session.beginTransaction();
                String queryString = "FROM User U Where U.username= username and U.password = password ";
                user.set((User) session.createQuery(queryString).getSingleResult());
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }

                Logger.getLogger("con").info("Exception" + e.getMessage());
                e.printStackTrace(System.err);
            }
            finally
            {
                session.close();
            }
        });
        newThread.start();
        return user.get();
    }
}
