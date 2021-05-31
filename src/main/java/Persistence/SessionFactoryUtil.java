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
        configObj.addResource("Users.hbm.tld");
        configObj.addResource("Branch.hbm.tld");
        configObj.addResource("Food.hbm.tld");
        configObj.addResource("Order.hbm.tld");
        configObj.addResource("OrderFood.hbm.tld");
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
        return configObj.buildSessionFactory(serviceRegistryObj);
    }

}
