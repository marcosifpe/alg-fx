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
public class CountingBox {
    
    private StackPane stackPane, countingPane, positionPane;
    private Rectangle rectangle, countingRectangle, positionRectangle;
    private String element, elementP, elementC;
    private EventHandler eventHandler;
    private NodeElement node;
    private int color;
    private double x, y;
    private double width, height;
    private Text text, textP, textC;
    
    public CountingBox() {}

    public CountingBox(double width, double height, String countingElement,
            String positionElement, int operation, double x, double y) {
        
        EventHandler eventHandler1 = generateEventHandler(2);
        this.width = width;
        this.height = height;
        this.stackPane = new StackPane();
        this.countingPane = new StackPane();
        this.positionPane = new StackPane();
        this.color = 0;
        this.countingRectangle = new Rectangle(width, height, Color.rgb(165, 42, 42));
        this.countingRectangle.setEffect(new InnerShadow());
        this.elementC = countingElement;
        this.elementP = positionElement;
        
        textC = new Text(elementC);
//        textC = new Text(elementC);
        textC.setFont(new Font(14.0));
        this.countingPane.getChildren().addAll(countingRectangle, textC);
        
        this.positionRectangle = new Rectangle(width, height / 2, Color.rgb(165, 180, 82));
        this.positionRectangle.setEffect(new InnerShadow());
        
        textP = new Text(elementP);
        textP.setFont(new Font(14.0));
        StackPane pan = new StackPane();
        pan.getChildren().addAll(textP);
        pan.setTranslateY(this.stackPane.getTranslateY() + 60.0);
        this.positionPane.getChildren().addAll(positionRectangle, pan);
        this.positionRectangle.setTranslateY(this.positionRectangle.getTranslateY() + 60.0);
        
        this.stackPane.getChildren().addAll(countingPane, positionPane);
        this.x = x;
        this.y = y;
        stackPane.setLayoutX(x);
        stackPane.setLayoutY(y);
        stackPane.setOnMousePressed(eventHandler1);

    }
    
    public int getColor() {
        return color;
    }
    
    public void insertNodeElement(NodeElement node) {
        this.node = node;
//        this.getStackPane().getChildren().add(node.getStackPane());
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

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
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

    public NodeElement getNode() {
        return node;
    }

    public void setNode(NodeElement node) {
        this.node = node;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public StackPane getCountingPane() {
        return countingPane;
    }

    public void setCountingPane(StackPane countingPane) {
        this.countingPane = countingPane;
    }

    public StackPane getPositionPane() {
        return positionPane;
    }

    public void setPositionPane(StackPane positionPane) {
        this.positionPane = positionPane;
    }
    
    public EventHandler generateEventHandler(int operation) {

        EventHandler eventHandler1 = null;

        switch (operation) {

            case 2:
                eventHandler1 = new EventHandler() {

                    @Override
                    public void handle(Event t) {

                        if (Main.canChooseBox) {

                            if (color == 0) {
                                countingRectangle.setFill(Color.rgb(120, 201, 178));
                                positionRectangle.setFill(Color.rgb(120, 201, 178));
                                color = 1;
                                Main.chosenBoxes++;

                            } else {

                                countingRectangle.setFill(Color.rgb(165, 42, 42));
                                positionRectangle.setFill(Color.rgb(165, 180, 82));
                                color = 0;
                                Main.chosenBoxes--;

                            }

                        }
                    }
                };
                break;

        }

        return eventHandler1;
    }

    public Rectangle getCountingRectangle() {
        return countingRectangle;
    }

    public void setCountingRectangle(Rectangle countingRectangle) {
        this.countingRectangle = countingRectangle;
    }

    public Rectangle getPositionRectangle() {
        return positionRectangle;
    }

    public void setPositionRectangle(Rectangle positionRectangle) {
        this.positionRectangle = positionRectangle;
    }
    
    public void setCountingValue(int number) {
        this.elementC = Integer.toString(number);
        this.textC.setText(Integer.toString(number));
    }

    public String getElementC() {
        return elementC;
    }

    public void setElementC(String elementC) {
        this.elementC = elementC;
    }

    public String getElementP() {
        return elementP;
    }

    public void setElementP(String elementP) {
        this.elementP = elementP;
    }

    public Text getTextC() {
        return textC;
    }

    public void setTextC(Text textC) {
        this.textC = textC;
    }

    public Text getTextP() {
        return textP;
    }

    public void setTextP(Text textP) {
        this.textP = textP;
    }
    
}
