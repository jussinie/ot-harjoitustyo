package hourreporter.domain;

/**
 * This class provides methods to store tasks that the user has worked upon. These Task objects can be added to a Day object.
 */
public class Task {

    /**
     * Name given for this task.
     */
    private String taskName;
    /**
     * Hours given for this worked task.
     */
    private double hours;

    /**
     * Task object is created with given parameters.
     * Currently these tasks are not utilized in full - only one is allowed to be created at a time in the Day object.
     *
     * @param taskName
     * @param hours
     */
    public Task(String taskName, double hours) {
        this.taskName = taskName;
        this.hours = hours;
    }

    /**
     * Method returns taskName String for this Task object.
     *
     * @return String Name given for this task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Method returns hours Double for this Task object.
     * @return Hours given for this task.
     */
    public double getHours() {
        return this.hours;
    }
}
