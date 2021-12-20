package ir.maktab.service;

import ir.maktab.dao.AdminDao;
import ir.maktab.model.Admin;

import java.util.Optional;

public class AdminService {
    AdminDao adminDao = new AdminDao();

    public void save(Admin admin) {
        adminDao.save(admin);
    }

    public Admin findByNationalCode(String nationalCode) throws RuntimeException {
        Optional<Admin> admin = adminDao.findByNationalCode(nationalCode);
        if (admin.isPresent()) {
            final Admin found = admin.get();
            return found;
        }
        throw new RuntimeException("this username not exist.");
    }
}
