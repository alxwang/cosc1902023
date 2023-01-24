import java.util.*;

public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(128);
        list.add(1);//add from Collection
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(4);

        //index is the position of the new elem should be zero based
        list.add(0,10); //add from List
        list.add(3,30);

        list.addAll(Arrays.asList(100,200,300));
        System.out.println(list);

        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(0,"red");
        linkedList.removeLast();
        linkedList.addFirst("green");
        linkedList.addAll(Arrays.asList("red","blue","black"));
        System.out.println(linkedList);

        //Display the linkedlist with Iterator forward with Collection.Iterator
        Iterator<Object> iterator = linkedList.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());

        //Display the linkedlist with Iterator backward
        ListIterator<Object> listIterator = linkedList.listIterator(linkedList.size());
        while(listIterator.hasPrevious())
            System.out.println(listIterator.previous());

        //Display the linkedlist with ListIterator forward with List.ListIterator
        ListIterator<Object> listIterator1 = linkedList.listIterator(0);
        while(listIterator1.hasNext())
            System.out.println(listIterator1.next());







//        ArrayList<String> list = new ArrayList<>();
//        list.add("alex");
//
//        //List<String> list1 = new ArrayList<>();
//        Collection<String> list1 = new Vector<>();
//
//        list1.add("alex");
//        list1.add("joe");
//        list1.add("max");
//        list1.add("kelci");
//
//        for(String name:list1) System.out.println(name);
//
//        list1.forEach(name -> System.out.println(name));
//
//        Iterator<String> iterator = list1.iterator();
//        while(iterator.hasNext())
//            System.out.println(iterator.next());
    }
}

