package Items;

public class Nutrition extends Item{
    double tasteness;

    public Nutrition(double weight, double tasteness) {
        super(weight);
        this.tasteness = tasteness;
    }

    public double getTasteness() {
        return tasteness;
    }

    public void setTasteness(double tasteness) {
        this.tasteness = tasteness;
    }
}
