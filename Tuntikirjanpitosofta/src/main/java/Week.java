import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Week {

    HashMap<String, Day> days;
    String[] weekdays;

    public Week() {
        weekdays = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        days = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            days.put(weekdays[i], new Day());
        }
    }

    public void printWholeWeek() {
        System.out.println("Reported hours:");
        for (int i = 0; i < 7; i++) {
            System.out.print(weekdays[i] + " : " + days.get(weekdays[i]).getDaysHours());
            System.out.print(" | ");
        }
        System.out.println("");
    }

    public double countWorkHours() {
        double total = 0.0;
        for (Day d : days.values()) {
            total = total + d.getDaysHours();
        }
        return total;
    }

    public Day getOneDay(String dayAbbreviation) {
        return days.get(dayAbbreviation);
    }
}
