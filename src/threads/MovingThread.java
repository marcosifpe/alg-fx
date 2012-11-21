/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import execution.Main;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import model.NodeElement;
import model.Question;
import model.Score;

/**
 *
 * @author rafael
 */
public class MovingThread extends Thread {

    NodeElement nodes[];
    Group root;
    private Score score;
    private HBox horizontalBox;
    private NodeElement node1;
    private NodeElement node2;
    private final Interpolator interpolator = Interpolator.LINEAR;
    private int operation;
    public final static int BUBBLE_SORT = 1, SELECTION_SORT = 2, INSERTION_SORT = 3, SHELL_SORT = 4,
            IN_PLACE_QUICK_SORT = 5;

    public MovingThread(NodeElement node1, NodeElement node2, NodeElement[] nodes, int operation) {
        this.node1 = node1;
        this.node2 = node2;
        this.nodes = nodes;
        this.operation = operation;
    }

    public MovingThread(NodeElement node1, NodeElement node2, NodeElement[] nodes, int operation, Group root) {
        this.node1 = node1;
        this.node2 = node2;
        this.nodes = nodes;
        this.operation = operation;
        this.root = root;

    }

    public MovingThread(NodeElement node1, NodeElement node2, NodeElement[] nodes, int operation,
            Group root, Score score) {
        this.node1 = node1;
        this.node2 = node2;
        this.nodes = nodes;
        this.operation = operation;
        this.root = root;
        this.score = score;
    }

    public MovingThread(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        try {



            if (this.operation == BUBBLE_SORT) {
                
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.setPoints(0);
                BubbleSort();
                clearNodes(nodes);
                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

            } else if (this.operation == SELECTION_SORT) {
                SelectionSort();
            } else if (this.operation == INSERTION_SORT) {
                InsertionSort();
            } else if (this.operation == SHELL_SORT) {
                ShellSort();
            } else if (this.operation == IN_PLACE_QUICK_SORT) {
                InPlaceQuickSort(0, nodes.length);
            }

        } finally {
            Main.running = false;
        }

    }

    public NodeElement[] getNodes() {
        return nodes;
    }

    public void setNodes(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    public void InsertionSort() {

        int i, j;

        for (i = 1; i < nodes.length; i++) {

            j = i;

            while ((nodes[j].getElementAsInt() < nodes[j - 1].getElementAsInt())) {

                testMoving(nodes[j - 1], nodes[j], j - 1, j, 0);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

//                aux = nodes[j].getElementAsInt();
//                nodes[j].setElementAsInt(nodes[j - 1].getElementAsInt());
//                nodes[j - 1].setElementAsInt(aux);
                j--;
                if (j == 0) {
                    break;
                }

            }
        }

    }

    public void SelectionSort() {

        int index_min, aux;

        for (int i = 0; i < nodes.length; i++) {

            index_min = i;

            for (int j = i + 1; j < nodes.length; j++) {
                if (nodes[j].getElementAsInt() < nodes[index_min].getElementAsInt()) {
                    index_min = j;
                }
            }

            if (index_min != i) {

                testMoving(nodes[i], nodes[index_min], i, index_min, 0);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

//                aux = nodes[index_min].getElementAsInt();
//                nodes[index_min].setElementAsInt(nodes[i].getElementAsInt());
//                nodes[i].setElementAsInt(aux);

            }

        }

    }

    public void clearNodes(NodeElement nodes[]) {

        for (NodeElement nodeElement : nodes) {
            nodeElement.getCircle().setFill(Color.rgb(156, 216, 255));
            nodeElement.setColor(0);
        }

    }

    public void BubbleSort() {

        boolean swapped = true;
        int j = 0;

        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < nodes.length - j; i++) {
                if (nodes[i].getElementAsInt() > nodes[i + 1].getElementAsInt()) {

                    Main.canChoose = true;
                    Main.chosenElements = 0;
                    clearNodes(nodes);

                    Question question = new Question(0, i, i + 1, nodes);

                    if (question.ask("Escolha os elementos à serem trocados.")) {
                        this.score.addRightAnswer();
                    } else {
                        this.score.addWrongAnswer();
                    }
                    this.score.addTotal();
                    
                    Main.canChoose = false;

                    testMoving(nodes[i], nodes[i + 1], i, i + 1, 0);

                    nodes[i].setColor(0);
                    nodes[i + 1].setColor(0);


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovingThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    tmp = nodes[i].getElementAsInt();
//                    nodes[i].setElementAsInt(nodes[i + 1].getElementAsInt());
//                    nodes[i + 1].setElementAsInt(tmp);
                    swapped = true;
                }
            }
        }

    }

    public void ShellSort() {

        int n = nodes.length;
        int h = n / 2;
        int j;
        NodeElement c;

        while (h > 0) {
            for (int i = h; i < n; i++) {

                c = nodes[i];
                j = i;

                while (j >= h && nodes[j - h].getElementAsInt() > c.getElementAsInt()) {

                    testMoving(nodes[j - h], nodes[j], j - h, j, 0);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MovingThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    j = j - h;

                }

                nodes[j] = c;

            }

            h = h / 2;

        }
    }

    public void InPlaceQuickSort(int beginning, int end) {

        if (beginning >= end) {
            return;
        }

        int pivot = nodes[end].getElementAsInt();
        int l = beginning;
        int r = end - 1;

        while (l <= r) {


            while (l <= r && nodes[l].getElementAsInt() <= pivot) {
                l++;
            }

            while (r >= l && nodes[r].getElementAsInt() >= pivot) {
                r--;
            }

            if (l < r) {
                testMoving(nodes[l], nodes[r], l, r, 0);
            }

            testMoving(nodes[l], nodes[end], l, end, 0);

            InPlaceQuickSort(beginning, l - 1);
            InPlaceQuickSort(l + 1, end);

        }


    }

    public void testMoving(NodeElement node1, NodeElement node2, int pos1, int pos2, int before) {

        double node1X, node1Y, node2X, node2Y, node1Xtemp, node2Xtemp;
        NodeElement nodeChange1, nodeChange2;

        nodeChange1 = node1;
        nodeChange2 = node2;

        node1Y = nodeChange1.getStackPane().getTranslateY();
        node1X = (pos1 * 80);
        node1Xtemp = node1X;

        node2Y = nodeChange2.getStackPane().getTranslateY();
        node2X = (pos2 * 80);
        node2Xtemp = node2X;

        int i = 0;

        while (i != 80) {

            nodeChange1.getStackPane().setTranslateY(nodeChange1.getStackPane().getTranslateY() + 1);
            nodeChange2.getStackPane().setTranslateY(nodeChange2.getStackPane().getTranslateY() - 1);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;

        }

        i = 0;

        while (i != (node2X - node1X)) {

            nodeChange1.getStackPane().setTranslateX(nodeChange1.getStackPane().getTranslateX() + 1.0);
            nodeChange2.getStackPane().setTranslateX(nodeChange2.getStackPane().getTranslateX() - 1.0);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;
        }

        i = 0;

        while (i != 80) {

            nodeChange1.getStackPane().setTranslateY(nodeChange1.getStackPane().getTranslateY() - 1);
            nodeChange2.getStackPane().setTranslateY(nodeChange2.getStackPane().getTranslateY() + 1);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(MovingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;

        }

        nodes[pos1] = nodeChange2;
        nodes[pos2] = nodeChange1;

    }
}
