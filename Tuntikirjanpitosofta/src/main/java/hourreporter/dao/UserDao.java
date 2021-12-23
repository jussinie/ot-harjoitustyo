package hourreporter.dao;

import hourreporter.domain.User;
import java.sql.*;
import java.util.List;

public class UserDao implements Dao<User, String, Long> {

    private Connection dbConn;

    public UserDao() {

    }

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

    @Override
    public User update(User user, String username, Long userNumber) {
        return null;
    }

    @Override
    public List<User> list() {
        return null;
    }

    public Connection getDbConn() {
        return this.dbConn;
    }

}
