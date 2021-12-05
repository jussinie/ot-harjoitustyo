package hourreporter.dao;

import hourreporter.domain.User;
import hourreporter.domain.Week;
import hourreporter.domain.Year;

import java.sql.*;
import java.util.List;

public class WeekDao implements Dao<Week, Integer> {

    @Override
    public void create(Week week) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
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
    }

    @Override
    public Week read(Integer userNumber) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:hourreporter.db");
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Weeks WHERE userNumber = ?");
        ps.setLong(1, userNumber);
        ResultSet rs = ps.executeQuery();
        Year year = new Year(userNumber);
        while (!rs.first()) {
            year.createNewWeek(rs.getInt("weekNumber"), rs.getLong("userNumber"));
        }

        ps.close();
        rs.close();
        return year.getWeek(1);
    }

    @Override
    public Week update(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Week> list() throws SQLException {
        return null;
    }

}
