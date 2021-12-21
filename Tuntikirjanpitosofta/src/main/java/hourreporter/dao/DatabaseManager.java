package hourreporter.dao;

import java.sql.*;
public class DatabaseManager {

    Connection db;

    public Connection initiateConnectionToDb(String prodOrTest) throws SQLException {
        if (prodOrTest.equals("prod")) {
            try {
                db = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
                Statement s = db.createStatement();
                s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
                s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday DOUBLE, tuesday DOUBLE, wednesday DOUBLE, thursday DOUBLE, friday DOUBLE, saturday DOUBLE, sunday DOUBLE)");
            } catch (Exception e) {
                System.out.println("Connection to production database was not succesful! " + e.getMessage());
            }
        }
        else if (prodOrTest.equals("test")) {
            try {
                db = DriverManager.getConnection("jdbc:sqlite:hourreporterTest.db");
                Statement s = db.createStatement();
                s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
                s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday DOUBLE, tuesday DOUBLE, wednesday DOUBLE, thursday DOUBLE, friday DOUBLE, saturday DOUBLE, sunday DOUBLE)");
            } catch (Exception e) {
                System.out.println("Connection to test database was not succesful! " + e.getMessage());
            }
        }

        System.out.println("Connection to" + prodOrTest + " database succesful!");
        return db;
    }
}
