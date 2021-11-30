package hourreporter.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileService {

    String filePathUsers;
    String filePathHours;

    public FileService() {
        this.filePathUsers = "users.txt";
        this.filePathHours = "hours.txt";
    }

    public HashMap<String, User> readExistingUsersToHashMap() {
        HashMap<String, User> users = new HashMap<>();
        try (Scanner fileReader = new Scanner(Paths.get(filePathUsers))) {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] parts = row.split(",");
                users.put(parts[2], new User(parts[0], parts[1], parts[2], parts[3], parts[4]));
            }
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        }
        return users;
    }

    public ArrayList<String> readExistingUsersToArrayList() {
        ArrayList<String> usernames = new ArrayList<>();
        try (Scanner fileReader = new Scanner(Paths.get(filePathUsers))) {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                String[] parts = row.split(",");
                usernames.add(parts[2]);
            }
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        }
        return usernames;
    }

    public void writeUserToFile(User user) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathUsers, true));
            writer.append(user.getUserNumber() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getUsername() + "," + user.getRole() + "," + user.getTeam() + "," + user.getUserNumber());
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void writeHoursToFile(String str) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePathHours, true));
            writer.append(str);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
