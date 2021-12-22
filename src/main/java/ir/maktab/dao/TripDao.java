package ir.maktab.dao;

import ir.maktab.enums.City;
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
    public Trip get(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Trip trip = session.get(Trip.class, id);
        transaction.commit();
        session.close();
        return trip;
    }

    public List<Trip> listTripByPaginated(City origin, City destination, Date date, int startResult, int maxResultInPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Trip.class, "t");
        /*criteria.createAlias("t.bus","b");
        criteria.createAlias("b.company","c");*/
        Criterion originCond = Restrictions.eq("t.origin", origin);
        Criterion destinationCond = Restrictions.eq("t.destination", destination);
        Criterion dateCond = Restrictions.eq("t.date", date);
        Criterion originAndDestinationCond = Restrictions.and(originCond, destinationCond);
        if (date != null) {
            criteria.add(Restrictions.and(originAndDestinationCond, dateCond));
            /*criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("t.time").as("time"))
                    .add(Projections.property("t.date").as("date"))
                    .add(Projections.property("t.price").as("Price"))
                    .add(Projections.property("c.name").as("companyName"))
                    .add(Projections.property("b.type").as("busType"))
                    .add(Projections.property("b.availableSeat").as("availableSeat")));
            criteria.setResultTransformer(Transformers.aliasToBean(TripDto.class));*/

        } else {
            criteria.add(originAndDestinationCond);
            /*criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("t.time").as("time"))
                    .add(Projections.property("t.date").as("date"))
                    .add(Projections.property("t.price").as("Price"))
                    .add(Projections.property("c.name").as("companyName"))
                    .add(Projections.property("b.type").as("busType"))
                    .add(Projections.property("b.availableSeat").as("availableSeat")));
            criteria.setResultTransformer(Transformers.aliasToBean(TripDto.class));*/
        }
        criteria.setFirstResult(startResult);
        criteria.setMaxResults(maxResultInPage);
        List<Trip> trips = criteria.list();
        transaction.commit();
        session.close();
        return trips;
    }
}
