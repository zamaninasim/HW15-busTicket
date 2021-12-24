package ir.maktab.dao;

import ir.maktab.enums.BusType;
import ir.maktab.enums.City;
import ir.maktab.model.Condition;
import ir.maktab.model.Trip;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

public class TripDao extends BaseDao {

    public void save(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trip);
        transaction.commit();
        session.close();
    }

    public Trip get(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Trip trip = session.get(Trip.class, id);
        transaction.commit();
        session.close();
        return trip;
    }

    public List<Trip> listTripByPaginated(City origin, City destination, Condition condition, int startResult, int maxResultInPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trip.class, "t");
        criteria.createAlias("t.bus","b");
        criteria.createAlias("b.company","c");

        SimpleExpression originCond = Restrictions.eq("t.origin", origin);
        criteria.add(originCond);
        SimpleExpression destinationCond = Restrictions.eq("t.destination", destination);
        criteria.add(destinationCond);

        if (condition.getDate() != null) {
            SimpleExpression dateCond = Restrictions.eq("t.date", condition.getDate());
            criteria.add(dateCond);
        }
        if (condition.getBusType() != null) {
            SimpleExpression busCond = Restrictions.eq("b.type", condition.getBusType());
            criteria.add(busCond);
        }
        if (condition.getCompanyName()!= null) {
            SimpleExpression companyCond = Restrictions.eq("c.name", condition.getCompanyName());
            criteria.add(companyCond);
        }
        if (condition.getMinTime() != null && condition.getMaxTime() != null) {
            Criterion timeCond = Restrictions.between("t.time", condition.getMinTime(), condition.getMaxTime());
            criteria.add(timeCond);
        }
        if (condition.getMinPrice() != null && condition.getMaxPrice() != null) {
            Criterion priceCond = Restrictions.between("t.price", condition.getMinPrice(), condition.getMaxPrice());
            criteria.add(priceCond);
        }
        criteria.setFirstResult(startResult);
        criteria.setMaxResults(maxResultInPage);
        List<Trip> trips = criteria.list();
        transaction.commit();
        session.close();
        return trips;
    }

    public List<Trip> findBusReservations(BusType busType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trip.class, "t");
        criteria.createAlias("t.bus","b");
        criteria.add(Restrictions.eq("b.type", busType));
        criteria.addOrder(Order.desc("t.date"));
        List trips = criteria.list();
        transaction.commit();
        session.close();
        return trips;
    }
}
