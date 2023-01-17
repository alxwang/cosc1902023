import java.util.ArrayList;

public class GenericStack<E> {
    private ArrayList<E> list =new ArrayList<>();

//    void addTag()
//    {
//        E o = new E();//Not it does not work
//        push(o);
//    }

    public int getSize(){return list.size();}

    public void push(E o){list.add(o);}

    public E pop()
    {
        E o = list.get(getSize()-1);
        list.remove(getSize()-1);
        return o;
    }

    public E peek()
    {
        return list.get(getSize()-1);
    }

    @Override
    public String toString() {
        return "Stack:"+list.toString();
    }
}
