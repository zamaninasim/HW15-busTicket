package ir.maktab.service;

import ir.maktab.dao.CustomerDao;
import ir.maktab.model.Customer;

public class CustomerService {
    CustomerDao customerDao = new CustomerDao();

    public void save(Customer customer) {
        customerDao.save(customer);
    }
}
