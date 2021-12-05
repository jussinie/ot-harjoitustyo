package hourreporter.domain;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String team;
    private boolean isTeamLead;
    private long userNumber;
    private Year year;

    public User(String firstName, String lastName, String username, String role, String team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.team = team;
        this.isTeamLead = false;
        String fullName = firstName.concat(lastName).concat(username);
        this.userNumber = fullName.hashCode();
        this.year = new Year(userNumber);
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

    public String getUsername() {
        return this.username;
    }

    public long getUserNumber() {
        return this.userNumber;
    }

    public boolean getIsTeamLead() {
        return this.isTeamLead;
    }

    public void setIsTeamLead() {
        this.isTeamLead = true;
    }
}