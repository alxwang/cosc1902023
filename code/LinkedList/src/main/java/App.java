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
    }
}
