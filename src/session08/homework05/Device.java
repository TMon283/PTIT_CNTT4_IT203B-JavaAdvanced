package session08.homework05;

class Light {
    public void off() {
        System.out.println("Đèn: Tắt");
    }
}

class Fan implements Observer {
    private String speed = "Tắt";

    public void setLowSpeed() {
        speed = "Chạy tốc độ thấp";
        System.out.println("Quạt: " + speed);
    }

    public void setHighSpeed() {
        speed = "Chạy tốc độ mạnh";
        System.out.println("Quạt: " + speed);
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
        }
    }

    public String getStatus() {
        return "Quạt: " + speed;
    }
}

class AirConditioner implements Observer {
    private int temperature = 25;

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temp);
    }

    @Override
    public void update(int temperature) {
        if (temperature > 30) {
            System.out.println("Điều hòa: Nhiệt độ = " + this.temperature + " (vẫn giữ)");
        }
    }

    public String getStatus() {
        return "Điều hòa: Nhiệt độ = " + temperature;
    }
}

