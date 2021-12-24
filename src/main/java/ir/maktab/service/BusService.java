package ir.maktab.service;

import ir.maktab.dao.BusDao;
import ir.maktab.enums.BusType;
import ir.maktab.model.Bus;
import ir.maktab.model.Trip;

import java.util.List;
import java.util.Optional;

public class BusService {
    BusDao busDao = new BusDao();

    public void save(Bus bus) {
        busDao.save(bus);
    }

    public void update(Bus bus) {
        busDao.update(bus);
    }

    public Bus findByPlaque(String plaque) {
        Optional<Bus> bus = busDao.findByPlaque(plaque);
        if (bus.isPresent()) {
            return bus.get();
        }
        throw new RuntimeException("bus not found!");
    }


}
