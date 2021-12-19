package ir.maktab.dao;

import ir.maktab.model.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompanyDao extends BaseDao {
    public void save(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(company);
        transaction.commit();
        session.close();
    }
}
