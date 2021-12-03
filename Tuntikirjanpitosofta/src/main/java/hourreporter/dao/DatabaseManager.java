package hourreporter.dao;

import java.sql.*;
public class DatabaseManager {

    public void initiateConnectionToDb() throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
            Statement s = db.createStatement();
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("CREATE TABLE Weeks (id INTEGER PRIMARY KEY, userId INTEGER, weekNumber INTEGER, monday INTEGER, tuesday INTEGER, wednesday INTEGER, thursday INTEGER, friday INTEGER, saturday INTEGER, sunday INTEGER)");
            s.execute("INSERT INTO Users (firstName, lastName, username, role, team, isTeamLead, userNumber) VALUES ('jussi', 'niemi', 'jusnie', 'admin', 'adminteam', false, 1)");
        } catch (Exception e) {
            System.out.println("Connection to database was not succesful!");
        }
        System.out.println("Connection to database succesful!");
    }
}
