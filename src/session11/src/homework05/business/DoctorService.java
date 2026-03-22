package session11.src.homework05.business;

import session11.src.homework05.DAO.DoctorDAO;
import session11.src.homework05.model.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public void showAllDoctors() {
        List<Doctor> doctors = dao.getAllDoctors();
        System.out.println("=== Danh sách bác sĩ ===");
        for (Doctor d : doctors) {
            System.out.println(d.getDoctorId() + " | " + d.getName() + " | " + d.getSpecialization());
        }
    }

    public void addDoctor(String id, String name, String specialization) {
        Doctor doctor = new Doctor(id, name, specialization);
        dao.insertDoctor(doctor);
    }

    public void showStatistic() {
        dao.statisticBySpecialization();
    }
}
