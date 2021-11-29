import hourReporter.domain.Week;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    @Test
    void printWholeWeek() {
    }

    @Test
    void weekIsCreatedWithZeroHours() {
        Week w = new Week();
        assertEquals(0, w.countWorkHours());
    }
}