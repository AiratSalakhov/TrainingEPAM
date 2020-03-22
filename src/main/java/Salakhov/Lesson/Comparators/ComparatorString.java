package Salakhov.Lesson.Comparators;

import java.util.Comparator;

public class ComparatorString {
    public Comparator<String> stringComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };
}
