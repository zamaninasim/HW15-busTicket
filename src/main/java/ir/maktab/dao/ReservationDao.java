package ir.maktab.dao;

import ir.maktab.model.Reservation;
import ir.maktab.model.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReservationDao extends BaseDao{
    public void save(Reservation reservation) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(reservation);
        transaction.commit();
        session.close();
    }
}
