/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import execution.Constants;
import execution.Main;
import java.awt.Toolkit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.NodeElement;
import model.Score;
import model.VectorElement;

/**
 *
 * @author rafael
 */
public class VectorThread extends Thread {

    private final Score score;
    private Main main;
    private int operation;
    private int position;
    private List<VectorElement> vector;
    private List<NodeElement> elements;
    private VectorElement vectorElement;
    private NodeElement nodeElement;
    private int number;
    private int actualCapacity;
    private Group animation;
    private boolean answered = false;
    public final int VECTOR_FINAL_CAPACITY = 8;
    public static final int INSERTION = 1;
    public static final int REMOVAL = 2;
    public static final int TOP = 3;
    public final int Y_POSITION = 150;

    public VectorThread(Group animation, List<VectorElement> vector, Score score,
            Main main, int operation, int number, NodeElement nodeElement,
            int position, int actualCapacity, List<NodeElement> vectorElements) {
        this.animation = animation;
        this.vector = vector;
        this.score = score;
        this.main = main;
        this.operation = operation;
        this.number = number;
        this.nodeElement = nodeElement;
        this.position = position;
        this.elements = vectorElements;
        this.actualCapacity = actualCapacity;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {

        this.score.disableVectorPane();

        try {

            if (operation == INSERTION) {

                insertElement(number, position);

            }


        } finally {

            this.score.enableVectorPane();
            Main.running = false;

        }

    }
    
    private Stage dialogStage;

    public void insertElement(int number, int position) {


        if (position < 0 || position > Main.vectorCapacity - 1 || position > elements.size()) {
            //ERRO FORA DO INDICE;
            return;

        } else {

            if (elements.size() == Main.vectorCapacity) {

                if (Main.vectorCapacity == 8) {

                    JOptionPane.showMessageDialog(null, "Chegou ao limite de "
                            + "capacidade da aplicação.");
                    return;

                } else {

                    for (int i = 0; i < Main.vectorCapacity * 2; i++) {
                        vector.get(i).getStackPane().setVisible(true);
                    }

                    Main.vectorCapacity = Main.vectorCapacity * 2;

                }

            }

            nodeElement.getStackPane().setVisible(true);
            int cycle = 0;

            do {
                nodeElement.getCircle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                nodeElement.getCircle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);
            
            
            
            

            for (int i = Main.vectorCapacity; i > position; i--) {
                
                
                if ((i - 1) >= 0 && vector.get(i - 1).getNode() != null) {
                    moveRight(vector.get(i - 1).getNode().getStackPane(), 90);
                    vector.get(i).insertNodeElement(vector.get(i - 1).getNode());
                }
                

            }

            double x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 300 + (position * 90);
            double y = Y_POSITION + 110;

            moveToLocation(nodeElement.getStackPane(),
                    x - nodeElement.getX() + 10,
                    y - nodeElement.getY() + 10);


            vector.get(position).insertNodeElement(nodeElement);
            elements.add(nodeElement);
            Main.variables.setText(Constants.VARIABLES
                    + "capacidade atual = " + Main.vectorCapacity
                    + "    tamanho atual = " + elements.size()
                    + "    tamanho máximo = " + vector.size());
            
            System.out.println("EL: ");
            for (NodeElement element1 : elements) {
                System.out.print(element1.getElementAsInt() + " ");
            }
            System.out.println("");
            System.out.println("");

        }

    }

    public void moveToLocation(StackPane pane, double x, double y) {

        double i = 0;

        while (i < x) {

            pane.setTranslateX(pane.getTranslateX() + 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }

        i = 0;

        while (i < y) {


            pane.setTranslateY(pane.getTranslateY() + 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;

        }

    }

    public void moveDownwards(StackPane pane, int quantity) {

        int i = 0;

        while (i != quantity) {

            pane.setTranslateY(pane.getTranslateY() + 1);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;

        }

    }

    public void moveRight(StackPane pane, int quantity) {

        int i = 0;

        while (i != quantity) {

            pane.setTranslateX(pane.getTranslateX() + 1);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;

        }

    }
    
    public void moveLeft(StackPane pane, int quantity) {

        int i = quantity;

        while (i != 0) {

            pane.setTranslateX(pane.getTranslateX() - 1);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i--;

        }

    }
    
}
