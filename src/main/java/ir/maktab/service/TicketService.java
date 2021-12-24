package ir.maktab.service;

import ir.maktab.dao.TicketDao;
import ir.maktab.model.Ticket;
import ir.maktab.model.Trip;

import java.util.List;
import java.util.Optional;

public class TicketService {
    TicketDao ticketDao = new TicketDao();

    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public void update(Ticket ticket) {
        ticketDao.update(ticket);
    }

    public List<Ticket> findAvailableSeatByTrip(Trip trip) {
        return ticketDao.findAvailableSeatByTrip(trip);
    }

    public Ticket findTicketBySeatNumber(int seatNumber, Trip trip) {
        Optional<Ticket> ticket = ticketDao.findTicketBySeatNumber(seatNumber, trip);
        if (ticket.isPresent()) {
            final Ticket found = ticket.get();
            return found;
        }
        throw new RuntimeException("ticket with " + seatNumber + " seatNumber not available!");
    }

}
