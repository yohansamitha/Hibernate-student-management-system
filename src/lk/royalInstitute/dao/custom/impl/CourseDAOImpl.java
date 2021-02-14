package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.CourseDAO;
import lk.royalInstitute.entity.Course;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course course) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(s + " delete course id ");
        Course load = session.load(Course.class, s);
        session.delete(load);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(course);
        transaction.commit();
        return true;
    }

    @Override
    public Course search(String s) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query from_course = session.createQuery("from Course ");
        List<Course> list = from_course.list();
        return list;
    }

    @Override
    public String getLastID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT course_code FROM Course ORDER BY course_code DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        System.out.println(id + " last Course ID");
        return id;
    }
}
