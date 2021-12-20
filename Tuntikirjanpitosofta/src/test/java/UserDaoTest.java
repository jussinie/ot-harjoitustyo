import hourreporter.dao.UserDao;
import hourreporter.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.List;

public class UserDaoTest {

    Connection dbConn;
    User testUserForCreate;
    UserDao ud;

    public UserDaoTest() throws SQLException {
        this.dbConn = DriverManager.getConnection("jdbc:sqlite:hourreporterTest.db");
        testUserForCreate = new User("creation", "testing", "creationTesting", "tester", "test team");
        ud = new UserDao();
    }

    @Before
    public void initiateConnectionToDb() throws SQLException {
        try {
            Statement s = dbConn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("INSERT INTO Users (firstName, lastName, username, role, team, isTeamLead, userNumber) VALUES ('test', 'person', 'testPerson', 'admin', 'adminteam', false, -2107266002)");
        } catch (Exception e) {
        }
    }

    @Test
    public void readingFromDBWorks() throws SQLException {
        User user = ud.read("testPerson", -2107266002L);
        //assertEquals(-2107266002L, user.getUserNumber());
        assertEquals("testPerson", user.getUsername());
    }

    @Test
    public void nonExistingUserDoesNotGetReturned() throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Users WHERE username = ?");
        ps.setString(1, "doesntExist");
        ResultSet rs = ps.executeQuery();
        assertFalse(rs.next());
        ps.close();
        rs.close();
    }

    @Test
    public void creatingUserWorks() throws SQLException {
        ud.create(testUserForCreate);

        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Users WHERE username = 'creationTesting'");
        ResultSet rs = ps.executeQuery();
        User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
                rs.getString("role"), rs.getString("team"));
        ps.close();
        rs.close();
        assertEquals("creationTesting", user.getUsername());
    }

    @AfterAll
    public static void cleanDatabase() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:hourreporterTest.db");
        c.prepareStatement("TRUNCATE TABLE Users");
    }

}
