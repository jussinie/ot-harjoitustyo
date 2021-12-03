package hourreporter.dao;

import hourreporter.domain.Week;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        ps.setDouble(3, week.getDaysHoursForWeek("mon"));
        ps.setDouble(4, week.getDaysHoursForWeek("tue"));
        ps.setDouble(5, week.getDaysHoursForWeek("wed"));
        ps.setDouble(6, week.getDaysHoursForWeek("thu"));
        ps.setDouble(7, week.getDaysHoursForWeek("fri"));
        ps.setDouble(8, week.getDaysHoursForWeek("sat"));
        ps.setDouble(9, week.getDaysHoursForWeek("sun"));

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    @Override
    public Week read(Integer key) throws SQLException {
        return null;
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
