package domainTests;

import hourreporter.dao.WeekDao;
import hourreporter.domain.Day;
import hourreporter.domain.User;
import hourreporter.domain.Week;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    User u;
    Week w;

    @BeforeEach
    void setUp() {
        u = new User("test", "test", "testUser", "test", "test");
        w = new Week(1, u.getUserNumber());
    }

    @Test
    void printWholeWeek() {
    }

    @Test
    void weekIsCreatedWithZeroHours() {
        assertEquals(0, w.countWorkHours());
    }

    @Test
    void hoursReturnedCorrectlyForOneDay() {
        HashMap<String, Day> days = w.getDays();
        days.get("Mon").addTaskToDay("test task", 5.0);
        assertEquals(5.0, w.getWeeksHoursByDay()[0]);
    }

    @Test
    void weeksHoursReturnedCorrectly() {
        HashMap<String, Day> days = w.getDays();
        days.get("Mon").addTaskToDay("test task", 5.0);
        days.get("Fri").addTaskToDay("test task 2", 5.5);
        assertEquals(10.5, w.countWorkHours());
    }

    /*
    @Test
    void settingNewDayWorks() {
        w.setDay("Mon", 7.5);
        Day d = w.getOneDay("Mon");
        assertEquals("dummy", d.tasksForDay.get(0).getTaskName());
    } */

    @Test
    void setSubmittedWorks() {
        w.setSubmitted();
        assertTrue(w.getSubmitted());
    }

    @Test
    void setAcceptedWorks() {
        w.setAccepted();
        assertTrue(w.getAccepted());
    }

    @Test
    void daysHoursAreReturnedCorrectly() {
        w.setDay("Mon", 5.5);
        assertEquals(5.5, w.getDaysHoursForWeek("Mon"));
    }

    @Test
    void zeroIsReturnedIfDaysGetReturnsNull() {
        assertEquals(0, w.getDaysHoursForWeek("nonexisting"));
    }

}