package session08.homework01;

public interface Device {
    void turnOn();
    void turnOff();
}

class Light implements Device {
    @Override
    public void turnOn() {
        System.out.println("Đèn: Bật sáng.");
    }
    @Override
    public void turnOff() {
        System.out.println("Đèn: Tắt.");
    }
}

class Fan implements Device {
    @Override
    public void turnOn() {
        System.out.println("Quạt: Bật quay.");
    }
    @Override
    public void turnOff() {
        System.out.println("Quạt: Tắt.");
    }
}

class AirConditioner implements Device {
    @Override
    public void turnOn() {
        System.out.println("Điều hòa: Bật làm mát.");
    }
    @Override
    public void turnOff() {
        System.out.println("Điều hòa: Tắt.");
    }
}
