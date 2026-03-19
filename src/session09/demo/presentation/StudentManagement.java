package session09.demo.presentation;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import session09.demo.business.StudentBusiness;
import session09.demo.entity.Student;
import session09.demo.helper.*;

public class StudentManagement {
    public static void main(String[] args) throws Exception {
        StudentBusiness studentBusiness = StudentBusiness.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1. List all students
                    2. Add new student
                    3. Update student by ID
                    4. Find student by ID
                    5. Find students by name
                    6. Filter students by GPA
                    7. Sort students by GPA
                    8. Exit
                    """);
            System.out.print("Choose an option: ");
            int choice = InputHelper.inputInteger(scanner);
            try {
                switch (choice) {
                    case 1 -> studentBusiness.listAllStudents();
                    case 2 -> {
                        while(true) {
                            var student = new session09.demo.entity.Student();
                            student.inputData(scanner);
                            studentBusiness.addStudent(student);
                            System.out.println("Student added successfully.");
                            System.out.print("Do you want to add another student? (y/n): ");
                            String continueInput = scanner.nextLine();
                            if (!continueInput.equalsIgnoreCase("y")) {
                                break;
                            }
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter student ID to update: ");
                        String id = scanner.nextLine();
                        Optional<Student> studentOpt = studentBusiness.findStudentById(id);
                        if (studentOpt.isPresent()) {
                            studentBusiness.updateStudent(studentOpt.get(), scanner);
                            System.out.println("Student updated successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter student ID to search: ");
                        String id = scanner.nextLine();
                        Optional<Student> studentOpt = studentBusiness.findStudentById(id);
                        studentBusiness.showStudentsTable(studentOpt.map(List::of).orElse(List.of()));
                    }
                    case 5 -> {
                        System.out.print("Enter name to search: ");
                        String name = scanner.nextLine();
                        var students = studentBusiness.findStudentsByName(name);
                        if (students.isEmpty()) {
                            System.out.println("No students found with that name.");
                        } else {
                            studentBusiness.showStudentsTable(students);
                        }
                    }
                    case 6 -> {
                        System.out.println("Filter students by GPA:");
                        System.out.print("Enter GPA threshold: ");
                        double threshold = ValidateStudentInput.validateGpa(InputHelper.inputDouble(scanner));
                        studentBusiness.filterStudentsByGpa(threshold);
                    }
                    case 7 -> {
                        studentBusiness.sortStudentsByGpaDescending();
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
