package ir.maktab.dao;

import ir.maktab.model.Customer;
import ir.maktab.model.Reservation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ReservationDao extends BaseDao {
    public void save(Reservation reservation) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(reservation);
        transaction.commit();
        session.close();
    }

    public void update(Reservation reservation) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(reservation);
        transaction.commit();
        session.close();
    }

    public List<Reservation> findReservationByCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Reservation.class, "r");
        criteria.add(Restrictions.eq("r.customer", customer));
        //criteria.add(Restrictions.eq("r.reservationType", "PROCESSING"));
        List reservations = criteria.list();
        transaction.commit();
        session.close();
        return reservations;
    }
}
