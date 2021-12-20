package ir.maktab.dao;

import ir.maktab.model.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketDao extends BaseDao {
    public void save(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }
}
