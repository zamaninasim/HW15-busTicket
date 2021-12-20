package ir.maktab.dao;

import ir.maktab.model.Seat;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SeatDao extends BaseDao {
    public void save(Seat seat) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(seat);
        transaction.commit();
        session.close();
    }
}
