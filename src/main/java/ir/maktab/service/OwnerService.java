package ir.maktab.service;

import ir.maktab.dao.OwnerDao;
import ir.maktab.model.Owner;

public class OwnerService {
    OwnerDao ownerDao = new OwnerDao();

    public void save(Owner owner) {
        ownerDao.save(owner);
    }

}
