package hourreporter.dao;

import hourreporter.domain.Week;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FakeWeekDao extends WeekDao {

    private ArrayList<Week> weeks;

    public FakeWeekDao() {
        weeks = new ArrayList<>();
        Week week = new Week(1, 100L);
        week.setDay("Mon", 7.5);
        week.setDay("Tue", 2.5);
        week.setDay("Wed", 7.5);
        week.setDay("Thu", 3.5);
        week.setDay("Fri", 7.5);
        week.setDay("Sat", 0.0);
        week.setDay("Sun", 0.0);
    }

    @Override
    public void create(Week week) throws SQLException {

    }

    @Override
    public Week read(Integer integer, Long userNumber) throws SQLException {
        return null;
    }

    @Override
    public Week update(Week week, Integer weekNumber, Long Usernumber) {
        return null;
    }

    @Override
    public List<Week> list() {
        return weeks;
    }

}
