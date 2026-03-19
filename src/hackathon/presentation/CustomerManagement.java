package hackathon.presentation;

import hackathon.business.CustomerBusiness;
import hackathon.entity.Customer;

import java.util.Scanner;

public class CustomerManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerBusiness customerBusiness = CustomerBusiness.getInstance();
        int choice = 0;
        do {
            System.out.println("-----------QUẢN LÝ KHÁCH HÀNG----------");
            System.out.println("1. Hiển thị danh sách toàn bộ khách hàng");
            System.out.println("2. Thêm mới khách hàng");
            System.out.println("3. Cập nhật thông tin khách hàng theo mã");
            System.out.println("4. Xóa khách hàng theo mã");
            System.out.println("5. Tìm kiếm khách hàng theo tên");
            System.out.println("6. Lọc khách hàng theo loại");
            System.out.println("7. Sắp xếp khách hàng theo tên tăng dần");
            System.out.println("8. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    customerBusiness.getAllCustomer();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.print("Nhập mã khách hàng: ");
                    customerBusiness.deleteCustomer(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Nhập tên khách hàng: ");
                    customerBusiness.findCustomerByName(scanner.nextLine());
                    break;
                case 6:
                    break;
                case 7:
                    customerBusiness.sortCustomerByName();
                    break;
                case 8:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 8);
    }
}
