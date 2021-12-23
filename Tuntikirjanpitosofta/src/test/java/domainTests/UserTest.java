package domainTests;

import hourreporter.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User user;

    @BeforeEach
    public void setUp() {
        user = new User("Test", "Person", "testperson", "sysAdmin", "test team");
    }

    @Test
    void firstNameIsReturnedCorrectly() {
        assertEquals("Test", user.getFirstName());
    }

    @Test
    void lastNameIsReturnedCorrectly() {
        assertEquals("Person", user.getLastName());
    }

    @Test
    void userameIsReturnedCorrectly() {
        assertEquals("testperson", user.getUsername());
    }
}
