


package living;

import Exceptions.InvalidAgeException;
import Exceptions.LocationIsNotAreaException;
import Items.Item;
import enums.Emotion;
import interfaces.Searchable;
import technical.Location;

import java.util.Objects;

public
class Entity {
    String name;
    int age;
    Emotion emotion;
    Location location;
    double intelligence;
    double strength;

    public Entity(String name, int age) {
        this.name = name;
        this.setAge(age);
        this.setEmotion(Emotion.NEUTRAL, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidAgeException{
        this.age = age;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
        System.out.printf("%s %s\n", this.name, emotion.getIncase());
    }
    public void setEmotion(Emotion emotion, boolean notify) {
        this.emotion = emotion;
        if (notify) {
            System.out.printf("%s %s\n", this.name, emotion.getIncase());
        }
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void think(String thought) {
        System.out.printf("%s думает: \"%s\"\n", this.name, thought);
    }

    public void eat(String food) {
        System.out.printf("%s ест %s\n", this.name, food);
        food = null;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setLocation(Location.Point point) {
        this.location = new Location("unnamed", point);
    }

    public boolean inArea(Location loc) throws LocationIsNotAreaException {
        if (loc.isArea()) {
            Location.Area area = loc.getArea();
            Location.Point point1 = area.getPoint1();
            Location.Point point2 = area.getPoint2();
            Location.Point ePoint = this.getLocation().getPoint();
            if ((ePoint.getLatitude() - point1.getLatitude()) * (ePoint.getLatitude() - point2.getLatitude()) < 0) {
                if ((ePoint.getLongitude() - point1.getLongitude()) * (ePoint.getLongitude() - point2.getLongitude()) < 0) {
                    return true;
                }
            }
            return false;
        } else {
            throw new LocationIsNotAreaException("Локация не является зоной");
        }
    }

    // 0.0001 - обзор
    public boolean searchForEntity(Entity searching) {
        Location searcherLoc = this.getLocation();
        Location searchingLoc = searching.getLocation();
        boolean result;

        Searchable EntitySearcher = new Searchable() {
            Location searcherLoc;
            Location searchingLoc;
            boolean result;
            @Override
            public void search(Location searcherLoc, Location searchingLoc) {
                this.searcherLoc = searcherLoc;
                this.searchingLoc = searchingLoc;
                double between = Location.getDistance(searcherLoc, searchingLoc);
                if (between < 0.001) {
                    setResult(true);
                }
            }
            @Override
            public boolean getResult() {
                return result;
            }
            @Override
            public void setResult(boolean result) {
                this.result = true;
            }
        };

        EntitySearcher.search(searcherLoc, searchingLoc);
        result = EntitySearcher.getResult();
        return result;
    }
    public boolean searchForItem(Item searching) {
        Location searcherLoc = this.getLocation();
        Location searchingLoc = searching.getLocation();
        boolean result;

        Searchable ItemSearcher = new Searchable() {
            Location searcherLoc;
            Location searchingLoc;
            boolean result;
            @Override
            public void search(Location searcherLoc, Location searchingLoc) {
                this.searcherLoc = searcherLoc;
                this.searchingLoc = searchingLoc;
                double between = Location.getDistance(searcherLoc, searchingLoc);
                if (between < 0.0005) {
                    setResult(true);
                }
            }
            @Override
            public boolean getResult() {
                return result;
            }
            @Override
            public void setResult(boolean result) {
                this.result = true;
            }
        };

        ItemSearcher.search(searcherLoc, searchingLoc);
        result = ItemSearcher.getResult();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Entity e2)) return false;
        return Objects.equals(e2.name, this.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name) * 11 + age * 31;
    }

    @Override
    public String toString() {
        return name;
    }
}