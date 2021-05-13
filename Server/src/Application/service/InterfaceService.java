package Application.service;

import java.util.List;

public interface InterfaceService<T> {
    void add(T t);
    void update(T t);
    void delete(T t);
    T getObject(int id);
    List<T> getAllObjects();
}
