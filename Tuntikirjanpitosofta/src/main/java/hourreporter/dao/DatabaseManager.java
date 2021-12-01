package hourreporter.dao;

import java.sql.*;
public class DatabaseManager {

    public void initiateConnectionToDb() throws SQLException {
        try {
            Connection db = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
            Statement s = db.createStatement();
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("INSERT INTO Users (firstName, lastName, username, role, team, isTeamLead) VALUES ('jussi', 'niemi', 'jusnie', 'admin', 'adminteam', false)");
        } catch (Exception e) {
            System.out.println("Connection to database was not succesful!");
        }
        System.out.println("Connection to database succesful!");
    }
}
