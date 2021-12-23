package hourreporter.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface that provides the methods to access and modify the database objects.
 * @param <T> General type parameter representing Object.
 * @param <K> General type parameter representing key value in database.
 * @param <P> General type parameter representing other key value in database.
 */
public interface Dao<T, K, P> {
    /**
     * This method creates a new object in the database.
     * @param object returns an object.
     * @throws SQLException if database operation fails for some reason
     */
    void create(T object) throws SQLException;

    /**
     * This method reads one entry from the database that is identified by key and key2.
     * @param key parameter for querying the database.
     * @param key2 second parameter for querying the database.
     * @return an object representing one row that is read from the database.
     * @throws SQLException if database operation fails for some reason.
     */
    T read(K key, P key2) throws SQLException;

    /**
     * This method updates one line in the database, identified by the below mentioned parameters.
     * @param object an object (row) to be updated.
     * @param key parameter used to identify the correct row.
     * @param key2 second parameter used to identify the correct row.
     * @return an object representing the updated row in the database.
     * @throws SQLException if database operation fails for some reason.
     */
    T update(T object, K key, P key2) throws SQLException;

    /**
     * This method returns a list of objects (rows) from the database.
     * @return List of entries.
     * @throws SQLException if database operation fails for some reason.
     */
    List<T> list() throws SQLException;
}
