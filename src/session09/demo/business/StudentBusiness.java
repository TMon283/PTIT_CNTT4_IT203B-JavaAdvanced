package session09.demo.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import session09.demo.entity.Student;
import session09.demo.helper.ValidateStudentInput;

public class StudentBusiness {
    private static StudentBusiness instance;

    public static StudentBusiness getInstance() {
        if (instance == null) {
            instance = new StudentBusiness();
        }
        return instance;
    }

    private List<Student> students = new ArrayList<>();

    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            showStudentsTable(students);
        }
    }

    public void showStudentsTable(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.printf("| %-12s | %-12s | %3s | %-5s |%n",
                    "Student ID", "Name", "Age", "GPA");
            System.out.println("------------------------------------------");
            for (Student student : students) {
                student.displayData();
            }
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Optional<Student> findStudentById(String studentId) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
    }

    public List<Student> findStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    public void updateStudent(Student student, Scanner scanner) {
        findStudentById(student.getStudentId()).ifPresent(existingStudent -> {
            interactivelyUpdateStudent(scanner, existingStudent);
        });
    }

    public void deleteStudent(String studentId) {
        if (!students.removeIf(s -> s.getStudentId().equals(studentId))) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
        }
    }

    public void sortStudentsByGpaDescending() {
        students.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        System.out.println("Students sorted by GPA in descending order:");
        showStudentsTable(students);
    }

    public void filterStudentsByGpa(double threshold) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() >= threshold) {
                filteredStudents.add(student);
            }
        }
        showStudentsTable(filteredStudents);
    }

    static void interactivelyUpdateStudent(Scanner scanner, Student student) {
        System.out.println("Updating student with ID: " + student.getStudentId());
        System.out.print("Enter new name (leave blank to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            student.setStudentName(newName);
        }
        System.out.print("Enter new age (leave blank to keep current): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.trim().isEmpty()) {
            student.setAge(ValidateStudentInput.validateAge(Integer.parseInt(ageInput)));
        }
        System.out.print("Enter new GPA (leave blank to keep current): ");
        String gpaInput = scanner.nextLine();
        if (!gpaInput.trim().isEmpty()) {
            student.setGpa(ValidateStudentInput.validateGpa(Double.parseDouble(gpaInput)));
        }
    }
}
