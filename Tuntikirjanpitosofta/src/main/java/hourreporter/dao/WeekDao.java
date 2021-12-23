package hourreporter.dao;

import hourreporter.domain.Week;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *Class that provides the methods to access and modify User objects in the database.
 */
public class WeekDao implements Dao<Week, Integer, Long> {

    private Connection dbConn;

    public WeekDao() {

    }

    /**
     * Constructor to create connection to the database and create needed tables if they do not yet exist.
     * @param testOrProd string parameter to define connection to either test or production database.
     */
    public WeekDao(String testOrProd) {
        try {
            dbConn = DriverManager.getConnection(testOrProd);
            Statement s = dbConn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, userNumber LONG, username TEXT, role TEXT, team TEXT, isTeamLead BOOLEAN)");
            s.execute("CREATE TABLE IF NOT EXISTS Weeks (id INTEGER PRIMARY KEY, userNumber LONG, weekNumber INTEGER, monday DOUBLE, tuesday DOUBLE, wednesday DOUBLE, thursday DOUBLE, friday DOUBLE, saturday DOUBLE, sunday DOUBLE)");
        } catch (Exception e) {
            System.out.println("WeekDao could not connect to DB.");
        }
    }
    /**
     * Method to create new line in the database and populate it with Week object data.
     * @param week Week object to store in the database.
     * @throws SQLException if SQL query fails to execute for some reason.
     */
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
    /**
     * Method to read a line from the database to be used in the application.
     * @param weekNumber parameter to find the correct Week from the database.
     * @param userNumber parameter to get the correct Week amongst many that have the same week number.
     * @return Week object corresponding with the row data.
     * @throws SQLException if SQL query fails to execute for some reason.
     */
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

    /**
     * Method to update Week object, i.e read it from database, modify it and save it back to the database.
     * @param week Week object to be updated.
     * @param weekNumber Week number to identify the correct Week.
     * @param userNumber User number to identify the correct row for the logged user.
     * @return Week object.
     * @throws SQLException if SQL query fails to execute for some reason.
     */
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

    /**
     * Method to return all the saved weeks from databse.
     * @return List of Week objects.
     * @throws SQLException if SQL query fails to execute for some reason.
     */
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
    /**
     * Method to return database connection.
     * @return Connection object.
     */
    public Connection getDbConn() {
        return this.dbConn;
    }
}
