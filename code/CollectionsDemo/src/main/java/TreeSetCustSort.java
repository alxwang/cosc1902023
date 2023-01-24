import java.util.Set;

public class TreeSetCustSort {
    public static void main(String[] args) {
        Set<String> set =new java.util.TreeSet<>();
        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("BeiJing");
        System.out.println(set);

        Set<String> set1 =new java.util.TreeSet<>
                (new CustStringComparetor());
        set1.add("London");
        set1.add("Paris");
        set1.add("New York");
        set1.add("San Francisco");
        set1.add("BeiJing");
        System.out.println(set1);
    }
}
