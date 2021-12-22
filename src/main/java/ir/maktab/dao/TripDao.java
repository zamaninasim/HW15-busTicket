package ir.maktab.dao;

import ir.maktab.enums.City;
import ir.maktab.model.Ticket;
import ir.maktab.model.Trip;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class TripDao extends BaseDao {

    public void save(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trip);
        transaction.commit();
        session.close();
    }
    public List<Trip> listTripByPaginated(City origin, City destination, Date date, int startResult, int maxResultInPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trip.class);
        Criterion originCond = Restrictions.eq("origin", origin);
        Criterion destinationCond = Restrictions.eq("destination", destination);
        Criterion dateCond = Restrictions.eq("date", date);
        Criterion originAndDestinationCond = Restrictions.and(originCond, destinationCond);
        if (date != null) {
            criteria.add(Restrictions.and(originAndDestinationCond, dateCond));
        } else {
            criteria.add(originAndDestinationCond);
        }
        criteria.setFirstResult(startResult);
        criteria.setMaxResults(maxResultInPage);
        List<Trip> trips = criteria.list();
        transaction.commit();
        session.close();
        return trips;
    }
}