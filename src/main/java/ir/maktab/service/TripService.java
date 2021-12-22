package ir.maktab.service;

import ir.maktab.dao.TripDao;
import ir.maktab.dto.TripDto;
import ir.maktab.enums.City;
import ir.maktab.model.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripService {
    TripDao tripDao = new TripDao();

    public void save(Trip trip) {
        tripDao.save(trip);
    }

    public List<TripDto> listTripByPaginated(City origin, City destination, Date date, int startResult, int maxResultInPage) {
        List<Trip> trips = tripDao.listTripByPaginated(origin, destination, date, startResult, maxResultInPage);
        List<TripDto> tripDtos = new ArrayList<>();
        for (Trip trip : trips) {
            TripDto tripDto = new TripDto();
            tripDto.setDate(trip.getDate());
            tripDto.setTime(trip.getTime());
            tripDto.setPrice(trip.getPrice());
            tripDto.setBusType(trip.getBus().getType());
            tripDto.setAvailableSeat(trip.getBus().getAvailableSeat());
            tripDto.setCompanyName(trip.getBus().getCompany().getName());
            tripDtos.add(tripDto);
        }

        return tripDtos;
    }
}
