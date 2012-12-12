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
import javafx.scene.shape.Rectangle;
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
    private Line leftLine, rightLine;
    private Rectangle leftChild, rightChild;
    private boolean rootNode;
    private int height;
    public static int ROOT = 1;
    public static int NORMAL_NODE = 2;
    public static int LEFT_ONLY = 3;
    public static int RIGHT_ONLY = 4;
    

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
            
            leftLine = new Line(70, 0, -50 , 50);
            leftLine.setFill(Color.rgb(156, 216, 255));
            leftLine.setStroke(Color.rgb(156, 216, 255));
            leftLine.setEffect(new InnerShadow());
            leftLine.setStrokeWidth(10);
            leftLine.setTranslateX(leftLine.getTranslateX() - 50);
            leftLine.setTranslateY(leftLine.getTranslateY() + 40);
            
            rightLine = new Line(-50, 0, 70 , 50);
            rightLine.setFill(Color.rgb(156, 216, 255));
            rightLine.setStroke(Color.rgb(156, 216, 255));
            rightLine.setEffect(new InnerShadow());
            rightLine.setStrokeWidth(10);
            rightLine.setTranslateX(rightLine.getTranslateX() + 50);
            rightLine.setTranslateY(rightLine.getTranslateY() + 40);
            
            leftChild = new Rectangle(20, 20, Color.rgb(156, 216, 255));
            leftChild.setEffect(new InnerShadow());
            leftChild.setTranslateX(leftLine.getTranslateX() - 65);
            leftChild.setTranslateY(leftChild.getTranslateY() + 70);
            
            rightChild = new Rectangle(20, 20, Color.rgb(156, 216, 255));
            rightChild.setEffect(new InnerShadow());
            rightChild.setTranslateX(rightLine.getTranslateX() + 65);
            rightChild.setTranslateY(rightChild.getTranslateY() + 70);
            
            this.stackPane.getChildren().addAll(leftLine, rightLine, leftChild, 
                    rightChild, circle, text);
            
            this.x = x;
            this.y = y;
            this.height = height;
            stackPane.setLayoutX(x);
            stackPane.setLayoutY(y);
            
        } else if (operation == NORMAL_NODE) {
            
            this.circleRadius = radius;
            this.stackPane = new StackPane();
            this.circle = new Circle(radius, Color.rgb(156, 216, 255));
            this.circle.setEffect(new InnerShadow());
            this.color = 0;
            this.element = element;
            Text text = new Text(element);
            text.setFont(new Font(14.0));
            
            int size = (40 / height);
            int h = (70 / height);
            leftLine = new Line(size, 0, -size , h);
            leftLine.setFill(Color.rgb(156, 216, 255));
            leftLine.setStroke(Color.rgb(156, 216, 255));
            leftLine.setEffect(new InnerShadow());
            leftLine.setStrokeWidth(10);
            leftLine.setTranslateX(leftLine.getTranslateX() - 50);
            leftLine.setTranslateY(leftLine.getTranslateY() + 40);
            
            rightLine = new Line(-size, 0, size , h);
            rightLine.setFill(Color.rgb(156, 216, 255));
            rightLine.setStroke(Color.rgb(156, 216, 255));
            rightLine.setEffect(new InnerShadow());
            rightLine.setStrokeWidth(10);
            rightLine.setTranslateX(rightLine.getTranslateX() + 50);
            rightLine.setTranslateY(rightLine.getTranslateY() + 40);
            
            leftChild = new Rectangle(20, 20, Color.rgb(156, 216, 255));
            leftChild.setEffect(new InnerShadow());
            leftChild.setTranslateX(leftLine.getTranslateX() - 50);
            leftChild.setTranslateY(leftChild.getTranslateY() + 80);
            
            rightChild = new Rectangle(20, 20, Color.rgb(156, 216, 255));
            rightChild.setEffect(new InnerShadow());
            rightChild.setTranslateX(rightLine.getTranslateX() + 50);
            rightChild.setTranslateY(rightChild.getTranslateY() + 80);
            
            this.stackPane.getChildren().addAll(leftLine, rightLine, leftChild, 
                    rightChild, circle, text);
            
            this.x = x;
            this.y = y;
            this.height = height;
            stackPane.setLayoutX(x);
            stackPane.setLayoutY(y);
            
        } else {
            
        }
        
        
    }
    
    public Line createLine(int height) {
        
        return null;
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

    public Rectangle getLeftChild1() {
        return leftChild;
    }

    public void setLeftChild1(Rectangle leftChild1) {
        this.leftChild = leftChild1;
    }

    public Line getLeftLine() {
        return leftLine;
    }

    public void setLeftLine(Line leftLine) {
        this.leftLine = leftLine;
    }

    public Rectangle getRightChild() {
        return rightChild;
    }

    public void setRightChild(Rectangle rightChild) {
        this.rightChild = rightChild;
    }

    public Line getRightLine() {
        return rightLine;
    }

    public void setRightLine(Line rightLine) {
        this.rightLine = rightLine;
    }
    
}
