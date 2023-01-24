import java.util.Set;

public class HashSet {
    public static void main(String[] args) {
        Set<String> set =new java.util.HashSet<>();

        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("BeiJing");
        set.add("New York");
        set.add("San Francisco");

        System.out.println(set);

        for(String s:set)
        {
            System.out.println(s.toUpperCase());
        }

        set.forEach(s->System.out.println(s.toUpperCase()));

    }
}
