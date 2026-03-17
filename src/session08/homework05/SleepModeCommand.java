package session08.homework05;

class SleepModeCommand implements Command {
    private Light light;
    private AirConditioner ac;
    private Fan fan;

    public SleepModeCommand(Light light, AirConditioner ac, Fan fan) {
        this.light = light;
        this.ac = ac;
        this.fan = fan;
    }

    @Override
    public void execute() {
        System.out.println("SleepMode: Tắt đèn");
        light.off();
        System.out.println("SleepMode: Điều hòa set 28°C");
        ac.setTemperature(28);
        System.out.println("SleepMode: Quạt tốc độ thấp");
        fan.setLowSpeed();
    }

    @Override
    public void undo() {
    }
}

