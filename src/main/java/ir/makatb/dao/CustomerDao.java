package ir.makatb.dao;

import ir.makatb.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDao extends BaseDao{
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
        session.close();
    }
}
