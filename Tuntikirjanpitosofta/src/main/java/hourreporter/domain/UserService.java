package hourreporter.domain;

import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * This class provides all the services and methods for the UI. It is also used to access the database through the DAO
 * objects that are injected to it upon creation.
 */
public class UserService {

    public HashMap<String, User> users;
    private UserDao ud;
    private WeekDao wd;
    private User user;
    private Week week;

    public UserService(UserDao ud, WeekDao wd) {
        this.users = new HashMap<>();
        this.ud = ud;
        this.wd = wd;
    }

    public void createUser(String firstName, String lastName, String username, String role, String team) {
        User inputtedUser = new User(firstName, lastName, username, role, team);
        try {
            User existingUser = ud.read(inputtedUser.getUsername(), 0L);
            System.out.println("Reading works");
            if (existingUser == null) {
                ud.create(inputtedUser);
                this.user = inputtedUser;
                System.out.println("User created succesfully!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void login(String username) {
        try {
            User user = ud.read(username, 0L);
            if (user != null) {
                this.user = user;
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // Milloin tämä suoritetaan?
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

    public List<Week> getAllWeeks() throws Exception {
        List<Week> weeks = wd.list();
        return weeks;
    }

    public void createWeek(int weekNumber) throws SQLException {
        Year year = loadSavedWeeksForUser(user.getUserNumber(), getWd());
        if (weekNumber > 0 && weekNumber < 53) {
            if (wd.read(weekNumber, user.getUserNumber()) == null) {
                year.createNewWeek(weekNumber, user.getUserNumber());
                wd.create(new Week(weekNumber, user.getUserNumber()));
            }
            this.week = year.getWeek(weekNumber);
        }
    }

    public void openExistingWeek(UserService userService, int weekNumber) throws SQLException {
        Year year = userService.loadSavedWeeksForUser(user.getUserNumber(), userService.getWd());
        if (weekNumber > 0 && weekNumber < 53) {
            if (wd.read(weekNumber, user.getUserNumber()) == null) {
                week = year.createNewWeek(weekNumber, user.getUserNumber());
            } else {
                week = year.getWeek(weekNumber);
            }
        }
    }

    public void saveHours(Week week) throws SQLException {
        Week oldWeek = wd.read(week.getWeekNumber(), week.getUserNumber());
        if (oldWeek != null) {
            wd.update(week, week.getWeekNumber(), week.getUserNumber());
        } else {
            createWeek(week.getWeekNumber());
        }
    }

    /*
    public Week selectWeek(User user, UserService us) throws SQLException {
        Year year = us.loadSavedWeeksForUser(user.getUserNumber(), us.getWd());
        while (true) {

        } else if (input.equals("2")) {
            if (!year.printCreatedWeeks()) {
                System.out.println("Which week you want to select? (1-52)");
                System.out.println("If you select something that doesn't exist, that week will be created.");
                String selectWeek = reader.nextLine();
                if (selectWeek.matches("-?\\d+")) {
                    if (Integer.valueOf(selectWeek) > 0 && Integer.valueOf(selectWeek) < 53) {
                        if (year.getWeek(Integer.valueOf(selectWeek)) == null) return year.createNewWeek(Integer.valueOf(selectWeek), user.getUserNumber());
                        else return year.getWeek(Integer.valueOf(selectWeek));
                    }
                }
            } else {
                System.out.println("As there are no weeks created, your selection (1-52) will be first created week.");
                System.out.println("This week will be selected after creation.");
                String selectWeek = reader.nextLine();
                return year.createNewWeek(Integer.valueOf(Integer.valueOf(selectWeek)), user.getUserNumber());
            }
        } else if (input.equals("1")) {
            System.out.println("For which week you want to create your sheet? (1-52)");
            System.out.println("You have created these weeks already:");
            year.printCreatedWeeks();
            System.out.println("This week will be selected after creation.");
            String weekNr = reader.nextLine();
            return year.createNewWeek(Integer.valueOf(weekNr), user.getUserNumber());
        }
    } */

    public HashMap<String, User> getUsers() {
        return this.users;
    }

    public UserDao getUd() {
        return ud;
    }

    public WeekDao getWd() {
        return wd;
    }

    public User getUser() {
        return user;
    }

    public Week getWeek() {
        return week;
    }

    public void logout() {
        this.user = null;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

}
