package Salakhov.Lesson.Comparators;

import Salakhov.Lesson.Human;

import java.util.Comparator;

public class ComparatorHuman {
    public Comparator<Salakhov.Lesson.Human> humanComparatorByFio = new Comparator<Human>() {
        @Override
        public int compare(Human o1, Human o2) {
            return o1.getFio().compareTo(o2.getFio());
        }
    };
    public Comparator<Salakhov.Lesson.Human> humanComparatorByAge = new Comparator<Human>() {
        @Override
        public int compare(Human o1, Human o2) {
            if (o1.getAge() == o2.getAge()) return 0;
            return o1.getAge() > o2.getAge() ? 1 : -1;
        }
    };
    public Comparator<Salakhov.Lesson.Human> humanComparatorByAddress = new Comparator<Human>() {
        @Override
        public int compare(Human o1, Human o2) {
            return o1.getAddress().toString().compareTo(o2.getAddress().toString());
        }
    };
}
