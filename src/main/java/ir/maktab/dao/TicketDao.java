package ir.maktab.dao;

import ir.maktab.enums.City;
import ir.maktab.model.Ticket;
import ir.maktab.model.Trip;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TicketDao extends BaseDao {
    public void save(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

    public void update(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(ticket);
        transaction.commit();
        session.close();
    }
    public List<Ticket> findAvailableSeatByTrip(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Ticket> query = session.createQuery("FROM Ticket d WHERE d.trip=:value and d.ticketType='AVAILABLE'");
        query.setParameter("value", trip);
        List<Ticket> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }
    public Optional<Ticket> findTicketBySeatNumber(int seatNumber,Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Ticket> query = session.createQuery("FROM Ticket d WHERE d.seatNumber=:value AND d.trip=:tripValue and d.ticketType='AVAILABLE' ");
        query.setParameter("value", seatNumber);
        query.setParameter("tripValue", trip);
        Optional<Ticket> ticket = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return ticket;
    }

}
