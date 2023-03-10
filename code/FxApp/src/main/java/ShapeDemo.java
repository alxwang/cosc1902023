import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class ShapeDemo extends  Application{

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new PolygonPane(),500,300);
        stage.setTitle("Shape Demo"); // Set the stage title
        stage.setScene(scene); // Place the scene in the stage
        stage.show(); // Display the stage
    }
}

class PolygonPane extends Pane{
    private void paint(){
        this.getChildren().clear();
        Polygon polygon = new Polygon();
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);

        ObservableList<Double> list = polygon.getPoints();

        double centerx=this.getWidth()/2;
        double centery=this.getHeight()/2;
        double radius = Math.min(getHeight(),getWidth())*0.4;
        for(int i=0;i<6;i++)
        {
            list.add(centerx+radius*Math.cos(2*i*Math.PI/6));
            list.add(centery-radius*Math.sin(2*i*Math.PI/6));
        }
        this.getChildren().add(polygon);
    }

    @Override
    public void setWidth(double width){
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height){
        super.setHeight(height);
        paint();
    }
}

class ArcPane extends Pane{
    public ArcPane()
    {
//        Arc arc = new Arc(150,100,80,80,30,35);
//        arc.setFill(Color.GREEN);
//        arc.setStroke(Color.BLUE);
//        arc.setType(ArcType.ROUND);
//        this.getChildren().add(arc);
        Arc arc1 = new Arc(150, 100, 80, 80, 30, 35); // Create an arc
        arc1.setFill(Color.RED); // Set fill color
        arc1.setType(ArcType.ROUND); // Set arc type

        Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
        arc2.setFill(Color.WHITE);
        arc2.setType(ArcType.OPEN);
        arc2.setStroke(Color.BLACK);

        Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
        arc3.setFill(Color.WHITE);
        arc3.setType(ArcType.CHORD);
        arc3.setStroke(Color.BLACK);

        Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
        arc4.setFill(Color.GREEN);
        arc4.setType(ArcType.CHORD);
        arc4.setStroke(Color.BLACK);

        this.getChildren().addAll(arc4,arc3,arc1,arc2);
    }
}

class EllipsePane extends Pane{
//public EllipsePane(){
//    Ellipse ellipse = new Ellipse(200,100,200,100);
//    ellipse.setFill(Color.ANTIQUEWHITE);
//    ellipse.setStroke(Color.GREEN);
//    this.getChildren().add(ellipse);
//}
    private void paint(){
        this.getChildren().clear();
        for(int i=0;i<16;i++) {
            Ellipse ellipse = new Ellipse(this.getWidth() / 2,
                    this.getHeight() / 2, this.getWidth() / 2 - 50,
                    this.getHeight() / 2 - 50);
            ellipse.setFill(Color.ANTIQUEWHITE);
            ellipse.setStroke(Color.color(Math.random(),Math.random(),Math.random()));
            ellipse.setRotate(i*180/16);
            this.getChildren().add(ellipse);
        }
    }

    @Override
    public void setWidth(double width){
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height){
        super.setHeight(height);
        paint();
    }



}

class RectPane extends Pane{
    public RectPane(){
        Rectangle r1 = new Rectangle(25,10,60,30);
        r1.setStroke(Color.BLUE);
        r1.setFill(Color.WHITE);
        r1.setArcHeight(25);
        r1.setArcWidth(15);

        Rectangle r2 = new Rectangle(25,50,60,30);

        Rectangle r3 = new Rectangle(25,90,60,30);
        r3.setStroke(Color.GOLDENROD);
        r3.setFill(Color.GRAY);


        Group group = new Group();
        group.getChildren().addAll(new Text(10,27,"r1"),r1,
                new Text(10,67,"r2"),r2,
                new Text(10,107,"r3"),r3);
        for(int i =0;i<4;i++)
        {
            Rectangle r = new Rectangle(100,50,100,30);
            r.setRotate(i*360/8);
            r.setStroke(Color.color(Math.random(),Math.random(),Math.random()));
            r.setFill(Color.WHITE);
            group.getChildren().add(r);
        }


        this.getChildren().add(group);


    }

}


class LinePane extends Pane{
    public LinePane()
    {
        Line line1 = new Line(10,10,20,20);
        line1.endXProperty().bind(this.widthProperty().subtract(10));
        line1.endYProperty().bind(this.heightProperty().subtract(10));
        line1.setStrokeWidth(5);
        line1.setStroke(Color.GREEN);
        this.getChildren().add(line1);

        Line line2 = new Line(10,10,20,20);
        line2.startXProperty().bind(this.widthProperty().subtract(10));
        line2.endYProperty().bind(this.heightProperty().subtract(10));
        line2.setStrokeWidth(5);
        line2.setStroke(Color.BLUE);
        this.getChildren().add(line2);
    }
}

