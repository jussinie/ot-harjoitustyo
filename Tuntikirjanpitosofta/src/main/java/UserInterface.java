import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {

    private Scanner reader;
    private HashMap<String, String> userCommands;
    private HashMap<String, String> commands;

    public UserInterface(Scanner reader) {
        this.reader = reader;
        commands = new HashMap();
        userCommands = new HashMap<>();

        userCommands.put("1", "Existing user - log in");
        userCommands.put("2", "Create new user");
        userCommands.put("0", "Quit program");

        commands.put("1", "See your reported hours");
        commands.put("2", "Create a new sheet");
        commands.put("3", "Print hours worked");
        commands.put("4", "Report hours");
        commands.put("0", "Quit program");
    }

    public User login() {
        System.out.println("Program started...");
        System.out.println("");
        while(true) {
            for (Map.Entry<String, String> entry : userCommands.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.print("Select the number of command you want to run: ");
            String userCommandInput = reader.nextLine();
            if (userCommandInput.equals("0")) {
                System.out.println("Exiting program...");
                System.exit(0);
            } else if (userCommandInput.equals("1")) {
                User user = loginUsingOldUser();
                try {
                    TimeUnit.SECONDS.sleep(1);
                    return user;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            } else if (userCommandInput.equals("2")) {
                User user = loginUsingOldUser();
                return user;
            }
        }
        return new User("", "", "", "");
    }

    public void startUI(User user) {
        Week week = new Week();
        while(true) {
            System.out.println("Logged in as " + user.getFirstName() + " " + user.getLastName());
            System.out.println("Available commands:");
            System.out.println("****************");
            for (Map.Entry<String, String> entry : commands.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println("****************");
            System.out.print("Select the number of command you want to run: ");
            String input = reader.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting program...");
                break;
            } else if (input.equals("1")) {
                week.printWholeWeek();
            } else if (input.equals("3")) {
                System.out.println(week.countWorkHours());
            } else if (input.equals("2")) {
                System.out.println("What week you want to create? ");
                int weekNr = Integer.valueOf(reader.nextLine());
                System.out.println("Creating week " + weekNr);
            } else if (input.equals("4")) {
                addHours(week);
            }
            //for (int i = 0; i < 49; i++) System.out.println("");
        }
    }

    private void addHours(Week week) {
        System.out.println("");
        for (int i = 0; i < 7; i++) {
            System.out.print(week.weekdays[i] + " | ");
        }
        System.out.println("");
        boolean exist = false;
        boolean cancel = false;
        String weekInput = "";
        while (!exist) {
            System.out.println("Select day to which report hours:");
            System.out.println("Cancel with 0");
            weekInput = reader.nextLine();
            for (int i = 0; i < 7; i++) {
                if (weekInput.equals(week.weekdays[i])) {
                    exist = true;
                }
            }
            if (weekInput.equals("0")) {
                cancel = true;
                break;
            }
        }
        if (!cancel) {
            Day day = week.getOneDay(weekInput);
            System.out.println("Please insert your task: ");
            String taskName = reader.nextLine();
            System.out.println("Please state hours worked (in form x.x).");
            double workedHours = Double.valueOf(reader.nextLine());
            day.addTaskToDay(taskName, workedHours);
        }
    }

    private static User loginUsingOldUser() {
        return new User("Teppo", "Testikäyttäjä", "Basic", "Test Team");
    }
}
