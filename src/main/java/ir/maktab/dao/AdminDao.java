package ir.maktab.dao;

import ir.maktab.model.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class AdminDao extends BaseDao {
    public void save(Admin admin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(admin);
        transaction.commit();
        session.close();
    }

    public Optional<Admin> findByNationalCode(String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Admin> query = session.createQuery("FROM Admin a WHERE a.nationalCode=:value");
        query.setParameter("value", username);
        Optional<Admin> admin = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return admin;
    }
}
