package hourreporter.domain;

public class Year {

    private Week[] weeks;
    private long userNumber;

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

    public Week getWeek(int weekNumber) {
        return this.weeks[weekNumber];
    }

    public int size() {
        return this.weeks.length;
    }

    public boolean printCreatedWeeks() {
        boolean isEmpty = true;
        for (int i = 0; i < 52; i++) {
            if (this.weeks[i] != null) {
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println("There are no weeks created yet!");
        } else {
            System.out.println("*******************************");
            for (int i = 1; i < weeks.length; i++) {
                if (weeks[i] == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(i + " ");
                }
                if (i % 12 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println("*******************************");
        }
        return isEmpty;
    }
}

