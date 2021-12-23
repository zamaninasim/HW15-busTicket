package ir.maktab.dao;

import ir.maktab.model.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OwnerDao extends BaseDao {
    public void save(Owner owner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(owner);
        transaction.commit();
        session.close();
    }
}
