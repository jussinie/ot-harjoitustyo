import hourreporter.dao.DatabaseManager;
import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;
import hourreporter.domain.*;
import hourreporter.ui.UserInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) throws SQLException{
        DatabaseManager db = new DatabaseManager();
        UserDao ud = new UserDao();
        WeekDao wd = new WeekDao();
        db.initiateConnectionToDb();
        Scanner reader = new Scanner(System.in);
        UserService userService = new UserService(ud, wd);
        UserInterface ui = new UserInterface(reader, userService);
        //year.createNewWeek(1, user.getUserNumber());
        //year.createNewWeek(2, user.getUserNumber());
        ui.startUI();
    }
}

