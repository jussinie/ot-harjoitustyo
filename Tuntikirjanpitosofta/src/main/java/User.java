public class User {

    private String firstName;
    private String lastName;
    private String role;
    private String team;

    public User(String firstName, String lastName, String role, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}