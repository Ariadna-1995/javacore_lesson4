package Repository;


import Classes.Department;
import Classes.Task;
import Exceptions.Checker;
import Exceptions.DepartmentException;

import java.util.*;

abstract public class FreeTaskRepository {
    private static List<Task> freeTaskRepository;

    public static List<Task> getFreeTaskRepository() {
        List<Task> result = null;
        createFreeTaskRepository();
        result = freeTaskRepository.stream()
                .sorted(Comparator.comparingInt(x -> (x.getPriority().getCode())))
                .toList();
        return result;
    }

    public static void setFreeTaskRepository(List<Task> freeTaskList) {
        createFreeTaskRepository();
        if (freeTaskList != null) freeTaskRepository.addAll(freeTaskList);
    }

    public static List<Task> addTask(Task task) {
        createFreeTaskRepository();
        freeTaskRepository.add(task);
        return freeTaskRepository;
    }

    public static void deleteTask(Task task) {
        deleteTask(freeTaskRepository.indexOf(task));
    }

    public static void deleteTask(int index) {
        freeTaskRepository.remove(index);
    }

    private static void createFreeTaskRepository() {
        if (freeTaskRepository == null) freeTaskRepository = new ArrayList<>();
    }
}