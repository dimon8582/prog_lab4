package living;

import interfaces.Flyable;

public class Fairy extends Creation implements Flyable {
    String power;

    public Fairy(String name, int age, String power) {
        super(name, age);
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
