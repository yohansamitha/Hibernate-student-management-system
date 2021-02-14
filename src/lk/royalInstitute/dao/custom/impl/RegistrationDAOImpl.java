package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.RegistrationDAO;
import lk.royalInstitute.entity.Registration;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public String getLastID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT regNo FROM Registration ORDER BY regNo DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        System.out.println(id + " dad");
        return id;
    }

    @Override
    public boolean add(Registration registration) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(registration);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(Registration registration) {
        return false;
    }

    @Override
    public Registration search(String s) {
        return null;
    }

    @Override
    public List<Registration> getAll() {

        return null;
    }
}
