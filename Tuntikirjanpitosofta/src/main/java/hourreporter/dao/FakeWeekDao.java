package hourreporter.dao;

import hourreporter.domain.Week;
import java.util.ArrayList;
import java.util.List;
/**
 * Class extending WeekDao. To be used in UserService testing as an injection
 * to avoid the need to use database for those test operations.
 */
public class FakeWeekDao extends WeekDao {

    /**
     * ArrayList to represent database objects (rows).
     */
    private ArrayList<Week> weeks;

    public FakeWeekDao() {
        weeks = new ArrayList<>();
    }
    /**
     * Method to create a week, i.e. add week to the arrayList.
     */
    @Override
    public void create(Week week) {
        weeks.add(week);
    }

    /**
     * Method to read one Week from the arrayList.
     * @param weekNumber parameter to find the correct Week from the database.
     * @param userNumber parameter to get the correct Week amongst many that have the same week number.
     * @return
     */
    @Override
    public Week read(Integer weekNumber, Long userNumber) {
        for (Week w : weeks) {
            if (w.getWeekNumber() == weekNumber) {
                return w;
            }
        }
        return null;
    }

    /**
     * Method to return the ArrayList
     * @return ArrayList of Weeks.
     */
    @Override
    public List<Week> list() {
        return weeks;
    }

}
