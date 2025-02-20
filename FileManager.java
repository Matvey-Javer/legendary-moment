package ToDoList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static void saveTasksToFile(List<Task> taskList, String filePath) throws IOException {
        createFileIfNotExists(filePath);
        Path path = Path.of(filePath);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write("Название, Описание, Статус, Дата создания, Приоритет");
            bw.newLine();
            for (Task task : taskList) {
                String line = task.getTaskName() + ", " +
                        task.getTaskDescription() + ", " +
                        task.isCompleted() + ", " +
                        task.getCurrentDate() + ", " +
                        task.getPriority();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Задачи успешно сохранены в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    public static List<Task> loadTasksFromFile(String filePath) throws IOException {
        createFileIfNotExists(filePath);
        List<Task> taskList = new ArrayList<>();
        Path path = Path.of(filePath);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    String taskName = parts[0];
                    String taskDescription = parts[1];
                    boolean isCompleted = Boolean.parseBoolean(parts[2]);
                    LocalDate currentDate = LocalDate.parse(parts[3]);
                    int priority = Integer.parseInt(parts[4]);
                    Task task = new Task(taskName, taskDescription, priority);
                    task.setCompleted(isCompleted);
                    taskList.add(task);
                } else {
                    System.out.println("Задачи успешно загружены из файла: " + filePath);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }
        return taskList;
    }

    public static void createFileIfNotExists(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            System.out.println("Файл не существует: " + filePath);
            System.out.print("Хотите создать новый файл? (да(y)/нет(n)): ");

            Scanner scanner = new Scanner(System.in);
            String result = scanner.nextLine();
            if (result.equalsIgnoreCase("да") || result.equalsIgnoreCase("yes")
                    || result.equalsIgnoreCase("y")) {
                // Создаем родительские директории, если они не существуют
                Path parentPath = path.getParent();
                if (parentPath != null && !Files.exists(parentPath)) {
                    Files.createDirectories(parentPath);
                }
                // создаем файл
                Files.createFile(path);
                System.out.println("Файл успешно создан: " + filePath);
            } else {
                throw new IOException("Пользователь отказался создавать файл.");
            }
        } else {
            System.out.println("Файл уже существует: " + filePath);
        }
    }
}
