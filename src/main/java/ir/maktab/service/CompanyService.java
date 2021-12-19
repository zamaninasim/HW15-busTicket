package ir.maktab.service;

import ir.maktab.dao.CompanyDao;
import ir.maktab.model.Company;

public class CompanyService {
    CompanyDao companyDao = new CompanyDao();
    public void save(Company company) {
        companyDao.save(company);
    }
}
