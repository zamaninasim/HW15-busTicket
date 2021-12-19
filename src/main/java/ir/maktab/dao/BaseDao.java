package ir.maktab.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseDao {
    SessionFactory sessionFactory = new Configuration()
            .configure().buildSessionFactory();
}
