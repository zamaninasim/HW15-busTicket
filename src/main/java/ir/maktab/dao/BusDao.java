package ir.maktab.dao;

import ir.maktab.enums.BusType;
import ir.maktab.model.Bus;
import ir.maktab.model.Customer;
import ir.maktab.model.Reservation;
import ir.maktab.model.Trip;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;
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
