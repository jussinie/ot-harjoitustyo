package hourreporter.domain;

import java.util.ArrayList;

/**
 * This class holds all the users Day objects.
 */
public class Day {

    /**
     * ArrayList to hold all the Task objects for a single Day object.
     */
    public ArrayList<Task> tasksForDay;

    /**
     * tasksForDay ArrayList is initialized in the constructor.
     */
    public Day() {
        tasksForDay = new ArrayList<>();
    }

    /**
     * Method returns all the hours from all the tasks for one day.
     * @return total hours worked for one day.
     */
    public double getDaysHours() {
        double total = 0;
        for (Task t : tasksForDay) {
            total = total + t.getHours();
        }
        return total;
    }

    /**
     * Method creates a new Task object with given parameters and adds it to Day object.
     * Currently the list is restricted to have only one task, as the tasks are not used anywhere.
     *
     * @param taskName Name given for this particular task.
     * @param hours Hours worked for this particular task.
     */
    public void addTaskToDay(String taskName, double hours) {
        if (tasksForDay.size() == 0) {
            tasksForDay.add(new Task(taskName, hours));
        }
        tasksForDay.set(0, new Task(taskName, hours));
    }

}
