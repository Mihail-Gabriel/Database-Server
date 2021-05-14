package Persistence.Repository.UserDAO.Implementation;

import Models.Users;
import Persistence.Repository.UserDAO.IUserDAO;
import Persistence.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;


public class UserDAOImpl implements IUserDAO {

    private Session session;


    @Override
    public void RegisterUserAsync(Users users) {
        Thread newThread = new Thread(() -> {
            SessionFactory sessionFactory = SessionFactoryUtil.getInstance().getHibernateSessionFactory();
            session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.save(users);
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
    public Users ValidateUserAsync(String username, String password) {
        AtomicReference<Users> user = new AtomicReference<>();
        Thread newThread = new Thread(() ->{
            session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
            Transaction tx = null;
            try{
                tx= session.beginTransaction();
                String queryString = "FROM Users U Where U.username= username and U.password = password ";
                user.set((Users) session.createQuery(queryString).getSingleResult());
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
