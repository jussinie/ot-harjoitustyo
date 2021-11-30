import hourreporter.domain.Day;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    @Test
    void dayIsCreatedWithZeroTasks() {
        Day d = new Day();
        assertEquals(0, d.tasksForDay.size());
    }

    @Test
    void hoursAreCalculatedCorrectly() {
        Day d = new Day();
        d.addTaskToDay("test", 2.5);
        d.addTaskToDay("another test", 2.5);
        assertEquals(5, d.getDaysHours());
    }

}