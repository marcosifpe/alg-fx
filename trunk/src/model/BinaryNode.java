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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author rafael
 */
public class BinaryNode {
    
    private StackPane stackPane;
    private Circle circle;
    private String element;
    private EventHandler eventHandler;
    private int color;
    private double x, y;
    private double circleRadius;
    private BinaryNode parentNode;
    private BinaryNode leftNode;
    private BinaryNode rightNode;
    private boolean rootNode;
    private int height;
    public static int ROOT = 1;
    public static int LEFT_ONLY = 2;
    public static int RIGHT_ONLY = 3;
    

    public BinaryNode(int operation, double radius, String element, double x, 
            double y, int height) {
        
        if (operation == ROOT) {
            System.out.println("ROOT!");
            this.circleRadius = radius;
            this.stackPane = new StackPane();
            this.circle = new Circle(radius, Color.rgb(156, 216, 255));
            this.circle.setEffect(new InnerShadow());
            this.color = 0;
            this.element = element;
            Text text = new Text(element);
            text.setFont(new Font(14.0));
            
            Line line1 = new Line(80, 0, 0 , 120);
            line1.setFill(Color.rgb(156, 216, 255));
            line1.setStroke(Color.rgb(156, 216, 255));
            line1.setEffect(new InnerShadow());
            line1.setStrokeWidth(10);
            line1.setTranslateX(line1.getTranslateX() - 30);
            line1.setTranslateY(line1.getTranslateY() + 40);
            
            Line line2 = new Line(0, 0, 80 , 120);
            line2.setFill(Color.rgb(156, 216, 255));
            line2.setStroke(Color.rgb(156, 216, 255));
            line2.setEffect(new InnerShadow());
            line2.setStrokeWidth(10);
            line2.setTranslateX(line2.getTranslateX() + 30);
            line2.setTranslateY(line2.getTranslateY() + 40);
            this.stackPane.getChildren().addAll(line1, line2, circle, text);
            this.x = x;
            this.y = y;
            this.height = height;
            stackPane.setLayoutX(x);
            stackPane.setLayoutY(y);
            
        } else if (operation == LEFT_ONLY) {
            
        } else {
            
        }
        
        
    }

    public BinaryNode(BinaryNode parentNode, BinaryNode leftNode, BinaryNode rightNode, 
            double radius, String element, double x, double y, int height) {
        this.circleRadius = radius;
        this.stackPane = new StackPane();
        this.circle = new Circle(radius, Color.rgb(156, 216, 255));
        this.circle.setEffect(new InnerShadow());
        this.color = 0;
        this.element = element;
        Text text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(circle, text);
        this.x = x;
        this.y = y;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parentNode = parentNode;
        this.height = height;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);

    }
    
    public BinaryNode(BinaryNode parentNode, BinaryNode leftNode, BinaryNode rightNode, 
            double radius, String element, int operation, double x, double y) {

        this.circleRadius = radius;
        this.stackPane = new StackPane();
        this.circle = new Circle(radius, Color.rgb(156, 216, 255));
        this.circle.setEffect(new InnerShadow());
        this.color = 0;
        this.element = element;
        Text text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(circle, text);
        this.x = x;
        this.y = y;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.parentNode = parentNode;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
    }
    
    public BinaryNode(double radius, String element, int operation, double x, double y) {
//        EventHandler eventHandler1 = generateEventHandler(operation);
        this.circleRadius = radius;
        this.stackPane = new StackPane();
        this.circle = new Circle(radius, Color.rgb(156, 216, 255));
        this.circle.setEffect(new InnerShadow());
        this.color = 0;
        this.element = element;
        Text text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(circle, text);
        this.x = x;
        this.y = y;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
//        stackPane.setOnMousePressed(eventHandler1);
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

    public BinaryNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(BinaryNode parentNode) {
        this.parentNode = parentNode;
    }

    public BinaryNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryNode rightNode) {
        this.rightNode = rightNode;
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

    public boolean isRootNode() {
        return rootNode;
    }

    public void setRootNode(boolean rootNode) {
        this.rootNode = rootNode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
