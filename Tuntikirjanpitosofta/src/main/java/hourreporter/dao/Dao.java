package hourreporter.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, K, P> {
    void create(T object) throws SQLException;
    T read(K key) throws SQLException;
    T update(T object, K key, P key2) throws SQLException;
    List<T> list() throws SQLException;
}
