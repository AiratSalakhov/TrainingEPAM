package main.java.Salakhov.Lesson.Comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ComparatorHashMap {
    public Comparator<HashMap.Entry> hashMapComparator = new Comparator<HashMap.Entry>() {
        @Override
        public int compare(Map.Entry o1, Map.Entry o2) {
            return o1.getValue().toString().compareTo(o2.getValue().toString());
        }
    };

}
