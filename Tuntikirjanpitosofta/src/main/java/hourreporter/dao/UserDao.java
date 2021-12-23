package hourreporter.dao;

import hourreporter.domain.User;
import java.sql.*;
import java.util.List;

/**
 *Class that provides the methods to access and modify User objects in the database.
 */
public class UserDao implements Dao<User, String, Long> {

    private Connection dbConn;

    public UserDao() {

    }

    /**
     * Constructor to create connection to the database and create needed tables if they do not yet exist.
     * @param testOrProd string parameter to define connection to either test or production database.
     */
    public UserDao(String testOrProd) {
        try {
            dbConn = DriverManager.getConnection(testOrProd);
            Statement s = dbConn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday DOUBLE, tuesday DOUBLE, wednesday DOUBLE, thursday DOUBLE, friday DOUBLE, saturday DOUBLE, sunday DOUBLE)");
        } catch (Exception e) {
            System.out.println("UserDao could not connect to DB.");
        }
    }

    /**
     * Method to create new line in the database and populate it with User object data.
     * @param user User object to store in the database.
     * @throws SQLException if SQL query fails to execute for some reason.
     */
    @Override
    public void create(User user) throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO Users"
            + " (firstName, lastName, username, userNumber, role, team, isTeamLead)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getUsername());
        ps.setLong(4, user.getUserNumber());
        ps.setString(5, user.getRole());
        ps.setString(6, user.getTeam());
        ps.setBoolean(7, user.getIsTeamLead());

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Method to read a line from the database to be used in the application.
     * @param username parameter to find the correct user (row) from the database.
     * @param userNumber currently not used in the query.
     * @return User object corresponding with the row data.
     * @throws SQLException if SQL query fails to execute for some reason.
     */
    @Override
    public User read(String username, Long userNumber) throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Users WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }
        User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
                rs.getString("role"), rs.getString("team"));
        ps.close();
        rs.close();
        return user;
    }

    /**
     * Method not in use but has to be defined because of DAO interface.
     * @param user
     * @param username
     * @param userNumber
     * @return
     */
    @Override
    public User update(User user, String username, Long userNumber) {
        return null;
    }

    /**
     * Method not in use but has to be defined because of DAO interface.
     * @return
     */
    @Override
    public List<User> list() {
        return null;
    }

    /**
     * Method to return database connection.
     * @return Connection object.
     */
    public Connection getDbConn() {
        return this.dbConn;
    }

}
