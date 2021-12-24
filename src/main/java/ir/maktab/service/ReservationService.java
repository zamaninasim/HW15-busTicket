package ir.maktab.service;

import ir.maktab.dao.ReservationDao;
import ir.maktab.model.Customer;
import ir.maktab.model.Reservation;

import java.util.List;

public class ReservationService {
    ReservationDao reservationDao = new ReservationDao();

    public void save(Reservation reservation) {
        reservationDao.save(reservation);
    }

    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }

    public List<Reservation> findReservationByCustomer(Customer customer) {
        return reservationDao.findReservationByCustomer(customer);
    }
}
