package hourreporter.ui;

import hourreporter.dao.WeekDao;
import hourreporter.domain.*;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {

    private Scanner reader;
    private HashMap<String, String> commands;
    private HashMap<String, String> adminCommands;

    public UserInterface(Scanner reader, UserService userService) {
        this.reader = reader;
        userService = new UserService();
        commands = new HashMap();
        adminCommands = new HashMap<>();

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
    }

    public Week selectOrCreateWeek(User user, FileService fileService, Year year) {
        while (true) {
            System.out.println("1: Create new week");
            System.out.println("2: Select existing week");
            System.out.println("0: Quit program");
            String input = reader.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting program...");
                break;
            } else if (input.equals("2")) {
                if (!year.printCreatedWeeks()) {
                    System.out.println("Which week you want to select?");
                    String selectWeek = reader.nextLine();
                    return year.getWeek(Integer.valueOf(selectWeek));
                } else {
                    System.out.println("As there are no weeks created, your selection will be first created week");
                    System.out.println("This week will be selected after creation.");
                    String selectWeek = reader.nextLine();
                    return year.createNewWeek(Integer.valueOf(Integer.valueOf(selectWeek)), user.getUserNumber());
                }
            } else if (input.equals("1")) {
                System.out.println("For which week you want to create your sheet?");
                System.out.println("You have created these weeks already:");
                year.printCreatedWeeks();
                System.out.println("This week will be selected after creation.");
                String weekNr = reader.nextLine();
                return year.createNewWeek(Integer.valueOf(weekNr), user.getUserNumber());
            }
        }
        return null;
    }

    public void startUI(User user, FileService fileService, Week week, WeekDao wd) throws SQLException {
        while (true) {
            printInfoAndOptions(user);
            String input = reader.nextLine();
            if (input.equals("0")) {
                System.out.println("Exiting program...");
                break;
            } else if (input.equals("1")) {
                week.printWholeWeek();
            } else if (input.equals("3")) {
                System.out.println(week.countWorkHours());
            } else if (input.equals("4")) {
                addHours(week);
            } else if (input.equals("5")) {
                saveHours(week, user, fileService, wd);
            }
        }
    }

    private void saveHours(Week week, User user, FileService fileService, WeekDao weekdao) throws SQLException {
        String workWeek = String.valueOf(user.getUserNumber());
        workWeek.concat(",");
        double[] workHours = week.getWeeksHoursByDay();
        for (int i = 0; i < 7; i++) {
            workWeek = workWeek + "," + week.weekdays[i] + "," + workHours[i];
        }
        weekdao.create(week);
        /*try {
            fileService.writeHoursToFile(workWeek);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } */
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

    private void printInfoAndOptions(User user) {
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

}
