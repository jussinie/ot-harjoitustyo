package hourreporter.dao;

import hourreporter.domain.User;
import hourreporter.domain.Week;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FakeWeekDao extends WeekDao {

    private ArrayList<Week> weeks;

    public FakeWeekDao() {
        weeks = new ArrayList<>();
    }

    public void create(Week week) {
        weeks.add(week);
    }

    @Override
    public Week read(Integer weekNumber, Long userNumber) {
        for (Week w : weeks) {
            if (w.getWeekNumber() == weekNumber && w.getUserNumber() == userNumber) {
                return w;
            }
        }
        return null;
    }

    public Week update() {
        return null;
    }

    @Override
    public List<Week> list() {
        return weeks;
    }

}
