package ir.makatb.dao;

import ir.makatb.model.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDao extends BaseDao {
    public void save(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(admin);
        transaction.commit();
        session.close();
    }
}
