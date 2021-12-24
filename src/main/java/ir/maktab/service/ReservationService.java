package ir.maktab.service;

import ir.maktab.dao.ReservationDao;
import ir.maktab.dto.ReservationDto;
import ir.maktab.model.Customer;
import ir.maktab.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    ReservationDao reservationDao = new ReservationDao();

    public void save(Reservation reservation) {
        reservationDao.save(reservation);
    }

    public void update(Reservation reservation) {
        reservationDao.update(reservation);
    }

    public List<ReservationDto> findReservationByCustomer(Customer customer) {
        List<Reservation> reservations = reservationDao.findReservationByCustomer(customer);
        List<ReservationDto> reservationsDto = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setReserveDate(reservation.getReserveDate());
            reservationDto.setReservationType(reservation.getReservationType());
            reservationDto.setTotalPrice(reservation.getTotalPrice());
            reservationDto.setSeatNumber(reservation.getTickets().stream().map(i->i.getSeatNumber()).collect(Collectors.toList()));
            reservationDto.setTripDate(reservation.getTickets().get(0).getTrip().getDate());
            reservationDto.setTripTime(reservation.getTickets().get(0).getTrip().getTime());
            reservationDto.setOrigin(reservation.getTickets().get(0).getTrip().getOrigin());
            reservationDto.setDestination(reservation.getTickets().get(0).getTrip().getDestination());
            reservationDto.setBusType(reservation.getTickets().get(0).getTrip().getBus().getType());
            reservationDto.setCompany(reservation.getTickets().get(0).getTrip().getBus().getCompany().getName());
            reservationsDto.add(reservationDto);
        }
        return reservationsDto;
    }
}
