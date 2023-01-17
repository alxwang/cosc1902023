public class App {
    public static void main(String[] args) {
        GenericStack<String> stack=new GenericStack<>();
        stack.push("alex");
        stack.push("joe");
        stack.push("max");
        stack.push("kelci");
        //stack.push(1.23);
        System.out.println(stack);
    }
}
