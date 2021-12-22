package ir.maktab.service;

import ir.maktab.dao.TripDao;
import ir.maktab.enums.City;
import ir.maktab.model.Trip;

import java.util.Date;
import java.util.List;

public class TripService {
    TripDao tripDao = new TripDao();

    public void save(Trip trip) {
        tripDao.save(trip);
    }

    public List<Trip> listTripByPaginated(City origin, City destination, Date date, int startResult, int maxResultInPage) {
        return tripDao.listTripByPaginated(origin, destination, date, startResult, maxResultInPage);
    }
}
