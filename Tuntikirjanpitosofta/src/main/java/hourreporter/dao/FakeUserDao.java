package hourreporter.dao;

import hourreporter.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FakeUserDao extends UserDao {

    ArrayList<User> users = new ArrayList<>();

    public FakeUserDao() {
        users = new ArrayList<>();
    }

    public void create(User user) {
        users.add(user);
    }

    public User read(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;

    }
}
