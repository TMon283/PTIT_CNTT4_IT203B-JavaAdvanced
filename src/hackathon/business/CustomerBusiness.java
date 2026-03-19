package hackathon.business;

import hackathon.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerBusiness {
    private static CustomerBusiness instance;
    public static CustomerBusiness getInstance() {
        if (instance == null) {
            instance = new CustomerBusiness();
        }
        return instance;
    }
    public List<Customer> customers = new ArrayList<>();
    public void getAllCustomer() {
        if (customers.isEmpty()) {
            System.out.println("Danh sách rỗng");
        } else {
            showAllCustomer(customers);
        }
    }
    public void showAllCustomer (List<Customer> customers) {
        System.out.printf("| %-5s | %-20s | %-30s | %-14s | %-15s | %-14s |",
                "ID", "Tên", "Email", "Số điện thoại", "Loại KH", "Ngày đăng ký");
        System.out.println("-----------------------------------------------------------");
        for (Customer customer : customers) {
            customer.displayData();
        }
    }
    public void addCustomer (Customer customer) {
        customers.add(customer);
    }
    public Optional<Customer> findCustomerById (String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId)).findFirst();
    }
    public List<Customer> findCustomerByName (String customerName) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getCustomerName().toLowerCase().contains(customerName.toLowerCase())) {
                result.add(customer);
            }
        }
        return result;
    }
    public void updateCustomer (Customer customer, Scanner scanner) {
        findCustomerById(customer.getCustomerId()).isPresent();
    }
    public void deleteCustomer (String customerId) {
        if (!customers.removeIf(c -> c.getCustomerId().equals(customerId))) {
            System.out.println("Mã khách hàng không tồn tại");
        }
    }
    public void filterCustomer (String customerType) {
        customers.sort((c1, c2) -> CharSequence.compare(c1.getCustomerType(), c2.getCustomerType()));
        showAllCustomer(customers);
    }
    public void sortCustomerByName () {
        customers.sort((c1, c2) -> CharSequence.compare(c1.getCustomerName(), c2.getCustomerName()));
        showAllCustomer(customers);
    }
}
