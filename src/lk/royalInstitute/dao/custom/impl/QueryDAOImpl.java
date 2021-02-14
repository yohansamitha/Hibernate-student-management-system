package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.QueryDAO;
import lk.royalInstitute.entity.CustomEntity;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> getCourseWiseStudent() {
        String sql = "select r.regNo,\n" +
                "       r.regDate,\n" +
                "       r.regFee,\n" +
                "       s.student_ID,\n" +
                "       s.studentName,\n" +
                "       s.contact,\n" +
                "       s.dob,\n" +
                "       c.course_code,\n" +
                "       c.courseName,\n" +
                "       c.courseFee,\n" +
                "       c.duration\n" +
                "from Registration r\n" +
                "         inner join Student s\n" +
                "                    on r.student.id = s.student_ID\n" +
                "         inner join Course c on r.course.id = c.course_code";
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery(sql);
        List<Object[]> list = query.list();
        ArrayList<CustomEntity> courseWiseStudents = new ArrayList<>();
        for (Object[] object : list) {
            courseWiseStudents.add(new CustomEntity(
                    String.valueOf(object[0]),
                    String.valueOf(object[1]),
                    Double.parseDouble(String.valueOf(object[2])),
                    String.valueOf(object[3]),
                    String.valueOf(object[4]),
                    String.valueOf(object[5]),
                    Date.valueOf(String.valueOf(object[6])),
                    String.valueOf(object[7]),
                    String.valueOf(object[8]),
                    Double.parseDouble(String.valueOf(object[9])),
                    String.valueOf(object[10])
            ));
        }
        return courseWiseStudents;
    }
}
