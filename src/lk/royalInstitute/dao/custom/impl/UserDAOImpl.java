package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.UserDAO;
import lk.royalInstitute.entity.User;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws RuntimeException {
        System.out.println(user.getUserID());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, s);
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String s) {
        return null;
    }

    @Override
    public List<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query from_user = session.createQuery("from User");
        List<User> list = from_user.list();
        return list;
    }

    @Override
    public String getLastID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT userID FROM User ORDER BY userID DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        System.out.println(id + " dad");
        return id;
    }

    @Override
    public Object[] getUser(String userName) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("select userName,password from User where userName=?1");
        query.setParameter(1, userName);
        Object[] user = (Object[]) query.uniqueResult();
        System.out.println(user[0] + " - " + user[1]);
        return user;
    }
}
