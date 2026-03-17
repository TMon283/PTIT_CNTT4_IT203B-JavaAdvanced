package session08.homework02;

public class SmartHomeFacade {
    private Light light;
    private Fan fan;
    private AirConditioner ac;
    private TemperatureSensor sensor;

    public SmartHomeFacade(TemperatureSensor sensor) {
        this.light = new Light();
        this.fan = new Fan();
        this.ac = new AirConditioner();
        this.sensor = sensor;
    }

    public void leaveHome() {
        light.turnOff();
        fan.turnOff();
        ac.turnOff();
    }

    public void sleepMode() {
        light.turnOff();
        ac.setTemperature(28);
        fan.setLowSpeed();
    }

    public void getCurrentTemperature() {
        double temp = sensor.getTemperatureCelsius();
        System.out.printf("Nhiệt độ hiện tại: %.1f°C (chuyển đổi từ %d°F)%n",
                temp, ((OldThermometer)((ThermometerAdapter)sensor).oldThermometer).getTemperatureFahrenheit());
    }
}
