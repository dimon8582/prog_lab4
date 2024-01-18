import Exceptions.BusySlotException;
import Items.BeerBottle;
import Items.Furniture;
import Items.Item;
import enums.DogBreed;
import enums.Emotion;
import enums.Gender;
import enums.Time;
import living.Dog;
import living.Fairy;
import living.Person;
import technical.DatingManager;
import technical.Location;


public class Lab3 {
    public static void main(String[] args) {

        Fairy carlson = new Fairy("Карлсон", -1, "Полёт");
        carlson.setIntelligence(0.7);

        Person junior = new Person("Малыш", 8, Gender.MALE);
        junior.setIntelligence(0.7);

        Person krister = new Person("Кристер", 8, Gender.MALE);
        krister.setIntelligence(0.6);

        Person gunilla = new Person("Гунилла", 8, Gender.FEMALE);
        gunilla.setIntelligence(0.5);

        Dog bimbo = new Dog("Бимбо", 4, Gender.MALE , DogBreed.DACHSHUND);
        bimbo.setStrength(0.6);
        bimbo.setIntelligence(0.4);
        bimbo.setCuteness(8);

        Person fille =  new Person("Филле", 35, Gender.MALE);
        Person rulle =  new Person("Рулле", 33, Gender.MALE);
        fille.setIntelligence(0.6);
        rulle.setIntelligence(0.6);
        fille.setStrength(0.8);
        rulle.setStrength(0.8);
        BeerBottle filleBottle = new BeerBottle(548, 0.8, 225);
        BeerBottle rulleBottle = new BeerBottle(842, 0.8, 345);
        try {
            fille.getInventory().setItem(filleBottle, 0);
            rulle.getInventory().setItem(rulleBottle, 0);
        } catch (BusySlotException e) {
            throw new RuntimeException(e);
        }

        Location vasaPark = new Location("Парк Ваза", 59.327707, 18.092550, 59.328010, 18.092640);
        Furniture[] benchesInPark = new Furniture[5];
        Location.Point vasaParkCenter = vasaPark.getArea().getAreaCenter();
        for (int i = 0; i < 5; i++) {
            benchesInPark[i] = new Furniture(150, 3);
            benchesInPark[i].setLocation(new Location("Скамейка " + i, vasaParkCenter.getLatitude() + (0.00005 - Math.random()*0.0001), vasaParkCenter.getLongitude() + (0.00005 - Math.random()*0.0001)));
            if (i != 4) {
                Person randomPerson1 = new Person("unnamed", 20, Gender.MALE);
                Person randomPerson2 = new Person("unnamed", 20, Gender.MALE);
                Person randomPerson3 = new Person("unnamed", 20, Gender.MALE);
                benchesInPark[i].takeSomePlace(randomPerson1, true);
                benchesInPark[i].takeSomePlace(randomPerson2, true);
                benchesInPark[i].takeSomePlace(randomPerson3, true);
            } else {
                benchesInPark[i].takePlace(fille, 1, false);
                benchesInPark[i].takePlace(rulle, 2, false);
                fille.setLocation(benchesInPark[i].getLocation());
                rulle.setLocation(benchesInPark[i].getLocation());
            }
        }

        junior.setLocation(vasaParkCenter);
        bimbo.setLocation(vasaParkCenter);
        krister.setLocation(new Location("Дом Кристера", 60.322152, 18.024923));
        gunilla.setLocation(new Location("Дом Гуниллы", 60.1428941, 18.198283));

        Dog someAngryDog = new Dog("Антикобылин", 7, Gender.MALE, DogBreed.PITBULL);
        someAngryDog.setStrength(0.7);
        someAngryDog.setIntelligence(0.2);
        someAngryDog.setLocation(vasaPark);
        Dog[] someDogs = {
            new Dog("Макс", (int) Math.floor(Math.random()*10), Gender.MALE,  DogBreed.POODLE),
            new Dog("Люси", (int) Math.floor(Math.random()*10), Gender.FEMALE, DogBreed.LABRADOR),
            new Dog("Тузик", (int) Math.floor(Math.random()*10), Gender.MALE, DogBreed.BEAGLE),
            new Dog("Чарли", (int) Math.floor(Math.random()*10), Gender.MALE, DogBreed.GOLDEN_RETRIEVER),
            new Dog("Молли", (int) Math.floor(Math.random()*10), Gender.FEMALE, DogBreed.SHEPHERD)
        };
        for (int i = 0; i < 5; i++) {
            someDogs[i].setCuteness((int) (Math.random() * 10));
            someDogs[i].setLocation(vasaParkCenter);
        }

        junior.walk(vasaPark, Time.MORNING);
        bimbo.walk(vasaPark, Time.MORNING);


        junior.say("Я всё же думаю, что это гном. Пошли, Бимбо!");
        bimbo.think("Вау, это самый весёлый час за весь день, сейчас здесь обнюхаю всех симпатичных собак и поболтаю с ними");

        for (int i = 0; i < 5; i++) {
            Dog otherDog = someDogs[i];
            bimbo.sniff(otherDog);
            otherDog.sniff(bimbo);
            DatingManager.date(bimbo, otherDog);
            if (bimbo.getEmotion() == Emotion.ANGER) {
                bimbo.barkAt(otherDog);
            }
            if (otherDog.getEmotion() == Emotion.ANGER) {
                otherDog.barkAt(bimbo);
            }
        }
        if (bimbo.getEmotion() == Emotion.HAPPY) {
            bimbo.think("Кайф, день прошёл не зря");
        } else {
            bimbo.think("Ну такое, в следующий раз наверно получится");
        }

        junior.think("А где Кристер и Гунилла?");
        double distToKrister = Location.getDistance(junior.getLocation(), krister.getLocation());
        double distToGunilla = Location.getDistance(junior.getLocation(), gunilla.getLocation());
        if (distToKrister > 0.1 && distToGunilla > 0.1) {
            System.out.println("Малыш не нашёл Кристера и Гуниллу поблизости");
            junior.think("Может быть, они уже уехали на каникулы");
            junior.setEmotion(Emotion.CARELESS);
            junior.think("Ха, плевать, у меня ж есть Карлсон. Ну и Бимбо");
        } else if (distToKrister <= 0.1) {
            System.out.println("Малыш нашёл Кристера поблизости");
            junior.say("Привет Кристер!");
            krister.say("Привет Малыш!");
            junior.setEmotion(Emotion.HAPPY);
            krister.setEmotion(Emotion.HAPPY);
        } else if (distToGunilla <= 0.1) {
            System.out.println("Малыш нашёл Гуниллу поблизости");
            junior.say("О Привет Гунилла!");
            krister.say("Привет Малыш!");
            junior.setEmotion(Emotion.HAPPY);
            krister.setEmotion(Emotion.HAPPY);
        } else {
            System.out.println("Малыш нашёл и Кристера, и Гуниллу поблизости");
            junior.say("Ого, всем привет");
            krister.say("Привет Малыш");
            gunilla.say("Привет Малыш");
            junior.setEmotion(Emotion.HAPPY);
            krister.setEmotion(Emotion.HAPPY);
            gunilla.setEmotion(Emotion.HAPPY);
        }

        someAngryDog.walkTo(someAngryDog.getLocation(), bimbo.getLocation());
        someAngryDog.setEmotion(Emotion.ANGER);

        bimbo.setEmotion(Emotion.COURAGE);
        bimbo.think("Сейчас жёстко разнесу его, сейчас покажу этой тупой псине, что я о ней думаю");
        bimbo.callForFight(someAngryDog);

        junior.say("Хватит Бимбо, погнали отсюда");

        junior.think("Хмм, надо бы найти скамейку в парке");
        Furniture freeBench = null;
        for (int i = 0; i < 5; i++) {
            boolean found = junior.searchForItem(benchesInPark[i]);
            if (found) {
                if (!benchesInPark[i].isFull()) {
                    freeBench = benchesInPark[i];
                }
            }
        }
        if (freeBench == null) {
            System.out.println("Малыш и Бимбо не смогли найти свободную скамейку");
        } else {
            System.out.println("Малыш и Бимбо нашли свободную скамейку");
            if (freeBench.isEmpty()) {
                System.out.println("Эта скамейка пуста");
            } else {
                System.out.println("На ней уже сидели: ");
                for (int i = 0; i < freeBench.getSize(); i++) {
                    if (freeBench.getPlaces()[i] != null) {
                        System.out.println(freeBench.getPlaces()[i].toString());
                    }
                }
            }
            junior.setEmotion(Emotion.FEAR);
            junior.think("Жесть, это же Филле и Рулле, местные охотники с пивом. Надо уходить отсюда");
            junior.setEmotion(Emotion.INTERESTED);
            junior.think("Хотя, они же охотились за Карлсоном, нужно узнать об этом поподробнее. Чё их бояться, они же меня не знают");

            freeBench.takeSomePlace(junior, false);
            bimbo.setLocation(freeBench.getLocation());

            junior.think("Буду короче как детектив буду подслушивать их разговор, как в детективных романах");
        }
    }
}