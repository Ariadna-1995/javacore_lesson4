package Menu;
import Classes.Assignment;
import Classes.Task;
import Classes.TaskPlanner;
import Repository.AssignmentRepository;
import Services.TaskService;

import java.util.InputMismatchException;

public class AssignmentMenu extends AbstractMenu {

    public static void printMenu() {
        AbstractMenu.printMenu();
        System.out.println("   1   | Вывести список назначенных заданий");
        System.out.println("   2   | Вывести назначенное задание по id");
        System.out.println("   0   | Выход");
        System.out.print("Пункт: ");
    }

    public static void viewMenu() {
        boolean isRepeat = true;
        while (isRepeat) {
            printMenu();
            switch (readMenu()) {
                case "1" -> System.out.println(AssignmentRepository.getAssignmentRepository());
                case "2" -> {
                    int assignmentId;
                    while (true) {
                        try {
                            System.out.print("Введите id назначенного задания: ");
                            assignmentId = Integer.parseInt(readMenu());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный формат идентификатора");
                        }
                    }
                    System.out.println(AssignmentRepository.getAssignmentById(assignmentId));
                }
                case "0" -> isRepeat = false;
                default -> System.out.println("Введен неизвестный код!");
            }
        }
    }
}