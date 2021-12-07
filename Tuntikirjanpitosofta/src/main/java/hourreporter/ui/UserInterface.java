package hourreporter.ui;

import hourreporter.dao.WeekDao;
import hourreporter.domain.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {

    private Scanner reader;
    private HashMap<String, String> commands;
    private HashMap<String, String> adminCommands;
    private HashMap<String, String> userCommands;
    private UserService us;

    public UserInterface(Scanner reader, UserService us) {
        this.reader = reader;
        commands = new HashMap();
        adminCommands = new HashMap<>();
        userCommands = new HashMap<>();
        this.us = us;

        commands.put("1", "See your reported hours");
        commands.put("2", "Create a new sheet");
        commands.put("3", "Print hours worked");
        commands.put("4", "Report hours");
        commands.put("5", "Save your report");
        commands.put("0", "Quit program");

        adminCommands.put("1", "See your reported hours");
        adminCommands.put("2", "Create a new sheet");
        adminCommands.put("3", "Print hours worked");
        adminCommands.put("4", "Report hours");
        adminCommands.put("5", "See hours from team members || not yet active");
        adminCommands.put("6", "Accept team members hour sheets || not yet active");
        adminCommands.put("0", "Quit program");

        userCommands.put("1", "Existing user - log in");
        userCommands.put("2", "Create new user");
        userCommands.put("0", "Quit program");
    }

    public Week selectOrCreateWeek(User user, UserService us) throws SQLException {
        Year year = us.loadSavedWeeksForUser(user.getUserNumber(), us.getWd());
        while (true) {
            System.out.println("Welcome " + user.getFirstName() + "! What do you want to do?");
            System.out.println("1: Create new week");
            System.out.println("2: Select existing week");
            System.out.println("0: Quit program");
            String input = reader.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting program...");
                break;
            } else if (input.equals("2")) {
                if (!year.printCreatedWeeks()) {
                    System.out.println("Which week you want to select? (1-52)");
                    System.out.println("If you select something that doesn't exist, that week will be created.");
                    String selectWeek = reader.nextLine();
                    if (selectWeek.matches("-?\\d+")) {
                        if (Integer.valueOf(selectWeek) > 0 && Integer.valueOf(selectWeek) < 53) {
                            if (year.getWeek(Integer.valueOf(selectWeek)) == null) return year.createNewWeek(Integer.valueOf(selectWeek), user.getUserNumber());
                            else return year.getWeek(Integer.valueOf(selectWeek));
                        }
                    }
                } else {
                    System.out.println("As there are no weeks created, your selection (1-52) will be first created week.");
                    System.out.println("This week will be selected after creation.");
                    String selectWeek = reader.nextLine();
                    return year.createNewWeek(Integer.valueOf(Integer.valueOf(selectWeek)), user.getUserNumber());
                }
            } else if (input.equals("1")) {
                System.out.println("For which week you want to create your sheet? (1-52)");
                System.out.println("You have created these weeks already:");
                year.printCreatedWeeks();
                System.out.println("This week will be selected after creation.");
                String weekNr = reader.nextLine();
                return year.createNewWeek(Integer.valueOf(weekNr), user.getUserNumber());
            }
        }
        return null;
    }

    public void startUI() throws SQLException {
        User user = login();
        Week week = selectOrCreateWeek(user, us);
        while (true) {
            printInfoAndOptions(user);
            String input = reader.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting program...");
                break;
            } else if (input.equals("1")) {
                week.printWholeWeek();
            } else if (input.equals("2")) {
                selectOrCreateWeek(user, us);
            } else if (input.equals("3")) {
                System.out.println(week.countWorkHours());
            } else if (input.equals("4")) {
                addHours(week);
            } else if (input.equals("5")) {
                saveHours(week, user);
            }
        }
    }

    private void saveHours(Week week, User user) throws SQLException {
        if (week.countWorkHours() == 0) {
            us.getWd().create(week);
        } else {
            us.getWd().create(week);
            //weekdao.update(week, week.getWeekNumber(), user.getUserNumber());
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

    private User inputForUserCreation() {
        Scanner reader = new Scanner(System.in);
        String confirmation = "no";
        while (confirmation.equals("no")) {
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
            System.out.println("Are you happy with the input?");
            System.out.println("First name: " + firstName + ", last name: " + lastName + ", username: " + username);
            System.out.println("Role: " + role + ", team: " + team + ", team lead: " + answer);
            System.out.println("yes | no");
            confirmation = reader.nextLine();
            if (confirmation.equals("yes")) {
                confirmation = "yes";
                return user;
            }
        }
        return null;
    }

    private User login() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Program started...");
        System.out.println();
        while (true) {
            printUserCommands();
            System.out.println();
            System.out.println();
            System.out.print("Select the number of command you want to run: ");
            String userCommandInput = reader.nextLine();
            if (userCommandInput.equals("0")) {
                System.out.println("Exiting program...");
                System.exit(0);
            } else if (userCommandInput.equals("1")) {
                while (true) {
                    System.out.println("Give your username");
                    System.out.println("0 returns");
                    String usernameInput = reader.nextLine();
                    try {
                        if (usernameInput.equals("0")) break;
                        User user = us.getUd().read(usernameInput);
                        if (user != null) {
                            TimeUnit.SECONDS.sleep(1);
                            return user;
                        } else {
                            System.out.println("Username doesn't exist.");
                            System.out.println("0: Exit");
                            System.out.println("1: Try again");
                            String usernameAgainOrExit = reader.nextLine();
                            if (usernameAgainOrExit.equals("0")) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Did not find anything or something went wrong.");
                        System.out.println("Please try again");
                    }
                }
            } else if (userCommandInput.equals("2")) {
                User user = inputForUserCreation();
                try {
                    us.getUd().create(user);
                    return user;
                } catch (Exception e) {
                    System.out.println("Creating a new user did not work. Please try again.");
                }
            }
        }
    }

    public void printInfoAndOptions(User user) {
        System.out.println();
        if (user.getIsTeamLead()) {
            System.out.println("Logged in as " + user.getFirstName() + " " + user.getLastName() + " - TEAM LEAD VIEW");
        } else {
            System.out.println("Logged in as " + user.getFirstName() + " " + user.getLastName());
        }
        System.out.println("Available commands:");
        System.out.println("****************");
        if (user.getIsTeamLead()) {
            printAdminCommands();
        } else {
            printCommands();
        }
        System.out.println("****************");
        System.out.print("Select the number of command you want to run: ");
    }

    private void printCommands() {
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    private void printAdminCommands() {
        for (Map.Entry<String, String> entry : adminCommands.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void printUserCommands() {
        for (Map.Entry<String, String> entry : userCommands.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
