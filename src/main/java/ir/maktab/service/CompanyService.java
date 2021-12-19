package ir.maktab.service;

import ir.maktab.dao.CompanyDao;
import ir.maktab.model.Company;

import java.util.Optional;

public class CompanyService {
    CompanyDao companyDao = new CompanyDao();

    public void save(Company company) {
        companyDao.save(company);
    }

    public Company findByName(String name) {
        Optional<Company> company = companyDao.findByName(name);
        if (company.isPresent()) {
            final Company found = company.get();
            return found;
        } else {
            Company newCompany = new Company();
            newCompany.setName(name);
            companyDao.save(newCompany);
            return newCompany;
        }
    }
}
