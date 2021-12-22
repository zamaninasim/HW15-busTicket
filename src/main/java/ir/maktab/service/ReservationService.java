package ir.maktab.service;

import ir.maktab.dao.ReservationDao;
import ir.maktab.model.Reservation;

public class ReservationService {
    ReservationDao reservationDao = new ReservationDao();
    public void save(Reservation reservation) {
        reservationDao.save(reservation);
    }
}
