import java.io.Serializable;

public class AirCraft implements Serializable {
    int year;
    public int getYear() {return year;}
    String name;
    public String getName(){return this.name;}
    String country;
    public String getCountry(){return this.country;}
    public AirCraft(String name,String country,int year)
    {
        this.name=name;
        this.country=country;
        this.year=year;
    }

}
