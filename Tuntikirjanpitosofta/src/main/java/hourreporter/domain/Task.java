package hourreporter.domain;

public class Task {

    private String taskName;
    private double hours;

    public Task(String taskName, double hours) {
        this.taskName = taskName;
        this.hours = hours;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public double getHours() {
        return this.hours;
    }
}
