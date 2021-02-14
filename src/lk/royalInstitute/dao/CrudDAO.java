package lk.royalInstitute.dao;

import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO {
    boolean add(T t) throws RuntimeException;

    boolean delete(ID id);

    boolean update(T t);

    T search(ID id);

    List<T> getAll();
}
