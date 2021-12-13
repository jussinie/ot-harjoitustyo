import hourreporter.dao.DatabaseManager;
import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;
import hourreporter.domain.*;
import hourreporter.ui.UserInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) throws SQLException {
        DatabaseManager db = new DatabaseManager();
        UserDao ud = new UserDao();
        WeekDao wd = new WeekDao();
        db.initiateConnectionToDb();
        Scanner reader = new Scanner(System.in);
        //UserServiceOld userService = new UserServiceOld(ud, wd);
        //UserInterface ui = new UserInterface(reader, userService);
        //ui.startUI();
    }
}

