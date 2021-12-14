package hourreporter.domain;

/**
 * This class provides means to represent and save the users of HourReporter software.
 */
public class User {

    /**
     * First name of the user.
     */
    private String firstName;
    /**
     * Last name of the user.
     */
    private String lastName;
    /**
     * Username of the user.
     */
    private String username;
    /**
     * Work role of the user.
     */
    private String role;
    /**
     * Team where the users works.
     */
    private String team;
    /**
     * Is user a team lead? True if yes, false if no.
     */
    private boolean isTeamLead;
    /**
     * Number that is created by calculating a hash code from last name and username.
     */
    private long userNumber;
    /**
     * Year instance that holds all the weeks for this user.
     */
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

    /**
     * Returns user's first name.
     * @return User's first name.
     */
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * Returns user's last name.
     * @return User's last name.
     */
    public String getLastName() {
        return this.lastName;
    }
    /**
     * Returns user's role
     * @return User's role.
     */
    public String getRole() {
        return this.role;
    }
    /**
     * Returns user's team.
     * @return User's team.
     */
    public String getTeam() {
        return this.team;
    }
    /**
     * Returns user's username.
     * @return User's username.
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Returns user's user number. Hash code created from user's last name and username.
     * @return User's user number.
     */
    public long getUserNumber() {
        return this.userNumber;
    }
    /**
     * Returns the boolean value to indicate if user is a team lead or not.
     * @return True if user is team lead, otherwise false.
     */
    public boolean getIsTeamLead() {
        return this.isTeamLead;
    }
    /**
     * Sets boolean value isTeamLead to true to indicate that user is team lead.
     */
    public void setIsTeamLead() {
        this.isTeamLead = true;
    }
}