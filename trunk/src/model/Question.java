/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import execution.Constants;
import execution.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class Question {

    private int chosenElements;
    private int chosenBoxes;
    private int correctBox;
    private int firstPosition;
    private int secondPosition;
    private NodeElement nodes[];
    private CountingBox boxes[];
    private boolean answered = false;
    public static final int ELEMENT_CHANGE = 1, BOX_SELECTION = 2, ELEMENT_SELECTION = 3;
    

    public Question(int chosenElements, int firstPosition, int secondPosition, NodeElement[] nodes) {
        this.chosenElements = chosenElements;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.nodes = nodes;
    }
    
    public Question(int chosenBoxes, int correctBox, 
            NodeElement[] nodes, CountingBox[] boxes) {
        this.chosenBoxes = chosenBoxes;
        this.correctBox = correctBox;
        this.nodes = nodes;
        this.boxes = boxes;
    }

    public int getChosenElements() {
        return chosenElements;
    }

    public void setChosenElements(int chosenElements) {
        this.chosenElements = chosenElements;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public NodeElement[] getNodes() {
        return nodes;
    }

    public void setNodes(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(int secondPosition) {
        this.secondPosition = secondPosition;
    }
    
    public boolean askBoxes(int operation) {
        
        if (operation == BOX_SELECTION) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.BOX_SELECTION);
        } else if (operation == ELEMENT_SELECTION) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.ELEMENT_SELECTION);
        }
        
        boolean response = false;

        while (!hasAnswered()) {

            if (Main.chosenBoxes == 1) {

                if (this.boxes[this.correctBox].getColor() == 1) {

                    Main.events.setText(Constants.EVENT + "\n\n"
                            + "CORRETO!");
                    response = true;

                } else {

                    Main.events.setText(Constants.EVENT + "\n\n"
                            + "ERRADO! Tente novamente.");
                    response = false;
                }

                answered = true;

            }

        }
        
        return response;
        
    }
    
    public boolean ask(int operation) {

        if (operation == ELEMENT_CHANGE) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.ELEMENT_CHANGE);
        }
        
        boolean response = false;

        while (!hasAnswered()) {

            if (Main.chosenElements == 2) {

                if (this.nodes[this.firstPosition].getColor() == 1
                        && this.nodes[this.secondPosition].getColor() == 1) {

                    Main.events.setText(Constants.EVENT + "\n\n"
                            + "CORRETO!");
                    response = true;

                } else {

                    Main.events.setText(Constants.EVENT + "\n\n"
                            + "ERRADO! Tente novamente.");
                    response = false;
                }

                answered = true;

            }



        }
        
        return response;

    }

    public boolean hasAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
