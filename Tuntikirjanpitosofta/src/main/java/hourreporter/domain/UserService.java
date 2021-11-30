package hourreporter.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserService {

    private HashMap<String, User> users;
    private HashMap<String, String> userCommands;
    private FileService fileService;

    public UserService() {
        this.users = new HashMap<>();
        this.userCommands = new HashMap<>();
        this.fileService = new FileService();

        userCommands.put("1", "Existing user - log in");
        userCommands.put("2", "Create new user");
        userCommands.put("0", "Quit program");
    }

    public void initiateUserList() {
        users = fileService.readExistingUsersToHashMap();
    }

    public void loadAllSavedHours() {

    }

    public User createNewUser() {
        Scanner reader = new Scanner(System.in);
        System.out.println("First Name");
        String firstName = reader.nextLine();
        System.out.println("Last Name");
        String lastName = reader.nextLine();
        System.out.println("Username");
        String username = reader.nextLine();
        System.out.println("Role");
        String role = reader.nextLine();
        System.out.println("Team");
        String team = reader.nextLine();
        User user = new User(firstName, lastName, username, role, team);
        System.out.println("Are you team lead? Yes / No ");
        String answer = reader.nextLine();
        if (answer.equals("Yes") || answer.equals("yes")) {
            user.setIsTeamLead();
        }
        try {
            saveUser(user, fileService);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return user;
    }

    public boolean checkIfUserExistsInFile(String username) {
        ArrayList<String> users = fileService.readExistingUsersToArrayList();
        if (users.contains(username)) {
            return true;
        }
        return false;
    }

    public User login() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Program started...");
        System.out.println("");
        while (true) {
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
                        String usernameAgainOrExit = reader.nextLine();
                        if (usernameAgainOrExit.equals("0")) {
                            break;
                        }
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

    public static void saveUser(User user, FileService fileService) throws IOException {
        fileService.writeUserToFile(user);
    }

}
