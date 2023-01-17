interface Comparator15 {
    public int compare(Object o1,Object o2);
}
interface Comparator<T> {
    public int compare(T o1,T o2);
}

class C15 implements Comparator15{

    @Override
    public int compare(Object o1, Object o2) {
        return ((String)o1).compareTo((String)o2);
    }
}

class C implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}

public class APp {


    public static void main(String[] args) {
        C15 c15=new C15();
        C c = new C();
        System.out.println(c15.compare("Alex","Joe"));
        System.out.println(c.compare("Alex","Joe"));
        System.out.println(c15.compare("Alex",new Object()));
        //System.out.println(c.compare("Alex",new Object()));
    }
}
