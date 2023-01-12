import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) throws FileNotFoundException {
        java.io.File file = new java.io.File("test.txt");
        if(!file.exists()) {
            System.out.println("File is not exists,exit");
            System.exit(0);
        }
        try(java.util.Scanner scanner=new Scanner(file)) {
            while(scanner.hasNext()){
                String str = scanner.next();
                System.out.println(str);
            }
        }
    }
}
