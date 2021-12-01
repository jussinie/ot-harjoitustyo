import hourreporter.dao.DatabaseManager;
import hourreporter.dao.UserDao;
import hourreporter.domain.FileService;
import hourreporter.domain.User;
import hourreporter.domain.UserService;
import hourreporter.ui.UserInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) throws SQLException{
        DatabaseManager db = new DatabaseManager();
        UserDao ud = new UserDao();
        db.initiateConnectionToDb();
        Scanner reader = new Scanner(System.in);
        UserService userService = new UserService();
        UserInterface ui = new UserInterface(reader, userService);
        userService.initiateUserList();
        FileService fileService = new FileService();
        User user = userService.login(ud);
        ui.startUI(user, fileService);
    }
}

