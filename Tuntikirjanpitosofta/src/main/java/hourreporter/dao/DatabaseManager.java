package hourreporter.dao;

import java.sql.*;
public class DatabaseManager {

    public void initiateConnectionToDb() throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
            Statement s = db.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday DOUBLE, tuesday DOUBLE, wednesday DOUBLE, thursday DOUBLE, friday DOUBLE, saturday DOUBLE, sunday DOUBLE)");
            //s.execute("INSERT INTO Users (firstName, lastName, username, role, team, isTeamLead, userNumber) VALUES ('jussi', 'niemi', 'jusnie', 'admin', 'adminteam', false, 1)");
        } catch (Exception e) {
            System.out.println("Connection to database was not succesful! " + e.getMessage());
        }
        System.out.println("Connection to database succesful!");
    }
}
