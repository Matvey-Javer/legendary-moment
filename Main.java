package ToDoList;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ToDoList toDoList = new ToDoList();

            while (true) {
                System.out.println("=== Меню ===\n" +
                        "1. Добавить задачу\n" +
                        "2. Просмотреть задачи\n" +
                        "3. Удалить задачу\n" +
                        "4. Отметить задачу как выполненную\n" +
                        "5. Поиск задач\n" +
                        "6. Сохранить задачи в файл\n" +
                        "7. Загрузить задачи из файла\n" +
                        "8. Отсортировать задачи по приоритету\n"+
                        "9. Выход");

                System.out.print("Выберите действие: ");
                int value = scanner.nextInt();
                scanner.nextLine();   // очистка буфера

                switch (value) {
                    case 1:
                        addTask(scanner, toDoList);
                        break;
                    case 2:
                        toDoList.outputTaskList();
                        break;
                    case 3:
                        removeTask(scanner, toDoList);
                        break;
                    case 4:
                        completingTask(scanner, toDoList);
                        break;
                    case 5:
                        searchTask(scanner, toDoList);
                        break;
                    case 6:
                        saveTaskToFile(scanner, toDoList);
                        break;
                    case 7:
                        loadTaskFromFile(scanner, toDoList);
                        break;
                    case 8:
                        toDoList.sortingTask();
                        break;
                    case 9:
                        System.out.println("До свидания!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        }
    }

    public static void addTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.print("Введите приоритет задачи: ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        toDoList.addTask(new Task(taskName, taskDescription, priority));
    }

    public static void removeTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Введите номер задачи для удаления: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        toDoList.removeTask(number);
    }

    public static void completingTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Введите номер задачи для отметки как выполненной: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        toDoList.completingTask(number);
    }

    public static void searchTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Введите ключевое слово для поиска: ");
        String keyword = scanner.nextLine();
        scanner.nextLine();

        toDoList.searchTask(keyword);
    }

    public static void saveTaskToFile(Scanner scanner, ToDoList toDoList) {
        System.out.print("Введите путь к файлу для сохранения: ");
        String filePath = scanner.nextLine();
        try {
            FileManager.saveTasksToFile(toDoList.getTaskList(), filePath);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    public static void loadTaskFromFile(Scanner scannerm, ToDoList toDoList) {
        System.out.print("Введите путь к файлу для загрузки: ");
        String filePath = scannerm.nextLine();
        try {
            toDoList.setTaskList(FileManager.loadTasksFromFile(filePath));
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }
    }
}
