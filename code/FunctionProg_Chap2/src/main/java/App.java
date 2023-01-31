import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static List<Apple> genApples()
    {
        List<Apple> rs = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            rs.add(new Apple(Math.random()+0.1,Apple.Color.randomColor()));
        }
        return rs;
    }

    public static List<Apple> filterGreen(List<Apple> apples)
    {
        List<Apple> rs = new ArrayList<>();
        for(Apple a: apples)
        {
            if(Apple.Color.Green.equals(a.getColor())) rs.add(a);
        }
        return rs;
    }

    public static List<Apple> filterByColor(List<Apple> apples, Apple.Color color)
    {
        List<Apple> rs = new ArrayList<>();
        for(Apple a: apples)
        {
            if(color.equals(a.getColor())) rs.add(a);
        }
        return rs;
    }

    public static List<Apple> filterByWeight(List<Apple> apples, double weight)
    {
        List<Apple> rs = new ArrayList<>();
        for(Apple a: apples)
        {
            if(a.getWeight()>=weight) rs.add(a);
        }
        return rs;
    }







    public static void main(String[] args) {
        List<Apple> apples = genApples();
//        List<Apple> greenApples = filterGreen(apples);
        List<Apple> greenApples = filterByColor(apples, Apple.Color.Green);
        for(Apple a : greenApples) System.out.println(a);
        System.out.println("--------------------------------------");
        List<Apple> redApples = filterByColor(apples, Apple.Color.Red);
        for(Apple a : redApples) System.out.println(a);
        System.out.println("--------------------------------------");
        List<Apple> bigApples = filterByWeight(apples, 0.5);
        for(Apple a : bigApples) System.out.println(a);
        System.out.println("--------------------------------------");
    }
}
