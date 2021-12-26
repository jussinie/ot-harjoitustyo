package hourreporter.dao;

import java.util.Scanner;
import java.nio.file.Paths;

/**
 * Utility class to enable usage of config file to let users freely select and create databases for the application.
 */
public class DatabaseSelector {

    /**
     * A string array to store the connection strings from config file.
     */
    String[] connectionStrings;

    /**
     * Method to extract database connection strings from the config file.
     * Looks for ; to separate strings. Line starting with test defines the test database connector,
     * line starting with prod defines the connection string to the production database.
     */
    private void getConnectionStringFromConfigFile() {
        connectionStrings = new String[2];
        try (Scanner fileReader = new Scanner(Paths.get("src/main/resources/config.txt"))) {
            while (fileReader.hasNextLine()) {
                String row = fileReader.nextLine();
                if (row.isEmpty() || row.charAt(0) == '#') {
                    continue;
                }
                String[] parts = row.split(";");
                if (parts[0].equals("test")) {
                    connectionStrings[0] = parts[1];
                }
                if (parts[0].equals("prod")) {
                    connectionStrings[1] = parts[1];
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Falling back to default: jdbc:sqlite:hourreporter.db");
            connectionStrings[1] = "jdbc:sqlite:hourreporter.db";
        }
    }

    /**
     * Method to return the extracted connection string for the application to use.
     * @param argument Command line argument given by the user. If the argument is "test" then connection to the test
     * database defined in the config file is established.
     * @return connection string for the database.
     */
    public String getConnectionString(String argument) {
        getConnectionStringFromConfigFile();
        if (argument == null) {
            System.out.println("Connected to: " + connectionStrings[1]);
            return connectionStrings[1];
        }
        if (argument.equals("test")) {
            System.out.println("Connected to: " + connectionStrings[0]);
            return connectionStrings[0];
        } else {
            System.out.println("Connected to: " + connectionStrings[1]);
            return connectionStrings[1];
        }
    }
}
