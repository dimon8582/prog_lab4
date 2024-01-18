package living;

import enums.Gender;
import enums.Time;

public class Person extends Creation{
    Gender gender;
    public Person(String name, int age, Gender gender) {
        super(name, age);
        this.gender = gender;
    }

    public void say(String phrase) {
        System.out.printf("%s говорит: %s\n", this.toString(), phrase);
    }
    public void say(String phrase, Time time) {
        System.out.printf("%s %s говорит: \"%s\"\n", this.toString(), time.getIncase(), phrase);
    }
    public Gender getGender() {
        return gender;
    }
}
