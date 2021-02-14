package lk.royalInstitute.dao.custom;

import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.entity.CustomEntity;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getCourseWiseStudent();
}
