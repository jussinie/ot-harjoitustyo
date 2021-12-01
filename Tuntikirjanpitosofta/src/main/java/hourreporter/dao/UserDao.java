package hourreporter.dao;

import hourreporter.domain.User;

import java.sql.*;
import java.util.List;

public class UserDao implements Dao<User, Integer> {

    @Override
    public void create(User user) throws SQLException {

    }

    @Override
    public User read(Integer key) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Users WHERE id = ?");
        ps.setInt(1, key);
        ResultSet rs = ps.executeQuery();

        User user = new User(rs.getString("firstName"),
                rs.getString("lastName"), rs.getString("username"),
                rs.getString("role"), rs.getString("team"));
        ps.close();
        rs.close();
        return user;
    }

    @Override
    public User update(Integer key) {
        return null;
    }

    @Override
    public List<User> list() throws SQLException {
        return null;
    }

}
