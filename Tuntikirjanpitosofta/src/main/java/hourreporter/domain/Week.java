package hourreporter.domain;

import java.util.HashMap;

public class Week {

    private HashMap<String, Day> days;
    public String[] weekdays;
    private int weekNumber;
    private long userNumber;
    private boolean submitted;
    private boolean accepted;

    public Week(int weekNumber, long userNumber) {
        this.submitted = false;
        this.accepted = false;
        weekdays = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        days = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            days.put(weekdays[i], new Day());
        }
        this.weekNumber = weekNumber;
        this.userNumber = userNumber;
    }

    public void printWholeWeek() {
        System.out.println("Reported hours for week " + this.weekNumber + ": ");
        for (int i = 0; i < 7; i++) {
            System.out.print(weekdays[i] + " : " + days.get(weekdays[i]).getDaysHours());
            System.out.print(" | ");
        }
        System.out.println("");
    }

    public void setDay(String dayAbbreviation, double hours) {
        days.get(dayAbbreviation).addTaskToDay("dummy", hours);
    }

    public double[] getWeeksHoursByDay() {
        double[] weeksHours = new double[7];
        for (int i = 0; i < 7; i++) {
            weeksHours[i] = days.get(weekdays[i]).getDaysHours();
        }
        return weeksHours;
    }

    public HashMap<String, Day> getDays() {
        return this.days;
    }

    public double countWorkHours() {
        double total = 0.0;
        for (Day d : days.values()) {
            total = total + d.getDaysHours();
        }
        return total;
    }

    public long getUserNumber() {
        return this.userNumber;
    }

    public int getWeekNumber() {
        return this.weekNumber;
    }

    public void setSubmitted() {
        this.submitted = true;
    }

    public void setAccepted() {
        this.accepted = true;
    }

    public boolean getSubmitted() {
        return this.submitted;
    }

    public boolean getAccepted() {
        return this.accepted;
    }

    /**
     * Method returns one day from the week.
     * Possible abbreviations are:
     * "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun".
     *
     * @param dayAbbreviation
     * @return selected Day object.
     */
    public Day getOneDay(String dayAbbreviation) {
        return days.get(dayAbbreviation);
    }

    /**
     * Method returns hours booked for specific day from this week.
     * If no Day object exists, 0 is returned. \\n
     *
     * Possible abbreviations are:
     * "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun".
     *
     * @param dayAbbreviation
     * @return
     */
    public double getDaysHoursForWeek(String dayAbbreviation) {
        if (days.get(dayAbbreviation) == null) {
            return 0;
        } else {
            return days.get(dayAbbreviation).getDaysHours();
        }
    }
}
