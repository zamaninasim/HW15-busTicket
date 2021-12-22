package ir.maktab.dao;

import ir.maktab.model.Bus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class BusDao extends BaseDao {
    public void save(Bus bus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(bus);
        transaction.commit();
        session.close();
    }
    public void update(Bus bus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(bus);
        transaction.commit();
        session.close();
    }

    public Optional<Bus> findByPlaque(String plaque) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Bus> query = session.createQuery("FROM Bus b WHERE b.plaque=:value");
        query.setParameter("value", plaque);
        Optional<Bus> bus = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return bus;
    }
}
