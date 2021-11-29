package hourReporter.domain;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserService {

    private HashMap<String, User> users;
    private HashMap<String, String> userCommands;

    public UserService() {
        this.users = new HashMap<>();
        this.userCommands = new HashMap<>();

        userCommands.put("1", "Existing user - log in");
        userCommands.put("2", "Create new user");
        userCommands.put("0", "Quit program");
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

    public User createNewUser() {
        Scanner reader = new Scanner(System.in);
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

    public boolean checkIfUserExistsInFile(String username) {
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

    public User login() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Program started...");
        System.out.println("");
        while(true) {
            getUserCommands();
            System.out.print("Select the number of command you want to run: ");
            String userCommandInput = reader.nextLine();
            if (userCommandInput.equals("0")) {
                System.out.println("Exiting program...");
                System.exit(0);
            } else if (userCommandInput.equals("1")) {
                while (true) {
                    System.out.println("Give your username");
                    String usernameInput = reader.nextLine();
                    if (checkIfUserExistsInFile(usernameInput)) {
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

    public void getUserCommands() {
        for (Map.Entry<String, String> entry : userCommands.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
