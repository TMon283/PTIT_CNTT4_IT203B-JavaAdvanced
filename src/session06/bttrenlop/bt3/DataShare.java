package session06.bttrenlop.bt3;

public class DataShare {
    private int number;
    private int control = 1;

    public DataShare() {
    }

    public DataShare(int number, int control) {
        this.number = number;
        this.control = control;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
