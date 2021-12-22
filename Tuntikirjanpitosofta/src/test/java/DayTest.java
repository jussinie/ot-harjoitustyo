import hourreporter.domain.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    Day d;

    @BeforeEach
    void setUp() {
        d = new Day();
    }

    @Test
    void dayIsCreatedWithZeroTasks() {
        assertEquals(0, d.tasksForDay.size());
    }

    @Test
    void hoursAreCalculatedCorrectly() {
        d.addTaskToDay("test", 2.5);
        assertEquals(2.5, d.getDaysHours());
    }

    @Test
    void tasksAreAddedCorrectly() {
        d.addTaskToDay("test task", 7.5);
        assertEquals("test task", d.tasksForDay.get(0).getTaskName());
        assertEquals(7.5, d.tasksForDay.get(0).getHours());
    }
}