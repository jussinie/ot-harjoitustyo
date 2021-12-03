import hourreporter.dao.DatabaseManager;
import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;
import hourreporter.domain.*;
import hourreporter.ui.UserInterface;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) throws SQLException{
        DatabaseManager db = new DatabaseManager();
        UserDao ud = new UserDao();
        db.initiateConnectionToDb();
        WeekDao wd = new WeekDao();
        Scanner reader = new Scanner(System.in);
        UserService userService = new UserService();
        UserInterface ui = new UserInterface(reader, userService);
        userService.initiateUserList();
        FileService fileService = new FileService();
        User user = userService.login(ud);
        Year year = new Year();
        year.createNewWeek(42, user);
        year.createNewWeek(35, user);
        Week week = ui.selectOrCreateWeek(user, fileService, year);
        ui.startUI(user, fileService, week, wd);
    }
}

