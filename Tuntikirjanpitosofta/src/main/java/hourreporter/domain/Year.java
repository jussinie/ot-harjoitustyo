package hourreporter.domain;

public class Year {

    private Week[] weeks;

    public Year() {
        weeks = new Week[53];
    }

    public Week createNewWeek(int weekNr, User user) {
        if (weekNr > 0 && weekNr <= 52) {
            this.weeks[weekNr] = new Week(weekNr, user);
        }
        return this.weeks[weekNr];
    }

    public Week getWeek(int weekNr) {
        return this.weeks[weekNr];
    }

    public void printCreatedWeeks() {
        System.out.println("*******************************");
        for (int i = 1; i < weeks.length; i++) {
            if (weeks[i] == null) {
                System.out.print(" ");
            } else {
                System.out.print(i);
            }
            if (i % 12 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("*******************************");
    }

}
