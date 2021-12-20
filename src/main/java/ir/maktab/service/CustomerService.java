package ir.maktab.service;

import ir.maktab.dao.CustomerDao;
import ir.maktab.model.Customer;

import java.util.Optional;

public class CustomerService {
    CustomerDao customerDao = new CustomerDao();

    public void save(Customer customer) {
        customerDao.save(customer);
    }

    public Customer findByNationalCode(String nationalCode) throws RuntimeException {
        Optional<Customer> customer = customerDao.findByNationalCode(nationalCode);
        if (customer.isPresent()) {
            final Customer found = customer.get();
            return found;
        }
        throw new RuntimeException("this username not exist.");
    }
}
