package hourreporter.dao;

import hourreporter.domain.User;
import java.util.ArrayList;

/**
 * Class extending UserDao. To be used in UserService testing as an injection
 * to avoid the need to use database for those test operations.
 */
public class FakeUserDao extends UserDao {

    /**
     * ArrayList to represent database objects (rows).
     */
    ArrayList<User> users;

    public FakeUserDao() {
        users = new ArrayList<>();
    }

    /**
     * Method to create a user, i.e. add user to the arrayList.
     */
    @Override
    public void create(User user) {
        users.add(user);
    }

    /**
     * Method to read one user from the ArrayList.
     * @param username username of the queried user.
     * @param userNumber user number of the queried user.
     * @return a User based on the given username.
     */
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
