package Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {

    private static SessionFactoryUtil instance;

    public static SessionFactoryUtil getInstance()
    {
        if(instance == null)
            instance = new SessionFactoryUtil();
        return instance;
    }

    public SessionFactory getHibernateSessionFactory()
    {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        configObj.addAnnotatedClass(Models.Branch.class);
        configObj.addAnnotatedClass(Models.Food.class);
        configObj.addAnnotatedClass(Models.Users.class);
        configObj.addAnnotatedClass(Models.OrderFood.class);
        configObj.addAnnotatedClass(Models.Orders.class);
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        return configObj.buildSessionFactory(serviceRegistryObj);
    }

}
