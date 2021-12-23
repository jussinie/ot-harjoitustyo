package hourreporter.domain;

import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;
import java.sql.SQLException;
import java.util.*;

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

    /**
     * Method creates an instance of User class, attaches this to UserService instance and saves the user to the database.
     *
     * @param firstName first name of the user.
     * @param lastName last name of the user.
     * @param username username of the user.
     * @param role User's role at work.
     * @param team User's team at work.
     */
    public String createUser(String firstName, String lastName, String username, String role, String team) {
        User inputtedUser = new User(firstName, lastName, username, role, team);
        try {
            User existingUser = ud.read(inputtedUser.getUsername(), 0L);
            if (existingUser == null) {
                if (firstName.length() < 2 || lastName.length() < 2 || username.length() < 2) {
                    return "checkInput";
                } else {
                    ud.create(inputtedUser);
                    this.user = inputtedUser;
                    return "ok";
                }
            } else {
                return "userExists";
                }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "checkInput";
    }

    /**
     * This method reads the user from database, using the provided username and attaches the User instance to the UserService instance.
     * If the username doesn't exist, nothing is read from the database.
     *
     * @param username user's username.
     */
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

    /**
     *
     * @param userNumber
     * @return
     * @throws SQLException if the SQL operation fails.
     */
    public Year loadSavedWeeksForUser(long userNumber) throws SQLException {
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

    /**
     * Method to return a list of all weeks for user who has logged in.
     * @return List of Week instances.
     * @throws SQLException if the SQL operation fails.
     */
    public List<Week> getAllWeeks(Long userNumber) throws SQLException {
        List<Week> allWeeks = wd.list();
        List<Week> weeksForLoggedUser = new ArrayList<>();
        for (Week w : allWeeks) {
            if (w.getUserNumber() == userNumber) {
                weeksForLoggedUser.add(w);
            }
        }
        Collections.sort(weeksForLoggedUser);
        return weeksForLoggedUser;
    }

    /**
     * Method to create a Week instance and save it into the database.
     * @param weekNumber Integer value for week number.
     * @throws SQLException if the SQL operation fails.
     */
    public void createWeek(int weekNumber) throws SQLException {
        Year year = loadSavedWeeksForUser(user.getUserNumber());
        if (weekNumber > 0 && weekNumber < 53) {
            if (wd.read(weekNumber, user.getUserNumber()) == null) {
                year.createNewWeek(weekNumber, user.getUserNumber());
                wd.create(new Week(weekNumber, user.getUserNumber()));
            }
            this.week = year.getWeek(weekNumber);
        }
    }

    /**
     * This method pulls a Week instance from the database and attaches it as a variable to UserService instance,
     * i.e. for the logged user.
     * @param weekNumber Integer value to identify the week that has to be loaded from the database.
     * @throws SQLException if the SQL operation fails.
     */
    public void openExistingWeek(int weekNumber) throws SQLException {
        Year year = loadSavedWeeksForUser(user.getUserNumber());
        if (weekNumber > 0 && weekNumber < 53) {
            if (wd.read(weekNumber, user.getUserNumber()) == null) {
                week = year.createNewWeek(weekNumber, user.getUserNumber());
            } else {
                week = year.getWeek(weekNumber);
            }
        }
    }

    /**
     * Method to check the double input for hours. Returns true if there are erroneous inputs,
     * i.e. not all the given strings can be parsed as double.
     * @param hours inputted string for reported hours. Should be in format X.X
     * @return true if input is in correct format, false if input is in incorrect format.
     */
    public boolean inspectInput(HashMap<String, String> hours) {
        boolean correct = true;
        for (Map.Entry entry : hours.entrySet()) {
            try {
                Double.parseDouble(entry.getValue().toString());
            } catch (Exception e) {
                correct = false;
            }
        }
        return correct;
    }

    /**
     * Method to save Week instance to the database. Method checks if week exists
     * in the database and updates the row if it does. Otherwise new row will be created.
     * @param week
     * @throws SQLException if the SQL operation fails.
     */
    public void saveHours(Week week) throws SQLException {
        Week oldWeek = wd.read(week.getWeekNumber(), week.getUserNumber());
        if (oldWeek != null) {
            wd.update(week, week.getWeekNumber(), week.getUserNumber());
        } else {
            createWeek(week.getWeekNumber());
        }
    }

    /**
     * Method to return the User object attached to the UserService object.
     * @return User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Method to return the Week object attached to the UserService object.
     * @return Week object.
     */
    public Week getWeek() {
        return week;
    }

    /**
     * Method to set the User object in this UserService instance to null.
     * Together with controllers returning the Scene to the landing page this effectively "logs out" the current user.
     */
    public void logout() {
        this.user = null;
    }

    /**
     * Method to attach the current Week instance as a variable for this UserService object.
     * Enables the maintenance of this Week instance.
     * @param week Week instance that will be attached as a variable for this UserService instance.
     */
    public void setWeek(Week week) {
        this.week = week;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
