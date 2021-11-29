import java.util.Scanner;

public class HourReporter {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        UserInterface ui = new UserInterface(reader);
        ui.initiateUserList();
        User user = ui.login();
        ui.startUI(user);
    }
}

