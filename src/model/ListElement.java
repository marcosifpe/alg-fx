/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.event.EventHandler;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author rafael
 */
public class ListElement {
    
    private StackPane stackPane;
    private Circle circle;
    private String element;
    private StackPane next;
    private StackPane previous;
//    private  nextRectangle;
    private EventHandler eventHandler;
    private int color;
    private double x, y;
    private double circleRadius;
    
    public ListElement() {
    }

    public ListElement(double radius, String element, int operation, double x, double y) {
//        EventHandler eventHandler1 = generateEventHandler(operation);
        this.circleRadius = radius;
        this.stackPane = new StackPane();
        this.circle = new Circle(radius, Color.rgb(156, 216, 255));
        this.circle.setEffect(new InnerShadow());
        this.color = 0;
        this.previous = createPrevious(radius, x, y);
        this.next = createNext(radius, x, y);
        this.element = element;
        Text text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(previous, next, circle, text);
        this.x = x;
        this.y = y;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
//        stackPane.setOnMousePressed(eventHandler1);
    }
    public StackPane createNext(double r, double posX, double posY) {
        StackPane sp = new StackPane();
        Rectangle rectangle = new Rectangle(r * 1.5, r / 4, Color.rgb(156, 216, 255));
        rectangle.setEffect(new InnerShadow());
        rectangle.setTranslateX(rectangle.getTranslateX() + (r));
        rectangle.setTranslateY(rectangle.getTranslateY() - (r / 2));
        
        Polygon triangle = new Polygon(
                new double[] {
                    35 , 30 ,
                    35 , 10,
                    55 , 20 
                }); 
        
        triangle.setTranslateX(rectangle.getTranslateX() + (r - 5) );
        triangle.setTranslateY(triangle.getTranslateY() - (r / 2));
        triangle.setFill(Color.rgb(156, 216, 255));
        triangle.setEffect(new InnerShadow());
        sp.getChildren().addAll(rectangle, triangle);
        return sp;
    }
    
    public StackPane createPrevious(double r, double posX, double posY) {
        
        StackPane sp = new StackPane();
        Rectangle rectangle = new Rectangle(r * 1.5, r / 4, Color.rgb(156, 216, 255));
        rectangle.setEffect(new InnerShadow());
        rectangle.setTranslateX(rectangle.getTranslateX() - (r));
        rectangle.setTranslateY(rectangle.getTranslateY() + (r / 2));
//        rectangle.setLayoutX(posX - (r / 2));
//        rectangle.setLayoutY(posY + (r / 2));
        //PREVIOUS
        Polygon triangle = new Polygon(
                new double[] {
                    30 , 35 ,
                    10 , 45 ,
                    30 , 55 
                });
        /* NEXT  */
//        Polygon triangle = new Polygon(
//                new double[] {
//                    35 , 30 ,
//                    35 , 10,
//                    55 , 20 
//                }); 
         /**/
        triangle.setTranslateX(rectangle.getTranslateX() - (r - 5) );
        triangle.setTranslateY(triangle.getTranslateY() + (r / 2));
        triangle.setFill(Color.rgb(156, 216, 255));
        triangle.setEffect(new InnerShadow());
        sp.getChildren().addAll(rectangle, triangle);
        return sp;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public double getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(double circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public StackPane getNext() {
        return next;
    }

    public void setNext(StackPane next) {
        this.next = next;
    }

    public StackPane getPrevious() {
        return previous;
    }

    public void setPrevious(StackPane previous) {
        this.previous = previous;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}
