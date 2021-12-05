package hourreporter.domain;

public class Year {

    private Week[] weeks;
    private long userNumber;

    public Year(long userNumber) {
        this.weeks = new Week[53];
        this.userNumber = userNumber;
    }

    public Week createNewWeek(int weekNr, long userNumber) {
        if (weekNr > 0 && weekNr <= 52) {
            this.weeks[weekNr] = new Week(weekNr, userNumber);
        }
        return this.weeks[weekNr];
    }

    public Week getWeek(int weekNr) {
        return this.weeks[weekNr];
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

