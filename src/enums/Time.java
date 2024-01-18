package enums;

public enum Time {
    MORNING("Утро", "ранним утром"),
    NOON("Полдень", "в полдень"),
    EVENING("Вечер", "поздним вечером"),
    NIGHT("Ночь", "тёмной ночью");

    private String title;
    private String incase;
    Time(String title, String incase) {
        this.title = title;
        this.incase = incase;
    }
    public String getTitle() {
        return title;
    }
    public String getIncase() {
        return incase;
    }
    @Override
    public String toString() {
        return title;
    }
}
