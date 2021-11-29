import hourReporter.domain.User;
import hourReporter.domain.UserService;

import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        UserService userService = new UserService();
        UserInterface ui = new UserInterface(reader, userService);
        userService.initiateUserList();
        User user = userService.login();
        ui.startUI(user);
    }
}

