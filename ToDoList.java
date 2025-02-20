package ToDoList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ToDoList {
    private static List<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }


    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Задача \"" + task.getTaskName() + "\" успешно добавлена.");
    }

    public void removeTask(int index) { // после изучения лямбда-выражений перепиши этот метод через лямбды
        if (index < 0 || index >= taskList.size()) {
            System.out.println("Ошибка: Задан неверный индекс.");
        } else {
            Task removedTask = taskList.remove(index);
            System.out.println("Задача \"" + removedTask.getTaskName() + "\" успешно удалена.");
        }
    }

    public void completingTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            System.out.println("Ошибка: Задан неверный индекс.");
        } else {
            Task task = taskList.get(index);
            task.setCompleted(true);
            System.out.println("Задача \"" + task.getTaskName() + "\" отмечена как выполненная.");
        }
    }

    public void outputTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("Задача #" + i);
                taskList.get(i).taskInfo();
                System.out.println();
            }
        }
    }

    public void searchTask(String keyword) {
        boolean found = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getTaskName().contains(keyword) || task.getTaskDescription().contains(keyword)) {
                System.out.println();
                System.out.println("Задача #" + i);
                task.taskInfo();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Задачи, содержащие \"" + keyword + "\", не найдены.");
        }
    }
    public void sortingTask() {
        taskList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Integer.compare(t1.getPriority(), t2.getPriority());
            }
        });
        System.out.println("Задачи отсортированы по приоритету.");
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        ToDoList.taskList = taskList;
    }
}