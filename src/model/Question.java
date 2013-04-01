/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import execution.Constants;
import execution.Main;
import java.util.List;
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
    private int correctQueue;
    private int correctStack;
    private int firstPosition;
    private int secondPosition;
    private String correctIndex;
    private NodeElement nodes[];
    private StackElement head, tail;
    private CountingBox boxes[];
    private List<QueueElement> queues;
    private List<StackElement> stack;
    private boolean answered = false;
    public static final int ELEMENT_CHANGE = 1, BOX_SELECTION = 2, 
            ELEMENT_SELECTION = 3, FRONT_ELEMENT = 4, POST_ELEMENT = 5, 
            CORRECT_REMOVAL = 6, TOP_ELEMENT = 7, HEAD_TAIL_ENQUEUE = 8,
            HEAD_TAIL_DEQUEUE = 9, HEAD_TAIL_FRONT = 10;
    

    public Question(int chosenElements, int firstPosition, int secondPosition, NodeElement[] nodes) {
        this.chosenElements = chosenElements;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.nodes = nodes;
    }
    
    public Question(int chosenElements, int correctQueue, List<QueueElement> queue) {
        this.chosenElements = chosenElements;
        this.correctQueue = correctQueue;
        this.queues = queue;
    }
    
    public Question(int chosenElements, int correctStack, List<StackElement> stack, int aux) {
        this.chosenElements = chosenElements;
        this.correctStack = correctStack;
        this.stack = stack;
    }
    
    public Question(int chosenElements, String correctIndex, 
            StackElement head, StackElement tail) {
        this.chosenElements = chosenElements;
        this.correctIndex = correctIndex;
        this.head = head;
        this.tail = tail;
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
    
    public boolean askCircularHead(int operation, int index) {
        
        if (operation == HEAD_TAIL_ENQUEUE) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.HEAD_TAIL_ENQUEUE);
        } else if (operation == HEAD_TAIL_DEQUEUE) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.HEAD_TAIL_DEQUEUE);
        } else if (operation == HEAD_TAIL_FRONT) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.HEAD_TAIL_FRONT);
        }
        
        boolean response = false;

        while (!hasAnswered()) {

            if (Main.chosenHeadTails == 1) {

                if (this.head.getColor() == 1 && this.tail.getColor() == 0) {


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
    
    public boolean askCircularTail(int operation, int index) {
        
        if (operation == HEAD_TAIL_ENQUEUE) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.HEAD_TAIL_ENQUEUE);
        } else if (operation == HEAD_TAIL_DEQUEUE) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.HEAD_TAIL_DEQUEUE);
        } 
        
        boolean response = false;

        while (!hasAnswered()) {

            if (Main.chosenHeadTails == 1) {

                if (this.head.getColor() == 0 && this.tail.getColor() == 1) {


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
    
    public boolean askStack(int operation) {

        if (operation == TOP_ELEMENT) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.FRONT_ELEMENT);
        } else if (operation == POST_ELEMENT) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.POST_ELEMENT);
        } else if (operation == CORRECT_REMOVAL) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.CORRECT_REMOVAL);
        }
        
        boolean response = false;

        while (!hasAnswered()) {

            if (Main.chosenStacks == 1) {

                if (this.stack.get(correctStack).getColor() == 1) {

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
    
    public boolean askQueue(int operation) {

        if (operation == FRONT_ELEMENT) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.FRONT_ELEMENT);
        } else if (operation == POST_ELEMENT) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.POST_ELEMENT);
        } else if (operation == CORRECT_REMOVAL) {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.CORRECT_REMOVAL);
        }
        
        boolean response = false;

        while (!hasAnswered()) {

            if (Main.chosenQueues == 1) {

                if (this.queues.get(correctQueue).getColor() == 1) {

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
