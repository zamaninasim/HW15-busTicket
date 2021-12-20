package ir.maktab.service;

import ir.maktab.dao.BusDao;
import ir.maktab.model.Bus;

public class BusService {
    BusDao busDao = new BusDao();
    public void save(Bus bus) {
        busDao.save(bus);
    }
}
