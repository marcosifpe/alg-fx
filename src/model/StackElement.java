/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import execution.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class StackElement {
    
    private StackPane stackPane;
    private Rectangle rectangle;
    private String element;
    private EventHandler eventHandler;
    private int color;
    private double x, y;
    private double width, height;

    public StackElement() {}

    public StackElement(double width, double height, String element, int operation, 
            double x, double y) {
        EventHandler eventHandler1 = generateEventHandler(2);
        this.width = width;
        this.height = height;
        this.stackPane = new StackPane();
        this.rectangle = new Rectangle(width, height, Color.rgb(156, 216, 255));
        this.rectangle.setEffect(new InnerShadow());
        this.color = 0;
        this.element = element;
        Text text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(rectangle, text);
        this.x = x;
        this.y = y;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
//        stackPane.setTranslateX(x);
//        stackPane.setTranslateY(y);
        stackPane.setOnMousePressed(eventHandler1);
    }
    
    public StackElement(double width, double height, String element, int operation, 
            double x, double y, int handler) {
        EventHandler eventHandler1 = generateEventHandler(handler);
        this.width = width;
        this.height = height;
        this.stackPane = new StackPane();
        this.rectangle = new Rectangle(width, height, Color.rgb(156, 216, 255));
        this.rectangle.setEffect(new InnerShadow());
        this.color = 0;
        this.element = element;
        Text text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(rectangle, text);
        this.x = x;
        this.y = y;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
//        stackPane.setTranslateX(x);
//        stackPane.setTranslateY(y);
        stackPane.setOnMousePressed(eventHandler1);
    }

    public EventHandler generateEventHandler(int operation) {

        EventHandler eventHandler1 = null;

        switch (operation) {
            case 1:
                eventHandler1 = new EventHandler() {

                    @Override
                    public void handle(Event t) {

                        if (Main.canChooseHeadTail) {

                            if (color == 0) {

                                rectangle.setFill(Color.rgb(120, 201, 178));
                                color = 1;
                                Main.chosenHeadTails++;

                            } else {

                                rectangle.setFill(Color.rgb(156, 216, 255));
                                color = 0;
                                Main.chosenHeadTails--;

                            }

                        }
                    }
                };
                break;

            case 2:
                eventHandler1 = new EventHandler() {

                    @Override
                    public void handle(Event t) {

                        if (Main.canChooseStack) {

                            if (color == 0) {

                                rectangle.setFill(Color.rgb(120, 201, 178));
                                color = 1;
                                Main.chosenStacks++;

                            } else {

                                rectangle.setFill(Color.rgb(156, 216, 255));
                                color = 0;
                                Main.chosenStacks--;

                            }

                        }
                    }
                };
                break;

        }

        return eventHandler1;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getElementAsInt() {
        return Integer.parseInt(element);
    }

    public void setElementAsInt(int element) {
        this.element = Integer.toString(element);
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
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
