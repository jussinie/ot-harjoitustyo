package hourreporter.dao;

import hourreporter.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FakeUserDao extends UserDao {

    ArrayList<User> users;

    public FakeUserDao() {
        users = new ArrayList<>();
    }

    @Override
    public void create(User user) {
        users.add(user);
    }

    @Override
    public User read(String username, Long userNumber) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;

    }
}
