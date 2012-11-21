/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import execution.Main;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class Question {
    
    private int chosenElements;
    private int firstPosition;
    private int secondPosition;
    private NodeElement nodes[];
    private boolean answered = false;

    public Question(int chosenElements, int firstPosition, int secondPosition, NodeElement[] nodes) {
        this.chosenElements = chosenElements;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.nodes = nodes;
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
    
    public void ask(String question) {
        JOptionPane.showMessageDialog(null, question);
        
        while (!isAnswered()) {
                        
                        
                        if (Main.chosenElements == 2) {
                            
                            if (this.nodes[this.firstPosition].getColor() == 1 && 
                                    this.nodes[this.secondPosition].getColor() == 1) {
                                JOptionPane.showMessageDialog(null, "Correto!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Errado!");
                            }
                            
                            break;
                        }
                        
                    }
        
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    
}
