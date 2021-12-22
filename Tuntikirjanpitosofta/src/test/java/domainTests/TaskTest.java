package domainTests;

import hourreporter.domain.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    void taskIsCreatedCorrectly() {
        Task t = new Task("coding", 4.0);
        assertEquals(4.0, t.getHours());
        assertEquals("coding", t.getTaskName());
    }

}
