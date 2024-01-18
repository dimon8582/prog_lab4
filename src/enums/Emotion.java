package enums;

public enum Emotion {
    NEUTRAL("ни о чем не переживает", 1.25),
    SHAME("стыдно сейчас", 0.9),
    FEAR("очень страшно", 0.85),
    ANGER("испытывает злость", 0.6),
    CARELESS("испытывает безразличие", 0.9),
    HAPPY("чувствует себя счастливо", 2),
    SAD("испытывает грусть", 0.75),
    COURAGE("чувствует себя смело", 1.5),
    INTERESTED("выглядит заинтересованным", 1.5);

    private final String incase;
    private final double sympathyAbility;
    public final static Emotion[] NEGATIVE = {Emotion.SAD, Emotion.SHAME, Emotion.ANGER, Emotion.FEAR};
    public String getIncase() {
        return incase;
    }
    public double getSympathyAbility() {
        return sympathyAbility;
    }
    public static Emotion getRandomNegative() {
//        final Emotion[] NEGATIVE = {Emotion.SAD, Emotion.SHAME, Emotion.ANGER, Emotion.FEAR};
        int idx = (int) Math.floor(Math.random() * NEGATIVE.length);
        return NEGATIVE[idx];
    }
    Emotion(String incase, double sympathyAbility) {
        this.incase = incase;
        this.sympathyAbility = sympathyAbility;
    }
}
