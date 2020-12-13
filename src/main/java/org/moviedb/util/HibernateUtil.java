package org.moviedb.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private  static HibernateUtil util = new HibernateUtil();
    private SessionFactory sessionFactory;

    public static HibernateUtil getInstance(){
        return util;
    }

    private HibernateUtil(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory=configuration.buildSessionFactory();
    }

    public static Session getSession(){
        Session session = getInstance().sessionFactory.openSession();
        return session;

    }

}
