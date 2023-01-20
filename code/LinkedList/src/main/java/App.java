public class App {
    public static void main(String[] args) {
        LList<Integer> list = new LList<>();
        list.addTail(1);
        System.out.println("1 Added\n");
        list.addTail(2);
        System.out.println("2 Added\n");
        list.addTail(3);
        System.out.println("3 Added\n");
        list.addTail(4);
        System.out.println("4 Added\n");

        System.out.println(list.find(3));

        list.addHead(0);
        list.addTail(6);
        System.out.println(list);
        //[0(->1)][1(->2)][2(->3)][3(->4)][4(->6)][6(->NULL)]


        list.insertAfter(list.find(4),5);
        System.out.println(list);
        //[0(->1)][1(->2)][2(->3)][3(->4)][4(->5)][5(->6)][6(->NULL)]

        list.remove(list.find(4));
        System.out.println(list);
        //[0(->1)][1(->2)][2(->3)][3(->5)][5(->6)][6(->NULL)]

        list.swap(list.find(1),list.find(3));
        System.out.println(list);
        //[0(->1)][3(->2)][2(->3)][1(->5)][5(->6)][6(->NULL)]

        System.out.println(LList.max(list));

        LList.sort(list);
        System.out.println(list);

    }
}
