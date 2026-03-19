package session09.demo.helper;

import java.util.List;

import session09.demo.entity.Student;

public class ValidateStudentInput {
    public static String validateId(String studentId) {
        String STUDENT_ID_PATTERN = "^SV\\d{3}$";
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (!studentId.matches(STUDENT_ID_PATTERN)) {
            throw new IllegalArgumentException("Invalid student ID format. Expected format: SV followed by 3 digits (e.g., SV001)");
        }
        return studentId;
    }

    public static int validateAge(int age) {
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("Age must be between 18 and 100");
        }
        return age;
    }

    public static double validateGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        return gpa;
    }

    public static void checkUniqueId(String studentId, List<Student> students) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                throw new IllegalArgumentException("Student ID must be unique. A student with this ID already exists.");
            }
        }
    }
}
