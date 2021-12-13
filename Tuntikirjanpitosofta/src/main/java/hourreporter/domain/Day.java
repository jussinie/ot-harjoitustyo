package hourreporter.domain;

import java.util.ArrayList;

public class Day {

    public ArrayList<Task> tasksForDay;

    public Day() {
        tasksForDay = new ArrayList<>();
    }

    public void printDay() {
        if (tasksForDay.isEmpty()) {
            System.out.println("No tasks booked for this day.");
        }
        for (Task t : tasksForDay) {
            System.out.println(t.getTaskName() + ", used " + t.getHours() + " hours");
        }
    }

    public double getDaysHours() {
        double total = 0;
        for (Task t : tasksForDay) {
            total = total + t.getHours();
        }
        return total;
    }

    public void addTaskToDay(String taskName, double hours) {
        if (tasksForDay.size() == 0) {
            tasksForDay.add(new Task(taskName, hours));
        }
        tasksForDay.set(0, new Task(taskName, hours));
    }

}
