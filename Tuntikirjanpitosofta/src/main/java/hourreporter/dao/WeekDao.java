package hourreporter.dao;

import hourreporter.domain.Week;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeekDao implements Dao<Week, Integer, Long> {

    private String testOrProd;
    private Connection dbConn;

    public WeekDao() {

    }

    public WeekDao(String testOrProd) {
        this.testOrProd = testOrProd;
        try {
            if (testOrProd.equals("test")) {
                dbConn = DriverManager.getConnection("jdbc:sqlite:hourreporterTest.db");
            } else if (testOrProd.equals("prod")) {
                dbConn = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
            }
            Statement s = dbConn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday DOUBLE, tuesday DOUBLE, wednesday DOUBLE, thursday DOUBLE, friday DOUBLE, saturday DOUBLE, sunday DOUBLE)");
        } catch (Exception e) {
            System.out.println("WeekDao could not connect to DB.");
        }
    }

    @Override
    public void create(Week week) throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("INSERT INTO Weeks"
                + " (userNumber, weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setLong(1, week.getUserNumber());
        ps.setInt(2, week.getWeekNumber());
        ps.setDouble(3, week.getDaysHoursForWeek("Mon"));
        ps.setDouble(4, week.getDaysHoursForWeek("Tue"));
        ps.setDouble(5, week.getDaysHoursForWeek("Wed"));
        ps.setDouble(6, week.getDaysHoursForWeek("Thu"));
        ps.setDouble(7, week.getDaysHoursForWeek("Fri"));
        ps.setDouble(8, week.getDaysHoursForWeek("Sat"));
        ps.setDouble(9, week.getDaysHoursForWeek("Sun"));
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public Week read(Integer weekNumber, Long userNumber) throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Weeks WHERE weekNumber = ? AND userNumber = ?");
        ps.setInt(1, weekNumber);
        ps.setLong(2, userNumber);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }
        Week w = new Week(weekNumber, userNumber);
        w.setDay("Mon", rs.getDouble("monday"));
        w.setDay("Tue", rs.getDouble("tuesday"));
        w.setDay("Wed", rs.getDouble("wednesday"));
        w.setDay("Thu", rs.getDouble("thursday"));
        w.setDay("Fri", rs.getDouble("friday"));
        w.setDay("Sat", rs.getDouble("saturday"));
        w.setDay("Sun", rs.getDouble("sunday"));
        ps.close();
        rs.close();
        return w;
    }

    @Override
    public Week update(Week week, Integer weekNumber, Long userNumber) throws SQLException {
        PreparedStatement ps = dbConn.prepareStatement("UPDATE Weeks SET monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ? WHERE weekNumber = ? AND userNumber = ?");
        ps.setDouble(1, week.getOneDay("Mon").getDaysHours());
        ps.setDouble(2, week.getOneDay("Tue").getDaysHours());
        ps.setDouble(3, week.getOneDay("Wed").getDaysHours());
        ps.setDouble(4, week.getOneDay("Thu").getDaysHours());
        ps.setDouble(5, week.getOneDay("Fri").getDaysHours());
        ps.setDouble(6, week.getOneDay("Sat").getDaysHours());
        ps.setDouble(7, week.getOneDay("Sun").getDaysHours());
        ps.setInt(8, weekNumber);
        ps.setLong(9, userNumber);
        ps.executeUpdate();
        ps.close();
        return week;
    }

    @Override
    public List<Week> list() throws SQLException {
        ArrayList<Week> weeks = new ArrayList<>();
        PreparedStatement ps = dbConn.prepareStatement("SELECT * FROM Weeks");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Week w = new Week(rs.getInt("weekNumber"), rs.getLong("userNumber"));
            w.setDay("Mon", rs.getDouble("monday"));
            w.setDay("Tue", rs.getDouble("tuesday"));
            w.setDay("Wed", rs.getDouble("wednesday"));
            w.setDay("Thu", rs.getDouble("thursday"));
            w.setDay("Fri", rs.getDouble("friday"));
            w.setDay("Sat", rs.getDouble("saturday"));
            w.setDay("Sun", rs.getDouble("sunday"));
            weeks.add(w);
        }
        ps.close();
        rs.close();
        return weeks;
    }
    public Connection getDbConn() {
        return this.dbConn;
    }
}
