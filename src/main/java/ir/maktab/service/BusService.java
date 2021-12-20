package ir.maktab.service;

import ir.maktab.dao.BusDao;
import ir.maktab.model.Bus;

import java.util.Optional;

public class BusService {
    BusDao busDao = new BusDao();

    public void save(Bus bus) {
        busDao.save(bus);
    }

    public Bus findByPlaque(String plaque) {
        Optional<Bus> bus = busDao.findByPlaque(plaque);
        if (bus.isPresent()) {
            return bus.get();
        }
        throw new RuntimeException("bus not found!");
    }
}
