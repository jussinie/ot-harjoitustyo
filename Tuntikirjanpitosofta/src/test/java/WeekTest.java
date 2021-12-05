import hourreporter.domain.User;
import hourreporter.domain.Week;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    @Test
    void printWholeWeek() {
    }

    @Test
    void weekIsCreatedWithZeroHours() {
        User user = new User("test", "test", "testUser", "test", "test");
        Week w = new Week(1, user.getUserNumber());
        assertEquals(0, w.countWorkHours());
    }
}