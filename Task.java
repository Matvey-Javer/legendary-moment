package ToDoList;

import java.time.LocalDate;

public class Task {
    private String taskName;
    private String taskDescription;
    private boolean isCompleted;
    private LocalDate currentDate;
    private int priority;

    public Task(String taskName, String taskDescription, int priority) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.currentDate = LocalDate.now();
        this.isCompleted = false;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public int getPriority() {
        return priority;
    }

    public void taskInfo() {
        System.out.println("Задача: " + taskName);
        System.out.println("Описание: " + taskDescription);
        System.out.println("Статус: " + (isCompleted ? "Выполнено" : "Не выполнено"));
        System.out.println("Дата создания: " + currentDate);
        System.out.println("Приоритет: " + priority);

    }
}
