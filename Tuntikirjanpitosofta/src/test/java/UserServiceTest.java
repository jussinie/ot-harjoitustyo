import hourreporter.dao.FakeWeekDao;
import hourreporter.dao.UserDao;
import hourreporter.domain.User;
import hourreporter.domain.UserService;
import hourreporter.domain.UserServiceOld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UserServiceTest {

    // Tips on how to test printing: https://www.baeldung.com/java-testing-system-out-println

    UserService us;
    UserDao ud;
    FakeWeekDao fwd;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        us = new UserService(ud, fwd);
        us.users.put("testPerson", new User("test", "person", "testPerson", "admin", "adminTeam"));
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void userServiceIsCreatedCorrectly() {
        assertEquals("test", us.users.get("testPerson").getFirstName());
        assertEquals("person", us.users.get("testPerson").getLastName());
    }

    @Test
    void getUsersWorks() {
        User user = us.users.get("testPerson");
        assertEquals("test", user.getFirstName());
    }

    @AfterEach
    public void restoreOut() {
        System.setOut(standardOut);
    }


}
