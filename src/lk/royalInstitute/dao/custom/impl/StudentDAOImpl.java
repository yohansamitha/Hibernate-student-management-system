package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.entity.Student;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student load = session.load(Student.class, s);
        session.delete(load);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student o = (Student) session.get(s, Student.class);
        session.close();
        return o;
    }

    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query from_student = session.createQuery("from Student ");
        List<Student> list = from_student.list();
        return list;
    }

    @Override
    public String getLastID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT student_ID FROM Student ORDER BY student_ID DESC LIMIT 1");
        String id = sqlQuery.uniqueResult().toString();
        System.out.println(id + " dad");
        return id;
    }
}
