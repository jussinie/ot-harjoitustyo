import hourreporter.domain.User;
import hourreporter.domain.Year;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YearTest {

    User user;
    Year y;

    @BeforeEach
    public void setUp() {
        user = new User("test", "person", "testPerson", "admin", "adminTeam");
        y = new Year(user.getUserNumber());
    }

    @Test
    public void yearInitiatedCorrectly() {
        assertEquals(53, y.size());
    }

    @Test
    public void newWeekIsCreatedCorrectly() {
        y.createNewWeek(44, user.getUserNumber());
        assertNotNull(y.getWeek(44));
    }

    @Test
    public void newWeekNotCreatedWithSmallerThanZero() {
        assertNull(y.createNewWeek(-1, user.getUserNumber()));
    }

    @Test
    public void newWeekNotCreatedWithBiggerThan52() {
        assertNull(y.createNewWeek(53, user.getUserNumber()));
    }


}
