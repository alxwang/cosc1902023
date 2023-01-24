import java.util.Comparator;

public class CustStringComparetor implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.charAt(o1.length()-1)-o2.charAt(o2.length()-1);
    }
}
