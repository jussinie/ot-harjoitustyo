package hourReporter.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String team;

    public User(String firstName, String lastName, String username, String role, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.team = team;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRole() {
        return this.role;
    }

    public String getTeam() {
        return this.team;
    }

    public void saveUser() throws IOException {
        String filename = "users.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.append(firstName+","+lastName+","+username+","+role+","+team);
        writer.newLine();
        writer.close();
    }

}