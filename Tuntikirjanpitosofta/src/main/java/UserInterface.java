import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {

    private Scanner reader;
    private HashMap<String, String> userCommands;
    private HashMap<String, String> commands;
    private HashMap<String, User> users;

    public UserInterface(Scanner reader) {
        this.reader = reader;
        users = new HashMap<>();
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
                while (true) {
                    System.out.println("Give your username");
                    String usernameInput = reader.nextLine();
                    if (checkIfUserExists(usernameInput)) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            return users.get(usernameInput);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    } else {
                        System.out.println("Username doesn't exist.");
                        System.out.println("0: Exit");
                        System.out.println("1: Try again");
                    }
                }
            } else if (userCommandInput.equals("2")) {
                User user = createNewUser();
                return user;
            }
        }
    }

    public void startUI(User user) {
        Week week = new Week();
        while(true) {
            System.out.println();
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
        System.out.println();
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

    private User createNewUser() {
        System.out.println("firstname");
        String firstName = reader.nextLine();
        System.out.println("lastname");
        String lastName = reader.nextLine();
        System.out.println("username");
        String username = reader.nextLine();
        System.out.println("role");
        String role = reader.nextLine();
        System.out.println("team");
        String team = reader.nextLine();
        User user = new User(firstName, lastName, username, role, team);
        try {
            user.saveUser();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return user;
    }

    public boolean checkIfUserExists(String username) {
        String filename = "users.txt";
        ArrayList<String> usernames = new ArrayList<>();
        try (Scanner fileReader = new Scanner(Paths.get(filename))) {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] parts = row.split(",");
                usernames.add(parts[2]);
            }
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        }
        if (usernames.contains(username)) {
            return true;
        }
        return false;
    }

    public void initiateUserList() {
        String filename = "users.txt";
        try (Scanner fileReader = new Scanner(Paths.get(filename))) {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] parts = row.split(",");
                users.put(parts[2], new User(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        }
    }
}
