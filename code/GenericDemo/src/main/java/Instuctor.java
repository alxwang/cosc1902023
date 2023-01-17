public class Instuctor extends Person{
    private String program;
    public Instuctor(String name,String program) {
        super(name);
        this.program=program;
    }

    public String getProgram(){return program;}
}
