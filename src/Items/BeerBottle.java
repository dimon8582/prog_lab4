package Items;

public class BeerBottle extends Nutrition{
    double kcal;

    public BeerBottle(double weight, double tasteness, double kcal) {
        super(weight, tasteness);
        this.kcal = kcal;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public static class BeerSubstance {
        static double alcoLevel = 0.05;

        public static double getAlcoLevel() {
            return alcoLevel;
        }

        public static void setAlcoLevel(double alcoLevel) {
            BeerSubstance.alcoLevel = alcoLevel;
        }
    }
}
