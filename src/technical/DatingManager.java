package technical;

import enums.Emotion;
import living.Animal;
import living.Creation;
import living.Entity;
import living.Person;

public class DatingManager {
    private DatingManager() {
    }
    public static void date(Entity e1, Entity e2) {
        double s1 = sympathyFactor(e1, e2); // фактор симпотишности e2 по мнению e1
        double s2 = sympathyFactor(e2,e1); // фактор симпотишности e1 по мнению e2
        if (s1 >= 0.75 && s2 >= 0.75) {
            System.out.printf("%s и %s симпатизируют друг другу%n", e1.getName(), e2.getName());
            e1.setEmotion(Emotion.HAPPY);
            e2.setEmotion(Emotion.HAPPY);
        }
        else if (s1 >= 0.75 && s2 < 0.75) { // e1 симпатизирует e2 но не наоборот
            System.out.printf("%s симпатизирует к %s, но это не взаимно(%n", e1.getName(), e2.getName());
            e1.setEmotion(Emotion.getRandomNegative());
            e2.setEmotion(Emotion.CARELESS);
        }
        else if (s1 < 0.75 && s2 >= 0.75) { // e2 симпатизирует e1 но не наоборот
            System.out.printf("%s симпатизирует к %s, но это не взаимно(%n", e2.getName(), e1.getName());
            e2.setEmotion(Emotion.getRandomNegative());
            e1.setEmotion(Emotion.CARELESS);
        } else {
            System.out.printf("%s и %s не нравятся друг другу(%n", e1.getName(), e2.getName());
            e1.setEmotion(Emotion.CARELESS);
            e2.setEmotion(Emotion.CARELESS);
        }
//        System.out.printf("%s %s%n", s1, s2);
    }
    private static double sympathyFactor(Entity e, Entity about) {
        double factor = 0.5;
        if (about instanceof Animal animalAbout) {
            factor *= (double) (1 + animalAbout.getCuteness() / 5);
            if (e instanceof Animal animalEntity) {
                if (animalEntity.getGender() != animalEntity.getGender()) {
                    factor *= 1.5;
                }
            }
        }
        if (about instanceof Creation creationAbout) {
            factor *= (double) (1 + creationAbout.getIntelligence() / 10);
        }
        if (about instanceof Person personAbout) {
            if (e instanceof Person personEntity) {
                if (personAbout.getGender() != personEntity.getGender()) {
                    factor *= 1.5;
                }
            }
        }
        factor *= about.getEmotion().getSympathyAbility();
        factor *= (1 - ((double) Math.abs(e.getAge() - about.getAge()) / 25));
        return factor;
    }
}
