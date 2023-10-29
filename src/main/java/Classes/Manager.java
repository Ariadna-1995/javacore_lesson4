package Classes;

import Enums.Skill;

import java.time.LocalDate;

public class Manager extends Employee {
    public Manager(String lastName, String firstName, LocalDate birthDate, double salary, Department department, Skill skill) {
        super(lastName, firstName, birthDate, salary, department, skill);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", department=" + department +
                ", skill=" + skill +
                ", isWorking=" + isWorking +
                '}';
    }
}