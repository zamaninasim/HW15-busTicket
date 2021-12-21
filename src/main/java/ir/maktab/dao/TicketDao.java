package ir.maktab.dao;

import ir.maktab.enums.City;
import ir.maktab.model.Ticket;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class TicketDao extends BaseDao {
    public void save(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

    public List<Ticket> search(City origin, City destination, Date date) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Ticket.class);
        Criterion originCond = Restrictions.eq("origin", origin);
        Criterion destinationCond = Restrictions.eq("destination", destination);
        Criterion dateCond = Restrictions.eq("date", date);
        Criterion originAndDestinationCond = Restrictions.and(originCond, destinationCond);
        if (date != null) {
            criteria.add(Restrictions.and(originAndDestinationCond, dateCond));
        } else {
            criteria.add(originAndDestinationCond);
        }
        List list = criteria.list();

        transaction.commit();
        session.close();
        return list;
    }

    public List<Ticket> listPaginatedTickets(City origin, City destination, Date date,int first,int max) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Ticket.class);
        Criterion originCond = Restrictions.eq("origin", origin);
        Criterion destinationCond = Restrictions.eq("destination", destination);
        Criterion dateCond = Restrictions.eq("date", date);
        Criterion originAndDestinationCond = Restrictions.and(originCond, destinationCond);
        if (date != null) {
            criteria.add(Restrictions.and(originAndDestinationCond, dateCond));
        } else {
            criteria.add(originAndDestinationCond);
        }
        criteria.setFirstResult(first);
        criteria.setMaxResults(max);
        List<Ticket> tickets = criteria.list();
        transaction.commit();
        session.close();
        return tickets;
    }
}
