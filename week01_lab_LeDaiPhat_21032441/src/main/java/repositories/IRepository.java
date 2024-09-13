package repositories;

import java.util.ArrayList;
import java.util.Optional;

public interface IRepository<T> {
    boolean add(T t);

    boolean update(T t);

    boolean delete(String id);

    T find(String id);

    ArrayList<T> findAll();
}
