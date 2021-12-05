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
        UserService userService = new UserService();
        UserInterface ui = new UserInterface(reader, userService);
        userService.initiateUserList();
        FileService fileService = new FileService();
        User user = userService.login(ud);
        Year year = new Year(user.getUserNumber());
        year.createNewWeek(1, user.getUserNumber());
        year.createNewWeek(2, user.getUserNumber());
        Week week = ui.selectOrCreateWeek(user, fileService, year);
        ui.startUI(user, fileService, week, wd);
    }
}

