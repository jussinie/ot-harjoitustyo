package daoTests;

import hourreporter.dao.DatabaseSelector;
import hourreporter.dao.WeekDao;
import hourreporter.domain.Week;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.util.List;


public class WeekDaoTest {

    WeekDao wd;
    Week testWeekForCreate;

    public WeekDaoTest() throws SQLException {
        testWeekForCreate = new Week(52, 2L);
        testWeekForCreate.setDay("Mon", 7.5);
        testWeekForCreate.setDay("Tue", 1.5);
        testWeekForCreate.setDay("Wed", 5.5);
        testWeekForCreate.setDay("Thu", 3.5);
        testWeekForCreate.setDay("Fri", 6.0);
        testWeekForCreate.setDay("Sat", 3.0);
        testWeekForCreate.setDay("Sun", 5.0);
        DatabaseSelector dbs = new DatabaseSelector();
        String databaseString = dbs.getConnectionString("test");
        wd = new WeekDao(databaseString);
        Week week = new Week(1, 1);
        week.setDay("Mon", 7.5);
        week.setDay("Tue", 7.5);
        week.setDay("Wed", 2.5);
        week.setDay("Thu", 3.5);
        week.setDay("Fri", 0.0);
        week.setDay("Sat", 0.0);
        week.setDay("Sun", 0.0);
        wd.create(week);
        Week week2 = new Week(2, 1);
        week2.setDay("Mon", 7.5);
        week2.setDay("Tue", 7.5);
        week2.setDay("Wed", 2.5);
        week2.setDay("Thu", 3.5);
        week2.setDay("Fri", 2.0);
        week2.setDay("Sat", 2.0);
        week2.setDay("Sun", 2.0);
        wd.create(week2);
    }

    @Test
    public void readingFromDBWorks() throws SQLException {
        Week week = wd.read(52, 2L);
        //assertEquals(-2107266002L, user.getUserNumber());
        assertEquals(52, week.getWeekNumber());
    }

    @Test
    public void nonExistingWeekDoesNotGetReturned() throws SQLException {
        Week week = wd.read(17, 1L);
        assertNull(week);
    }

    @Test
    public void creatingWeekWorks() throws SQLException {
        wd.create(testWeekForCreate);
        PreparedStatement ps = wd.getDbConn().prepareStatement("SELECT * FROM Weeks WHERE weekNumber = 52 AND userNumber = 2");
        ResultSet rs = ps.executeQuery();
        Week week = new Week(rs.getInt("weekNumber"), rs.getLong("userNumber"));
        week.setDay("Mon", rs.getDouble("monday"));
        week.setDay("Tue", rs.getDouble("tuesday"));
        week.setDay("Wed", rs.getDouble("wednesday"));
        week.setDay("Thu", rs.getDouble("thursday"));
        week.setDay("Fri", rs.getDouble("friday"));
        week.setDay("Sat", rs.getDouble("saturday"));
        week.setDay("Sun", rs.getDouble("sunday"));
        ps.close();
        rs.close();
        assertEquals(52, week.getWeekNumber());
    }


    @Test
    public void updateWeekReturnsUpdatedWeek() throws SQLException {
        testWeekForCreate.setDay("Sun", 7.5);
        wd.update(testWeekForCreate, 52, 2L);
        Week updatedWeekFromDB = wd.read(52, 2L);
        assertEquals(7.5, updatedWeekFromDB.getDaysHoursForWeek("Sun"));
    }

    @Test
    public void listReturnsNull() throws SQLException {
        List<Week> weeks = wd.list();
        //assertNull(ud.list());
    }


    @Test
    public void canCreateConnectionToProd() throws SQLException {
        wd = new WeekDao("jdbc:sqlite:hourreporter.db");
        DatabaseMetaData dbmd = wd.getDbConn().getMetaData();
        assertEquals("jdbc:sqlite:hourreporter.db", dbmd.getURL());
    }

}
