package hourreporter.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, K> {
    void create(T object) throws SQLException;
    T read(K key) throws SQLException;
    T update(K key) throws SQLException;
    List<T> list() throws SQLException;
}
