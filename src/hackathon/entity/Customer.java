package hackathon.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String phone;
    private String customerType;
    private LocalDate registrationDate;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String email, String phone, String customerType, LocalDate registrationDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.customerType = customerType;
        this.registrationDate = registrationDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void inputData(Scanner scanner, List<Customer> customers) {
        while (true) {
            System.out.print("Nhập mã khách hàng(C + 3 số): ");
            boolean existsId = customers.stream()
                    .anyMatch(c -> c.getCustomerId().equalsIgnoreCase(customerId));
            if (!customerId.matches("C\\d{3}")) {
                System.out.println("Mã khách hàng chưa đúng định dạng");
            } else if (existsId) {
                System.out.println("Mã khách hàng bị trùng");
            } else {
                this.customerId = scanner.nextLine();
                break;
            }
        }
        while (true) {
            System.out.print("Nhập tên khách hàng: ");
            if (this.customerName.trim().isEmpty()) {
                System.out.println("Tên khách hàng không được để trống");
            } else {
                this.customerName = scanner.nextLine();
                break;
            }
        }
        while (true) {
            System.out.print("Nhập email: ");
            boolean existsEmail = customers.stream()
                    .anyMatch(c -> c.getEmail().equalsIgnoreCase(email));
            if (!this.email.contains("@") || !this.email.contains(".")) {
                System.out.println("Email không hợp lệ");
            } else if (existsEmail) {
                System.out.println("Email bị trùng");
            } else {
                this.email = scanner.nextLine();
                break;
            }
        }
        while (true) {
            System.out.print("Nhập số điện thoại khách hàng: ");
            if (phone.matches("0\\d{9,10}")) {
                this.phone = scanner.nextLine();
                break;
            } else {
                System.out.println("Số điện thoại không hợp lệ");
            }
        }
        while (true) {
            System.out.print("Nhập loại khách hàng: ");
            if (this.customerType.toLowerCase().matches("[cá nhân, doanh nghiệp, ưu đãi]")) {
                this.customerType = scanner.nextLine();
                break;
            } else {
                System.out.println("Loại khách hàng không hợp lệ");
            }
        }
        System.out.println("Nhập ngày đăng ký: ");
        this.registrationDate = LocalDate.now();
    }
    public void displayData() {
        System.out.printf("| %-5s | %-20s | %-30s | %-14s | %-15s | %-14s |",
                customerId, customerName, email, phone, customerType, registrationDate);
    }
}
