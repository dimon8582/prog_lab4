package interfaces;

import enums.Time;
import technical.Location;

public interface Flyable {
    public default void fly(Location at) {
        System.out.printf("%s полетел", this.toString());
    };
    public default void fly(Location at, Time time) {
        System.out.printf("%s полетел %s\n", this.toString(), time.getIncase());
    };
    public default void flyTo(Location from, Location at) {
        System.out.printf("%s полетел из %s в %s\n", this.toString(), from.toString(), at.toString());
    }
    public default void flyTo(Location from, Location at, Time time) {
        System.out.printf("%s полетел из %s в %s %s\n", this.toString(), from.toString(), at.toString(), time.getIncase());
    }
}
