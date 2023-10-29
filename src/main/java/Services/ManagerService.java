package Services;

import Classes.*;
import Enums.Skill;
import Enums.Status;
import Exceptions.Checker;
import Exceptions.EmployeeException;
import Exceptions.ManagerException;
import Exceptions.TaskException;
import Repository.AssignmentRepository;
import Repository.EmployeeRepository;
import Repository.FreeTaskRepository;

import java.util.List;

public class ManagerService {
    public void increaseSalaryByManagerByEmployee(Manager manager, Employee employee, double amount) throws Exception {
        if (Checker.managerIsNotNull(manager) && Checker.employeeIsNotNull(employee)) {
            if (amount <= 0) throw new ManagerException("The amount is less than or equal to zero");
            if (!manager.getSkill().equals(Skill.MANAGER))
                throw new ManagerException("Only a manager can increase salary");
            if (manager.equals(employee)) throw new ManagerException("A manager cannot increase his own salary");
            if (!manager.getDepartment().equals(employee.getDepartment()))
                throw new ManagerException("A manager can increase salary only in his own department");
            employee.setSalary(employee.getSalary() + amount);
        }
    }

    public void increaseSalaryByManagerAllEmployees(Manager manager, double amount) throws Exception {
        if (Checker.managerIsNotNull(manager)) {
            if (amount <= 0) throw new ManagerException("The amount is less than or equal to zero");
            List<Employee> employeeList = EmployeeRepository.getRepository().stream()
                    .filter(x -> x.getDepartment().equals(manager.getDepartment()))
                    .filter(x -> !x.equals(manager))
                    .toList();
            for (Employee employee : employeeList) {
                increaseSalaryByManagerByEmployee(manager, employee, amount);
            }
        }
    }

    public void startFreeTaskByManager(Manager manager) throws Exception {
        if (Checker.managerIsNotNull(manager)) {
            List<Task> taskList = FreeTaskRepository.getTasks(manager.getDepartment());
            if (!taskList.isEmpty()) startTaskByManager(manager, taskList.get(0));
        }
    }

    public void startTaskByManager(Manager manager, Task task) throws Exception {
        if (Checker.managerIsNotNull(manager) && Checker.taskIsNotNull(task)) {
            Employee employee = TaskPlanner.findEmployee(manager.getDepartment(), task);
            if (employee != null) startTaskByManager(manager, task, employee);
        }
    }

    public void startTaskByManager(Manager manager, Task task, Employee employee) throws Exception {
        if (Checker.managerIsNotNull(manager) && Checker.taskIsNotNull(task) && Checker.employeeIsNotNull(employee)) {
            List<Assignment> assignmentList = AssignmentRepository.getRepository().stream()
                    .filter(x -> x.getTask() == task)
                    .toList();
            if (!assignmentList.isEmpty()) throw new TaskException("This task has already been assigned");
            assignmentList = AssignmentRepository.getRepository().stream()
                    .filter(x -> x.getEmployee() == employee)
                    .filter(x -> x.getStatus().equals(Status.IN_PROGRESS))
                    .filter(x -> x.getTask().getPriority().getCode() >= task.getPriority().getCode())
                    .toList();
            if (!assignmentList.isEmpty())
                throw new EmployeeException("This employee already has a assignment with the same priority or higher");
            Assignment assignment = TaskPlanner.createAssigment(employee, task);
            EmployeeService employeeService = new EmployeeService();
            employeeService.startAssignmentByEmployee(manager, assignment);
        }
    }
}