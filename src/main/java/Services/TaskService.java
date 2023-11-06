package Services;


import Classes.Department;
import Classes.Employee;
import Classes.Task;
import Enums.Priority;
import Enums.Skill;
import Exceptions.EmployeeException;
import Exceptions.TaskException;
import Repository.DepartmentRepository;
import Repository.EmployeeRepository;
import Repository.FreeTaskRepository;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    public List<Task> getFreeTaskRepository() {
        return FreeTaskRepository.getFreeTaskRepository();
    }

    public Task getFirstTask() {
        Task result = null;
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        if (!taskList.isEmpty()) result = taskList.get(0);
        return result;
    }

    public Task getTaskByIndex(int index) {
        Task result = null;
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        if (!taskList.isEmpty()) result = taskList.get(index);
        return result;
    }

    public Task getTaskById(int taskId) {
        Task result = null;
        List<Task> taskList = FreeTaskRepository.getFreeTaskRepository();
        if (!taskList.isEmpty()) {
            List<Task> resultList = taskList.stream().filter(x -> x.getId() == taskId).toList();
            if (!resultList.isEmpty()) result = resultList.get(0);
        }
        return result;
    }

    public void addTaskConsole() {
        Scanner scanner = new Scanner(System.in, Charset.forName("windows-1251"));
        System.out.println("Введите Название задачи:");
        String name = scanner.next();
        int rank;
        while (true) {
            System.out.println("Введите ранг:");
            rank = scanner.nextInt();
            break;

        }
        LocalDate dueDate;
        while (true) {
            System.out.println("Введите Дату окончания (Формат ГГГГ-ММ-ДД):");
            try {
                dueDate = LocalDate.parse(scanner.next());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат даты окончания. Повторите ввод");
            }
        }
        Priority priority;
        while (true) {
            System.out.println("Выберете приоритет из списка:");
            for (Priority priority1 : Priority.values()) System.out.print(priority1 + " ");
            System.out.println();

            priority = Priority.valueOf(scanner.next());
            if (priority == null) throw new TaskException();
            break;

        }
        Skill skill;
        while (true) {
            System.out.println("Выберете навык из списка:");
            for (Skill skill1 : Skill.values()) System.out.print(skill1 + " ");
            try {
                skill = Skill.valueOf(scanner.next());
                if (skill == null) throw new TaskException();
                break;
            } catch (InputMismatchException | TaskException e) {
                System.out.println("Неверный навык. Повторите ввод");
            }
        }
        int length;
        while (true) {
            System.out.println("Введите длину:");

            length = scanner.nextInt();
            break;

        }
        addTask(name, rank, dueDate, priority, skill, length);
    }

    public void addTask(String name, int rank, LocalDate dueDate, Priority priority, Skill skill, int length) {
        Task task = new Task(name, rank, dueDate, priority, skill, length);
        addTaskToRepository(task);
    }

    public void addTaskToRepository(Task task) {
        FreeTaskRepository.addTask(task);
    }
}