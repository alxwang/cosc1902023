import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Order implements Comparable<Order> {
    private String firstName;
    private String lastName;
    private Date orderTime;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Order(String firstName,String lastName)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.orderTime = new Date();
    }
    public Order(String str) throws ParseException {
        String[] lst = str.split(",");
        this.firstName=lst[1];
        this.lastName=lst[2];
        this.orderTime = format.parse(lst[3]);
    }

    @Override
    public int compareTo(Order o) {
        return this.orderTime.compareTo(o.orderTime);
    }

    @Override
    public String toString() {
        return String.format("ORDER,%s,%s,%s",firstName,lastName,format.format(orderTime));
    }
}
