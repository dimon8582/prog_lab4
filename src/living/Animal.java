package living;

import enums.Gender;
import enums.Habitat;

public abstract class Animal extends Entity {
    int cuteness = 0;
    Habitat habitat;
    Gender gender;

    public Animal(String name, int age, Gender gender, Habitat habitat) {
        super(name, age);
        this.habitat = habitat;
        this.gender = gender;
    }

    public int getCuteness() {
        return cuteness;
    }

    public void setCuteness(int cuteness) {
        this.cuteness = cuteness;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public Gender getGender() {
        return gender;
    }

    public void callForFight(Animal animal) {
        class FightManager {
            Animal animal1;
            Animal animal2;
            Animal winner;

            public void setAnimal1(Animal animal1) {
                this.animal1 = animal1;
            }

            public void setAnimal2(Animal animal2) {
                this.animal2 = animal2;
            }

            void start() {
                System.out.printf("Началась битва между %s и %s\n", animal1.toString(), animal2.toString());
                double chance = ((animal1.getStrength() + animal2.getStrength()) * Math.random());
                if (chance >= animal1.getStrength()) {
                    setWinner(animal2);
                } else if (chance < animal1.getStrength()) {
                    setWinner(animal1);
                }
                System.out.printf("В битве победил %s!\n", winner.toString());
            }
            Animal getWinner() {
                return winner;
            }
            void setWinner(Animal winner) {
                this.winner = winner;
            }
        }

        FightManager fight = new FightManager();
        fight.setAnimal1(this);
        fight.setAnimal2(animal);
        fight.start();
    }
}
