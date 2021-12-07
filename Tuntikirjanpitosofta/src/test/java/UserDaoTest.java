import hourreporter.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

public class UserDaoTest {

    Connection dbConn;

    @BeforeEach
    public void initiateConnectionToDb() throws SQLException {
        try {
            dbConn = DriverManager.getConnection("jdbc:sqlite:hourreporterTest.db");
            Statement s = dbConn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("INSERT INTO Users (firstName, lastName, username, role, team, isTeamLead, userNumber) VALUES ('test', 'person', 'testPerson', 'admin', 'adminTeam', false, -2107266002)");
        } catch (Exception e) {
        }
    }

    @Test
    public void readTest() throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Users WHERE username = ?");
        ps.setString(1, "testPerson");
        ResultSet rs = ps.executeQuery();
        User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"),
                rs.getString("role"), rs.getString("team"));
        ps.close();
        rs.close();
        assertEquals("test", user.getFirstName());
        assertEquals("person", user.getLastName());
        assertEquals("testPerson", user.getUsername());
        assertEquals("admin", user.getRole());
        assertEquals("adminteam", user.getTeam());
        assertEquals(-2107266002L, user.getUserNumber());
        assertEquals(false, user.getIsTeamLead());
    }
}
