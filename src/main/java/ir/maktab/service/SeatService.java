package ir.maktab.service;

import ir.maktab.dao.SeatDao;
import ir.maktab.model.Seat;

public class SeatService {
    SeatDao seatDao = new SeatDao();
    public void save(Seat seat) {
        seatDao.save(seat);
    }
}
