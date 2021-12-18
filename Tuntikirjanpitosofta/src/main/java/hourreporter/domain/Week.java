package hourreporter.domain;

import java.util.HashMap;

/**
 * Class to represent and store Weeks.
 */
public class Week {

    /**
     * Hashmap to store weekdays' string abbreviations and corresponding Day instance.
     */
    private HashMap<String, Day> days;
    /**
     * String array to store weekday's string abbreviations.
     */
    public String[] weekdays;
    /**
     * Integer value for the week number.
     */
    private int weekNumber;
    /**
     * Long value for user number (Hash code from user's last name and username).
     */
    private long userNumber;
    /**
     * Boolean value representing if user has submitted the hours for this particular week.
     */
    private boolean submitted;
    /**
     * Boolean value representing if team lead has accepted the hours for this particular week for this user.
     */
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

    /**
     * As tasks are not yet used in the application, dummy task is added to the given Day instance with given hours.
     * @param dayAbbreviation Day abbreviation Mon, Tue, Wed, Thu, Fri, Sat or Sun.
     * @param hours Hours worked on this particular day.
     */
    public void setDay(String dayAbbreviation, double hours) {
        days.get(dayAbbreviation).addTaskToDay("dummy", hours);
    }

    /**
     * Returns all the hours that user has saved for this particular week.
     * @return Double array containing the worked hours for every day, starting from index 0 (=Mon), 1 (=Tue) etc.
     */
    public double[] getWeeksHoursByDay() {
        double[] weeksHours = new double[7];
        for (int i = 0; i < 7; i++) {
            weeksHours[i] = days.get(weekdays[i]).getDaysHours();
        }
        return weeksHours;
    }

    /**
     * Returns a HashMap object containing all the String, Day pairs for this week. E.g. "Mon", Day object.
     * @return HashMap object containing all the String, Day pairs for this week.
     */
    public HashMap<String, Day> getDays() {
        return this.days;
    }

    /**
     * Returns all hours that are saved by this particular user for this week.
     * @return Single double value containing the sum of worked hours for this week.
     */
    public double countWorkHours() {
        double total = 0.0;
        for (Day d : days.values()) {
            total = total + d.getDaysHours();
        }
        return total;
    }

    /**
     *
     * @return Long user number from this Week instance.
     */
    public long getUserNumber() {
        return this.userNumber;
    }
    /**
     *
     * @return Integer week number from this Week instance.
     */
    public int getWeekNumber() {
        return this.weekNumber;
    }

    /**
     * Sets value setSubmitted to true.
     */
    public void setSubmitted() {
        this.submitted = true;
    }

    /**
     * Sets value setAccepted to true.
     */
    public void setAccepted() {
        this.accepted = true;
    }


    /**
     * Returns true if attribute submitted is true, otherwise false.
     * @return boolean true of false.
     */
    public boolean getSubmitted() {
        return this.submitted;
    }

    /**
     * Returns true if attribute accepted is true, otherwise false.
     * @return boolean true or false.
     */
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
