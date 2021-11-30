import hourreporter.domain.FileService;
import hourreporter.domain.User;
import hourreporter.domain.UserService;
import hourreporter.ui.UserInterface;

import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        UserService userService = new UserService();
        UserInterface ui = new UserInterface(reader, userService);
        userService.initiateUserList();
        FileService fileService = new FileService();
        User user = userService.login();
        ui.startUI(user, fileService);
    }
}

