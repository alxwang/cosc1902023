public class Apple {
    public enum Color{Red, Green, Yellow};
    private double weight;
    private Color color;

    public double getWeight(){return  weight;}
    public Color getColor(){return color;};

    public Apple(double weight, Color color)
    {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("Apple Color=%s Weight=%f",this.color.toString(),this.weight);

    }
}
