package ir.maktab.dao;

import ir.maktab.model.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class CompanyDao extends BaseDao {
    public void save(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(company);
        transaction.commit();
        session.close();
    }

    public Optional<Company> findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Company> query = session.createQuery("FROM Company c WHERE c.name=:nameValue");
        query.setParameter("nameValue", name);
        Optional<Company> admin = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return admin;
    }
}
