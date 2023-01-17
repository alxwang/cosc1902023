//interface Comparator15 {
//    public int compare(Object o1,Object o2);
//}
//interface Comparator<T> {
//    public int compare(T o1,T o2);
//}
//
//class C15 implements Comparator15{
//
//    @Override
//    public int compare(Object o1, Object o2) {
//        return ((String)o1).compareTo((String)o2);
//    }
//}
//
//class C implements Comparator<String>{
//
//    @Override
//    public int compare(String o1, String o2) {
//        return o1.compareTo(o2);
//    }
//}
//
//class PC implements PeronCompartor<Student>
//{
//    @Override
//    public int compare(Student o1, Student o2) {
//        return 0;
//    }
//}
//
//class PC1 implements PeronCompartor<Instuctor>
//{
//    @Override
//    public int compare(Instuctor o1, Instuctor o2) {
//        return 0;
//    }
//}
//
//class PCAll implements PeronCompartor<Person>
//{
//
//    @Override
//    public int compare(Person o1, Person o2) {
//        return 0;
//    }
//}

//class pcnum implements NumComparetor<String>





public class APp {
//    public static <E> E max(E o1, E o2)
    public static <E extends Comparable<E>> E max(E o1, E o2)
    {
        Comparable<E> c1 = (Comparable<E>) o1;
        Comparable<E> c2 = (Comparable<E>) o2;
        if(c1.compareTo(o2)>=0) return o1;
        else return  o2;
  }


    public static void main(String[] args) {
//        C15 c15=new C15();
//        C c = new C();
//        System.out.println(c15.compare("Alex","Joe"));
//        System.out.println(c.compare("Alex","Joe"));
//        System.out.println(c15.compare("Alex",new Object()));
        //System.out.println(c.compare("Alex",new Object()));

        System.out.println(max(1,"Welcome"));

    }
}
