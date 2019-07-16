package enums;

import java.util.Random;

public enum Person {
    IRONMAN("Tony Stark"),
    CAPTAINAMERICA("Steve Rogers"),
    HULK("Bruce Banner"),
    THOR("Thor"),
    BLACKWIDOW("Natasha Romanoff"),
    HOWKEYE("Clint Barton"),
    ANTMAN("Scott Lang"),
    STARLORD("Peter Quill"),
    DRSTRANGE("Stephen Strange"),
    ROCKET("Rocket");

    private String person;
    Random random = new Random();

    Person(String person){
        this.person = person;
    }

    public String getPerson(){
        return person;
    }

    public Person getRandomPerson(){
        return values()[random.nextInt(values().length)];
    }
}
