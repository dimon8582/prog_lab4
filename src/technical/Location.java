package technical;

import Exceptions.LocationIsNotAreaException;
import Exceptions.LocationIsNotPointException;

import java.util.Objects;

public class Location {
    String name;
    Area area;
    Point point;

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.point = new Point(lat, lon);
    }
    public Location(String name, Point point) {
        this.name = name;
        this.point = new Point(point.getLongitude(), point.getLongitude());
    }
    public Location(String name, Point point1, Point point2) {
        this.name = name;
        this.area = new Area(point1, point2);
    }
    public Location(String name, double lat1, double lon1, double lat2, double lon2) {
        this.name = name;
        Point point1 = new Point(lat1, lon1);
        Point point2 = new Point(lat2, lon2);
        this.area = new Area(point1, point2);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }
    public Area getArea() {
        return area;
    }

    public void setCoordinates(double lat, double lon) {
        this.point = new Point(lat, lon);
        this.area = null;
    }
    public void setCoordinates(Point point) {
        this.point = new Point(point.getLatitude(), point.getLongitude());
        this.area = null;
    }
    public void setCoordinates(Point p1, Point p2) {
        this.area = new Area(p1, p2);
        this.point = null;
    }



    public static double getDistance(Location loc1, Location loc2) throws LocationIsNotPointException {
        if ((!loc1.isArea()) && (!loc2.isArea())) {
            return Math.sqrt(Math.pow(Math.abs(loc1.getPoint().getLatitude() - loc2.getPoint().getLatitude()), 2) + Math.pow(Math.abs(loc1.getPoint().getLongitude() - loc2.getPoint().getLongitude()), 2));
        } else {
            throw new LocationIsNotPointException("Локации должны быть точками, чтобы рассчитать между ними расстояние");
        }
    }

    public boolean isArea() {
        if (this.point == null && this.area != null) {
            return true;
        } else if (this.point != null && this.area == null) {
            return false;
        }
        return false;
    }

    public class Area {
        Point point1, point2;

        Area(Point point1, Point point2) {
            this.point1 = point1;
            this.point2 = point2;
        }

        public Point getPoint1() {
            return point1;
        }

        public void setPoint1(Point point1) {
            this.point1 = point1;
        }

        public Point getPoint2() {
            return point2;
        }

        public void setPoint2(Point point2) {
            this.point2 = point2;
        }

        public Point getAreaCenter() throws LocationIsNotAreaException{
            Point p1 = this.getPoint1();
            Point p2 = this.getPoint2();
            double difLat = Math.abs(p1.getLatitude() - p2.getLatitude());
            double difLong = Math.abs(p1.getLongitude() - p2.getLongitude());
            double lat = Math.min(p1.getLatitude(), p2.getLatitude()) + difLat * 0.5;
            double lon = Math.min(p1.getLongitude(), p2.getLongitude()) + difLong * 0.5;
            Point center = new Point(lat, lon);
            return center;
        }
    }
    public class Point {
        double lat, lon;
        public Point(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
        public double getLatitude() {
            return lat;
        }

        public void setLatitude(double latitude) {
            this.lat = latitude;
        }

        public double getLongitude() {
            return lon;
        }

        public void setLongitude(double longitude) {
            this.lon = longitude;
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Location)) return false;
        Location loc = (Location) obj;
        if (this.isArea()) {
            Area a1 = this.getArea();
            Area a2 = this.getArea();
            return (loc.name == this.name) && (a1.getPoint1().getLatitude() == a2.getPoint1().getLatitude()) && (a1.getPoint1().getLongitude() == a2.getPoint1().getLongitude()) && (a1.getPoint2().getLatitude() == a2.getPoint2().getLatitude()) && (a1.getPoint2().getLongitude() == a2.getPoint2().getLongitude());
        } else {
            Point p1 = this.getPoint();
            Point p2 = loc.getPoint();
            return (loc.name == this.name) && (p1.getLatitude() == p2.getLatitude()) && (p1.getLongitude() == p2.getLongitude());
        }
    }

    @Override
    public int hashCode() {
        if (this.isArea()) {
            Area a = this.getArea();
            return (int) (Objects.hash(this.name) * 11 + Math.round(a.getPoint1().getLatitude() * 1000) * 13 + Math.round(a.getPoint2().getLatitude() * 1000) * 13 + Math.round(a.getPoint1().getLongitude() * 1000) * 1313 + Math.round(a.getPoint2().getLongitude() * 1000) * 1313);
        } else {
            Point p = this.getPoint();
            return (int) (Objects.hash(this.name) * 11 + Math.round(p.getLatitude() * 1000) * 13 + Math.round(p.getLongitude() * 1000) * 1313);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
