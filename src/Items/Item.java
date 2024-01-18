package Items;

import Exceptions.InvalidWeightException;
import technical.Location;

public class Item {
    double weight;
    Location location;

    public Item(double weight) {
        this.setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) throws InvalidWeightException {
        this.weight = weight;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
