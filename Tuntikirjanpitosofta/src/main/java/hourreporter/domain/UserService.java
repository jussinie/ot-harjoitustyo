package hourreporter.domain;

import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UserService {

    public HashMap<String, User> users;
    private UserDao ud;
    private WeekDao wd;

    public UserService(UserDao ud, WeekDao wd) {
        this.users = new HashMap<>();
        this.ud = ud;
        this.wd = wd;
    }

    public User createNewUser(User user) {
        if (user != null) {
            try {
                ud.create(user);
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
            return user;
        }
        return null;
    }

    public Year loadSavedWeeksForUser(long userNumber, WeekDao wd) throws SQLException {
        Year year = new Year(userNumber);
        List<Week> weeks = wd.list();
        for (Week w : weeks) {
            if (w.getUserNumber() == userNumber) {
                year.createNewWeek(w.getWeekNumber(), userNumber);
                year.getWeek(w.getWeekNumber()).setDay("Mon", w.getOneDay("Mon").getDaysHours());
                year.getWeek(w.getWeekNumber()).setDay("Tue", w.getOneDay("Tue").getDaysHours());
                year.getWeek(w.getWeekNumber()).setDay("Wed", w.getOneDay("Wed").getDaysHours());
                year.getWeek(w.getWeekNumber()).setDay("Thu", w.getOneDay("Thu").getDaysHours());
                year.getWeek(w.getWeekNumber()).setDay("Fri", w.getOneDay("Fri").getDaysHours());
                year.getWeek(w.getWeekNumber()).setDay("Sat", w.getOneDay("Sat").getDaysHours());
                year.getWeek(w.getWeekNumber()).setDay("Sun", w.getOneDay("Sun").getDaysHours());
            }
        }
        return year;
    }

    public HashMap<String, User> getUsers() {
        return this.users;
    }

    public UserDao getUd() {
        return ud;
    }

    public WeekDao getWd() {
        return wd;
    }

}
