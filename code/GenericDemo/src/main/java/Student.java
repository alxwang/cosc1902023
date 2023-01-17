public class Student extends Person{
    private String id;
    public Student(String name,String ID) {
        super(name);
        this.id=ID;
    }

    public String getId(){return  this.id;}
}
