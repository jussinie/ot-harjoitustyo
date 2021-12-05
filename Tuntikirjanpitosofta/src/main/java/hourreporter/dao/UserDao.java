package hourreporter.dao;

import hourreporter.domain.User;

import java.sql.*;
import java.util.List;

public class UserDao implements Dao<User, String> {

    @Override
    public void create(User user) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
        PreparedStatement ps = c.prepareStatement("INSERT INTO Users"
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
        c.close();
    }

    @Override
    public User read(String username) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Users WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
                rs.getString("role"), rs.getString("team"));
        ps.close();
        rs.close();
        return user;
    }

    @Override
    public User update(String username) {
        return null;
    }

    @Override
    public List<User> list() throws SQLException {
        return null;
    }

}
