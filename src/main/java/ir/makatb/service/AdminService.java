package ir.makatb.service;

import ir.makatb.dao.AdminDao;
import ir.makatb.model.Admin;

import java.util.Optional;

public class AdminService {
    AdminDao adminDao = new AdminDao();

    public Admin findByUsername(String username) {
        Optional<Admin> admin = adminDao.findByUsername(username);
        if (admin.isPresent()) {
            final Admin found = admin.get();
            return found;
        }
        throw new RuntimeException("Admin not found.");
    }
}
