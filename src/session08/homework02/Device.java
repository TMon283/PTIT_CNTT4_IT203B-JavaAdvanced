package session08.homework02;

class Light {
    public void turnOff() {
        System.out.println("FACADE: Tắt đèn");
    }
}

class Fan {
    public void turnOff() {
        System.out.println("FACADE: Tắt quạt");
    }
    public void setLowSpeed() {
        System.out.println("FACADE: Quạt chạy tốc độ thấp");
    }
}

class AirConditioner {
    public void turnOff() {
        System.out.println("FACADE: Tắt điều hòa");
    }
    public void setTemperature(int temp) {
        System.out.println("FACADE: Điều hòa set " + temp + "°C");
    }
}

