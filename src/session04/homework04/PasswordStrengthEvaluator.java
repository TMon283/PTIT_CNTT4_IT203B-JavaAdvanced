package session04.homework04;

public class PasswordStrengthEvaluator {

    public static String evaluatePasswordStrength(String password) {
        if (password == null || password.isEmpty()) {
            return "Yếu";
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        boolean longEnough = password.length() >= 8;

        if (!longEnough) {
            return "Yếu";
        }

        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Mạnh";
        }

        if ((hasUpper && hasLower) || (hasDigit && hasSpecial)) {
            return "Trung bình";
        }

        return "Yếu";
    }
}

