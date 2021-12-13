package hourreporter.dao;

import hourreporter.domain.Week;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeekDao implements Dao<Week, Integer, Long> {

    String connString;

    public WeekDao() {
        this.connString = "jdbc:sqlite:hourreporter.db";
    }

    @Override
    public void create(Week week) throws SQLException {
        Connection c = DriverManager.getConnection(connString);
        PreparedStatement ps = c.prepareStatement("INSERT INTO Weeks"
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
        c.close();
        System.out.println("domain.Week saved!");
    }

    @Override
    public Week read(Integer weekNumber, Long userNumber) throws SQLException {
        Connection c = DriverManager.getConnection(connString);
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Weeks WHERE weekNumber = ? AND userNumber = ?");
        ps.setInt(1, weekNumber);
        ps.setLong(2, userNumber);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }
        Week w = new Week(weekNumber, userNumber);
        w.setDay("Mon", rs.getDouble("monday"));
        w.setDay("Wed", rs.getDouble("wednesday"));
        ps.close();
        rs.close();
        c.close();
        return w;
    }

    @Override
    public Week update(Week week, Integer weekNumber, Long userNumber) throws SQLException {
        Connection c = DriverManager.getConnection(connString);
        //PreparedStatement ps = c.prepareStatement("UPDATE Weeks SET tuesday = 12.5");
        PreparedStatement ps = c.prepareStatement("UPDATE Weeks SET monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ? WHERE weekNumber = ? AND userNumber = ?");
        ps.setDouble(1, week.getOneDay("Mon").getDaysHours());
        ps.setDouble(2, week.getOneDay("Tue").getDaysHours());
        ps.setDouble(3, week.getOneDay("Wed").getDaysHours());
        ps.setDouble(4, week.getOneDay("Thu").getDaysHours());
        ps.setDouble(5, week.getOneDay("Fri").getDaysHours());
        ps.setDouble(6, week.getOneDay("Sat").getDaysHours());
        ps.setDouble(7, week.getOneDay("Sun").getDaysHours());
        ps.setInt(8, weekNumber);
        ps.setLong(9, userNumber);
        //ResultSet rs = ps.executeQuery();
        //System.out.println(rs.getMetaData());
        ps.executeUpdate();
        ps.close();
        c.close();
        return week;
    }

    @Override
    public List<Week> list() throws SQLException {
        ArrayList<Week> weeks = new ArrayList<>();
        Connection c = DriverManager.getConnection(connString);
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Weeks");
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
}
