package hourreporter.domain;

/**
 * This class work as a parent for Week and other more specific time periods.
 */
public class Year {

    /**
     * An array to hold all the Week objects for this year.
     */
    private Week[] weeks;
    /**
     * Username of the user that has logged in. This is used to identify the reporter and link reported hours to correct user.
     */
    private long userNumber;

    /**
     * Constructs an empty Year instance capable of storing 52 Week instances. Currently we do not handle years with 53 weeks.
     * That will be later development. Currently the array size is 53 to allow storing weeks starting from number 1.
     * Usernumber given as a parameter will be assigned to this Year instance as a class attribute.
     * @param userNumber
     */
    public Year(long userNumber) {
        this.weeks = new Week[53];
        this.userNumber = userNumber;
    }

    /**
     * Method creates a new Week object with a given week number to this Year and returns it.
     * If week number is smaller than 0 or bigger than 52, null is returned.
     * User number is needed in creating new Week and assigning it to the correct user.
     *
     * @param weekNumber
     * @param userNumber
     * @return
     */
    public Week createNewWeek(int weekNumber, long userNumber) {
        if (weekNumber > 0 && weekNumber <= 52) {
            this.weeks[weekNumber] = new Week(weekNumber, userNumber);
            return this.weeks[weekNumber];
        }
        return null;
    }

    /**
     * Method returns the Week instance corresponding to the week number given as a parameter.
     * @param weekNumber Integer value to identify the correct Week instance.
     * @return Week instance with the given week number.
     */
    public Week getWeek(int weekNumber) {
        return this.weeks[weekNumber];
    }


    public int size() {
        return this.weeks.length;
    }

}

