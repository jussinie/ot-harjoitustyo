package daoTests;

import hourreporter.dao.DatabaseSelector;
import hourreporter.dao.UserDao;
import hourreporter.domain.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class UserDaoTest {

    User testUserForCreate;
    UserDao ud;

    public UserDaoTest() {
        testUserForCreate = new User("creation", "testing", "creationTesting", "tester", "test team");
        DatabaseSelector dbs = new DatabaseSelector();
        String databaseString = dbs.getConnectionString("test");
        ud = new UserDao(databaseString);
    }

    @Test
    public void readingFromDBWorks() throws SQLException {
        try {
            Statement s = ud.getDbConn().createStatement();
            s.execute("INSERT INTO Users (firstName, lastName, username, role, team, isTeamLead, userNumber) VALUES ('test', 'person', 'testPerson', 'admin', 'adminteam', false, -2107266002)");
        } catch (Exception e) {
        }
        User user = ud.read("testPerson", -2107266002L);
        assertEquals("testPerson", user.getUsername());
    }

    @Test
    public void nonExistingUserDoesNotGetReturned() throws SQLException {
        User user = ud.read("doesntExist", 1L);
        assertNull(user);
    }

    @Test
    public void creatingUserWorks() throws SQLException {
        ud.create(testUserForCreate);

        PreparedStatement ps = ud.getDbConn().prepareStatement("SELECT * FROM Users WHERE username = 'creationTesting'");
        ResultSet rs = ps.executeQuery();
        User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
                rs.getString("role"), rs.getString("team"));
        ps.close();
        rs.close();
        assertEquals("creationTesting", user.getUsername());
    }

    @Test
    public void updateUserReturnsNull() {
        assertNull(ud.update(testUserForCreate, "creationTesting", 1L));
    }

    @Test
    public void listReturnsNull() {
        assertNull(ud.list());
    }

    @Test
    public void canCreateConnectionToProd() throws SQLException {
        ud = new UserDao("jdbc:sqlite:hourreporter.db");
        DatabaseMetaData dbmd = ud.getDbConn().getMetaData();
        assertEquals("jdbc:sqlite:hourreporter.db", dbmd.getURL());
    }
}
