package ir.maktab.dao;

import ir.maktab.model.Bus;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BusDao extends BaseDao {
    public void save(Bus bus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(bus);
        transaction.commit();
        session.close();
    }
}
