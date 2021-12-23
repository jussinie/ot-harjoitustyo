package hourreporter.dao;

import java.util.Scanner;
import java.nio.file.Paths;

public class DatabaseSelector {

    String[] connectionStrings;

    private void getConnectionStringFromConfigFile() {
        connectionStrings = new String[2];
        try (Scanner fileReader = new Scanner(Paths.get("config.txt"))) {
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
        }
    }

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
