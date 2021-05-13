package Application.DAO;

import java.util.List;

public interface InterfaceDAO<T> {
        void add(T t);
        void update(T t);
        void delete(T t);
        T getObject(int id);
        List<T> getAllObjects();
}
