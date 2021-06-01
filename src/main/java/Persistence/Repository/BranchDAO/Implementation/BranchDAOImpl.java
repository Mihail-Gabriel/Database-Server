package Persistence.Repository.BranchDAO.Implementation;

import Models.Branch;
import Models.Food;
import Persistence.Repository.BranchDAO.IBranchDAO;
import Persistence.SessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.util.impl.Log;


import javax.persistence.Query;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class BranchDAOImpl implements IBranchDAO {

    private Session session;

    @Override
    public String AddBranchAsync(Branch branch) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<String> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(branch);
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
    public Object GetBranchAsync(int id) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<Object> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sqlQuery = "FROM Branch U WHERE U.branchId = :ids";
            Query q = session.createQuery(sqlQuery);
            q.setParameter("ids",id);
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

    @Override
    public List<Branch> GetAllBranches() {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        Transaction tx = null;
        List<Branch> branchList = new ArrayList<Branch>();
        try{
            tx = session.beginTransaction();
            String queryString = "FROM Branch";
            Query q = session.createQuery(queryString);
            branchList = (List<Branch>) q.getResultList();
        }
        catch (HibernateException e) {
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

        return branchList;
    }

    @Override
    public String RemoveBranchAsync(int id) throws ExecutionException, InterruptedException {
        session = SessionFactoryUtil.getInstance().getHibernateSessionFactory().openSession();
        CompletableFuture<String> response = new CompletableFuture<>();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Branch branch = new Branch();
            String sqlQuery = "FROM Branch U WHERE U.branchId = :ids ";
            System.out.println("Before querry");
            Query q = session.createQuery(sqlQuery);
            System.out.println("After query");
            q.setParameter("ids",id);
            branch = (Branch) q.getSingleResult();
            System.out.println(branch);
            session.delete(branch);
            response = CompletableFuture.supplyAsync(() ->"Success");
            tx.commit();
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
