package hourreporter.domain;

import java.util.HashMap;

public class Week {

    private HashMap<String, Day> days;
    public String[] weekdays;

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

    public double[] getWeeksHoursByDay() {
        double[] weeksHours = new double[7];
        for (int i = 0; i < 7; i++) {
            weeksHours[i] = days.get(weekdays[i]).getDaysHours();
        }
        return weeksHours;
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
