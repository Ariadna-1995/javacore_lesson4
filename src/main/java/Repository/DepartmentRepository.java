package Repository;

import Classes.Assignment;
import Classes.Department;

import java.util.ArrayList;
import java.util.List;

abstract public class DepartmentRepository {
    private static List<Department> departmentRepository;

    public static List<Department> getDepartmentRepository() {
        return departmentRepository;
    }

    public static void setDepartmentRepository(List<Department> departmentList) {
        createDepartmentRepository();
        if (departmentList != null) departmentRepository.addAll(departmentList);
    }

    public static List<Department> addDepartment(Department department) {
        createDepartmentRepository();
        departmentRepository.add(department);
        return departmentRepository;
    }

    private static void createDepartmentRepository() {
        if (departmentRepository == null) departmentRepository = new ArrayList<>();
    }
}
