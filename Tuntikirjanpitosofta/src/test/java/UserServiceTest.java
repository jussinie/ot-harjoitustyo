import hourreporter.dao.DatabaseManager;
import hourreporter.dao.FakeUserDao;
import hourreporter.dao.FakeWeekDao;
import hourreporter.dao.UserDao;
import hourreporter.domain.User;
import hourreporter.domain.UserService;
import hourreporter.domain.Week;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class UserServiceTest {

    // Tips on how to test printing: https://www.baeldung.com/java-testing-system-out-println

    UserService us;
    FakeUserDao fud = new FakeUserDao();
    FakeWeekDao fwd = new FakeWeekDao();
    //private final PrintStream standardOut = System.out;
    //private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
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
        //System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    void creatingUserWorks() {
        String firstName = "test";
        String lastName = "person2";
        String username = "testPerson2";
        String role = "admin";
        String team = "test team";
        User user1 = new User(firstName, lastName, username, role, team);
        assertNull(fud.read(username));
        fud.create(user1);
        User user2 = fud.read(username);
        assertEquals(user1.getFirstName(), user2.getFirstName());
    }

    @Test
    void loginWorks() {
        us.login("testPerson");
        assertEquals("testPerson", us.getUser().getUsername());
    }

    @Test
    void openingExistingWeekWorks() throws SQLException {
        us.openExistingWeek(1);
        assertEquals(0.0, us.getWeek().countWorkHours());
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
    void savingHoursWorksAsExpected() {

    }

    /*
    @Test
    void userServiceIsCreatedCorrectly() {
        assertEquals("test", us.users.get("testPerson").getFirstName());
        assertEquals("person", us.users.get("testPerson").getLastName());
    }

    /*
    @Test
    void getUsersWorks() {
        User user = us.users.get("testPerson");
        assertEquals("test", user.getFirstName());
    } */

    /*
    @AfterEach
    public void restoreOut() {
        System.setOut(standardOut);
    } */

}
