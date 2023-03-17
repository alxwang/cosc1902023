import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
public class ClockPane extends Pane{
    private int hour;
    public int getHour(){return this.hour;}

    public void setHour(int hour) {
        this.hour = hour;
    }

    private int minute;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private int second;

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public ClockPane()
    {
        setCurrentTime();
    }

    public void setCurrentTime()
    {
        Calendar calendar = new GregorianCalendar();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute=calendar.get(Calendar.MINUTE);
        this.second=calendar.get(Calendar.SECOND);
        paint();
    }

    private void paint(){
        this.getChildren().clear();
        double centerX = getWidth()/2;
        double centerY = getHeight()/2;
        double radius = Math.min(getWidth(),getHeight())*0.4;
        Circle circle = new Circle(centerX,centerY,radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 =new Text(centerX-5,centerY-radius+12,"12");
        Text t2 = new Text(centerX-radius+3,centerY+5,"9");
        Text t3 = new Text(centerX+radius-10,centerY+3,"3");
        Text t4 = new Text(centerX-3,centerY+radius-3,"6");

        double sLength = radius*0.8;
        double secondX = centerX+sLength*Math.sin(second*(2*Math.PI/60));
        double secondY = centerY-sLength*Math.cos(second*(2*Math.PI/60));
        Line sLine = new Line(centerX,centerY,secondX,secondY);
        sLine.setStroke(Color.RED);

        double mLength = radius*0.65;
        double minX = centerX+mLength*Math.sin(minute*(2*Math.PI/60));
        double minY = centerY-mLength*Math.cos(minute*(2*Math.PI/60));
        Line mLine = new Line(centerX,centerY,minX,minY);
        mLine.setStroke(Color.BLUE);

        double hLengh = radius*0.5;
        double hourX = centerX + hLengh*
                Math.sin((hour%12+minute/60.0)*(2*Math.PI)/12);
        double hourY = centerY - hLengh*
                Math.cos((hour%12+minute/60.0)*(2*Math.PI)/12);
        Line hLine = new Line(centerX,centerY,hourX,hourY);
        hLine.setStroke(Color.GREEN);



        this.getChildren().addAll(circle,t1,t2,t3,t4,
                sLine,mLine,hLine);



    }

    @Override
    public void setWidth(double width){
        super.setWidth(width);
        setCurrentTime();
        paint();
    }

    @Override
    public void setHeight(double height){
        super.setHeight(height);
        setCurrentTime();
        paint();
    }

}
