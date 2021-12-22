package ir.maktab.service;

import ir.maktab.dao.TicketDao;
import ir.maktab.enums.City;
import ir.maktab.model.Ticket;
import org.hibernate.Criteria;

import java.util.Date;
import java.util.List;

public class TicketService {
    TicketDao ticketDao = new TicketDao();

    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

}
