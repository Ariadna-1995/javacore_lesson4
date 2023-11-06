package Repository;

import Classes.Employee;

import java.util.ArrayList;
import java.util.List;

abstract public class EmployeeRepository {
    private static List<Employee> employeeRepository;

    public static List<Employee> addEmployee(Employee employee) {
        createEmployeeRepository();
        employeeRepository.add(employee);
        return employeeRepository;
    }

    public static List<Employee> getEmployeeRepository() {
        createEmployeeRepository();
        return employeeRepository;
    }

    public static void setEmployeeRepository(List<Employee> employeeList) {
        createEmployeeRepository();
        if (employeeList != null) employeeRepository.addAll(employeeList);
    }

    private static void createEmployeeRepository() {
        if (employeeRepository == null) employeeRepository = new ArrayList<>();
    }
}