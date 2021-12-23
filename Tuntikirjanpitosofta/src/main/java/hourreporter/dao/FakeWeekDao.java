package hourreporter.dao;

import hourreporter.domain.Week;
import java.util.ArrayList;
import java.util.List;

public class FakeWeekDao extends WeekDao {

    private ArrayList<Week> weeks;

    public FakeWeekDao() {
        weeks = new ArrayList<>();
    }

    @Override
    public void create(Week week) {
        weeks.add(week);
    }

    @Override
    public Week read(Integer weekNumber, Long userNumber) {
        for (Week w : weeks) {
            if (w.getWeekNumber() == weekNumber) {
                return w;
            }
        }
        return null;
    }

    @Override
    public List<Week> list() {
        return weeks;
    }

}
