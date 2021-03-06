package domainTests;

import hourreporter.dao.FakeUserDao;
import hourreporter.dao.FakeWeekDao;
import hourreporter.domain.User;
import hourreporter.domain.UserService;
import hourreporter.domain.Week;
import hourreporter.domain.Year;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceTest {

    // Tips on how to test printing: https://www.baeldung.com/java-testing-system-out-println

    public UserService us;
    FakeUserDao fud = new FakeUserDao();
    FakeWeekDao fwd = new FakeWeekDao();

    public UserServiceTest() {
        us = new UserService(fud, fwd);
        us.createUser("test", "person", "testPerson", "admin", "adminTeam");
        us.setUser(new User("test", "person", "testPerson", "admin", "adminTeam"));
        Week week = new Week(1, 100L);
        week.setDay("Mon", 7.5);
        week.setDay("Tue", 2.5);
        week.setDay("Wed", 7.5);
        week.setDay("Thu", 3.5);
        week.setDay("Fri", 7.5);
        week.setDay("Sat", 0.0);
        week.setDay("Sun", 0.0);
        us.setWeek(week);
        fwd.create(week);
    }

    @Test
    void creatingUserWorks() {
        String firstName = "test";
        String lastName = "person2";
        String username = "testPerson2";
        String role = "admin";
        String team = "test team";
        User user1 = new User(firstName, lastName, username, role, team);
        us.createUser(firstName, lastName, username, role, team);
        assertEquals("person2", fud.read("testPerson2", 1L).getLastName());
    }

    @Test
    void loginWorksWithCorrectUsername() {
        us.login("testPerson");
        assertEquals("testPerson", us.getUser().getUsername());
    }

    @Test
    void inspectInputWorksWithDoubles() {
        HashMap<String, String> hours = new HashMap<>();
        hours.put("Mon", "7.5");
        assertTrue(us.inspectInput(hours));
    }

    @Test
    void inspectInputDoesntWorkWithIncorrectInput() {
        HashMap<String, String> hours = new HashMap<>();
        hours.put("Mon", "test");
        assertFalse(us.inspectInput(hours));
    }

    @Test
    void inspectInputDoesntWorkWithMixedInput() {
        HashMap<String, String> hours = new HashMap<>();
        hours.put("Mon", "test");
        hours.put("Tue", "6.5");
        hours.put("Wed", "3.0");
        assertFalse(us.inspectInput(hours));
    }

    @Test
    void savingHoursWorksForNewWeek() throws SQLException {
        Week newWeek = new Week(43, 3L);
        us.saveHours(newWeek);
        assertEquals(43, fwd.read(43, 3L).getWeekNumber());
    }

    @Test
    void savingHoursWorksForExistingWeek() {
        Week existingWeek = fwd.read(1, 2072563456L);
        existingWeek.setDay("Sun", 7.5);
        fwd.create(existingWeek);
        assertEquals(7.5, fwd.read(1, 2072563456L).getDaysHoursForWeek("Sun"));
    }

    @Test
    void loadingUsersHoursWork() {
        Year year = new Year(100L);
        List<Week> weeks = new ArrayList<>();
        weeks = fwd.list();
        for (Week w : weeks) {
            year.createNewWeek(w.getWeekNumber(), -2107266002);
        }
        assertEquals(0.0, year.getWeek(1).getDaysHoursForWeek("Sun"));
    }

    @Test
    void logoutWorks() {
        us.logout();
        assertNull(us.getUser());
    }
}
