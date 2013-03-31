/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import execution.Main;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class NodeElement {

    private StackPane stackPane;
    private Circle circle;
    private String element;
    private EventHandler eventHandler;
    private int color;
    private double x, y;
    private double circleRadius;
    private Image arrow;
    private ImageView imageView;
    private Label arrowLabel;
    private Text text;

    public NodeElement() {
    }

    public NodeElement(double radius, String element, int operation, double x, double y) {
        EventHandler eventHandler1 = generateEventHandler(operation);
        this.circleRadius = radius;
        this.stackPane = new StackPane();
        this.circle = new Circle(radius, Color.rgb(156, 216, 255));
        this.circle.setEffect(new InnerShadow());
        this.color = 0;
        this.element = element;
        text = new Text(element);
        text.setFont(new Font(14.0));
        this.stackPane.getChildren().addAll(circle, text);
        this.x = x;
        this.y = y;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
        stackPane.setOnMousePressed(eventHandler1);
    }

    public EventHandler generateEventHandler(int operation) {

        EventHandler eventHandler1 = null;

        switch (operation) {
            case 1:
                eventHandler1 = new EventHandler() {

                    @Override
                    public void handle(Event t) {
                        JOptionPane.showMessageDialog(null, "Teste");
                    }
                };
                break;

            case 2:
                eventHandler1 = new EventHandler() {

                    @Override
                    public void handle(Event t) {

                        if (Main.canChoose) {

                            if (color == 0) {

                                circle.setFill(Color.rgb(120, 201, 178));
                                color = 1;
                                Main.chosenElements++;

                            } else {

                                circle.setFill(Color.rgb(156, 216, 255));
                                color = 0;
                                Main.chosenElements--;

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

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
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

    public double getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(double circleRadius) {
        this.circleRadius = circleRadius;
    }

    public Image getArrow() {
        return arrow;
    }

    public void setArrow(Image arrow) {
        this.arrow = arrow;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void createArrow() {
        arrow = new Image("arrow-down.gif");
        imageView = new ImageView(arrow);

        arrowLabel = new Label("", imageView);
        arrowLabel.setTranslateX(circle.getTranslateX());
        arrowLabel.setTranslateY(circle.getTranslateY() - 80);
        stackPane.getChildren().add(arrowLabel);
    }

    public Label getArrowLabel() {
        return arrowLabel;
    }

    public void setArrowLabel(Label arrowLabel) {
        this.arrowLabel = arrowLabel;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
    
    public void setAnimationElement(int i) {
        this.text.setText(Integer.toString(i));
        
    }
    
}
