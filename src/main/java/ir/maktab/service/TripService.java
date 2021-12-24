package ir.maktab.service;

import ir.maktab.dao.TripDao;
import ir.maktab.dto.TripDto;
import ir.maktab.enums.BusType;
import ir.maktab.enums.City;
import ir.maktab.model.Condition;
import ir.maktab.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    TripDao tripDao = new TripDao();

    public void save(Trip trip) {
        tripDao.save(trip);
    }

    public Trip get(Integer id) {
        return tripDao.get(id);
    }

    public List<TripDto> listTripByPaginated(City origin, City destination, Condition condition, int startResult, int maxResultInPage) {
        List<Trip> trips = tripDao.listTripByPaginated(origin, destination, condition, startResult, maxResultInPage);
        List<TripDto> tripDtos = new ArrayList<>();
        for (Trip trip : trips) {
            TripDto tripDto = new TripDto();
            tripDto.setId(trip.getId());
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
    public List<Trip> findBusReservations(BusType busType) {
        return tripDao.findBusReservations(busType);
    }
}
