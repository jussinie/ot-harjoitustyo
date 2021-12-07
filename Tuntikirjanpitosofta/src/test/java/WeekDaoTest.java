import hourreporter.dao.FakeWeekDao;
import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;
import hourreporter.domain.Week;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeekDaoTest {

    Connection dbConn;
    WeekDao wd;
    FakeWeekDao fwd = new FakeWeekDao();
    UserDao ud = new UserDao();

    @BeforeEach
    public void initiateConnectionToDb() throws SQLException {
        wd = new WeekDao();
        Week w = new Week(1, 1);
        try {
            dbConn = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
            Statement s = dbConn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday INTEGER, tuesday INTEGER, wednesday INTEGER, thursday INTEGER, friday INTEGER, saturday INTEGER, sunday INTEGER)");
            PreparedStatement ps = dbConn.prepareStatement("INSERT INTO Weeks"
                    + " (userNumber, weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setLong(1, 1L);
            ps.setInt(2, 1);
            ps.setDouble(3, 7.5);
            ps.setDouble(4, 7.5);
            ps.setDouble(5, 2.5);
            ps.setDouble(6, 3.5);
            ps.setDouble(7, 0.0);
            ps.setDouble(8, 0.0);
            ps.setDouble(9, 0.0);

            ps.executeUpdate();
            ps.close();
            dbConn.close();
        } catch (Exception e) {
        }
    }

    @Test
    void readingListWorks() throws SQLException {
        List<Week> weeks = wd.list();
        assertEquals(1, weeks.size());
    }

    /*
    @Test
    public void updateTest() throws SQLException {
        //PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Weeks WHERE username = ?");
        //ps.setInt(1, 1);
        PreparedStatement ps = dbConn.prepareStatement("UPDATE Weeks SET monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ? WHERE userNumber = ? AND weekNumber = ?");
        ps.setDouble(1, 3.5);
        ps.setDouble(2, 2.5);
        ps.setDouble(3, 7.5);
        ps.setDouble(4, 2.0);
        ps.setDouble(5, 4.5);
        ps.setDouble(6, 5.0);
        ps.setDouble(7, 1.0);
        ResultSet rs = ps.executeQuery();
        ps.close();
        rs.close();
        //assertEquals(3.5, );
    } */

    @AfterAll
    static void cleanUp() throws SQLException {
        try {
            Connection dbConn = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
            Statement s = dbConn.createStatement();
            s.execute("DROP TABLE Weeks");
            dbConn.close();
        } catch (Exception e) {

        }
    }

}
