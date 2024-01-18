package interfaces;

import enums.Time;
import living.Entity;
import technical.Location;

public interface Walkable {
    private void changeLocation(Entity e, Location loc) {
        if (loc.isArea()) {
            e.setLocation(loc.getArea().getAreaCenter());
        } else {
            e.setLocation(loc);
        }
    }
    default void walk(Location to) {
        changeLocation((Entity) this, to);
        System.out.printf("%s гуляет в %s\n", this.toString(), to.toString());
    }
    default void walk(Location to, Time time) {
        changeLocation((Entity) this, to);
        System.out.printf("%s гуляет в %s %s\n", this.toString(), to.toString(), time.getIncase());
    }
    default void walkTo(Location from, Location to) {
        changeLocation((Entity) this, to);
        System.out.printf("%s идет в %s из %s\n", this.toString(), to.toString(), from.toString());
    }
    default void walkTo(Location from, Location to, Time time) {
        changeLocation((Entity) this, to);
        System.out.printf("%s гуляет в %s из %s %s\n", this.toString(), to.toString(), from.toString(), time.getIncase());
    }
}
