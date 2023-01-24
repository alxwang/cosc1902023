import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        Set<String> set =new java.util.HashSet<>();
        long start = System.currentTimeMillis();
        for(int i=0;i<1;i++) {
            set.add("London" + i);
            set.add("Paris" + i);
            set.add("New York" + i);
            set.add("San Francisco" + i);
            set.add("BeiJing" + i);
        }
        TreeSet<String>treeset = new TreeSet<>(set);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        //772 for hashset 24
        //1613 for tree set 35
        //Combined 2325???? 56
        System.out.println(treeset);
        System.out.println("First->"+treeset.first());
        System.out.println("Last->"+treeset.last());
        System.out.println("Headset(New York->"+treeset.headSet("New York"));
        System.out.println("Tailset(New York->"+treeset.tailSet("New York"));

        System.out.println("lower(P)->"+treeset.lower("P"));
        System.out.println("higher(P)->"+treeset.higher("P"));
        System.out.println("ceiling((P)->"+treeset.ceiling("P"));
        System.out.println("floor((P)->"+treeset.floor("P"));

        System.out.println("pollFirst->"+treeset.pollFirst());
        System.out.println("pollLast->"+treeset.pollLast());
        System.out.println(treeset);
    }
}
