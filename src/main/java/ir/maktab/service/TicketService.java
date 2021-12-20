package ir.maktab.service;

import ir.maktab.dao.TicketDao;
import ir.maktab.model.Ticket;

public class TicketService {
    TicketDao ticketDao = new TicketDao();

    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }
}
