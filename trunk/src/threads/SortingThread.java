/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import execution.Constants;
import execution.Main;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import model.CountingBox;
import model.NodeElement;
import model.Question;
import model.Score;

/**
 *
 * @author rafael
 */
public class SortingThread extends Thread {

    NodeElement nodes[];
    CountingBox boxes[];
    NodeElement sorted[];
    Group root;
    private Group animation;
    private Main instance;
    private Score score;
    private NodeElement node1;
    private NodeElement node2;
    private final Interpolator interpolator = Interpolator.LINEAR;
    private int operation;
    public final static int BUBBLE_SORT = 1, SELECTION_SORT = 2, INSERTION_SORT = 3, SHELL_SORT = 4,
            IN_PLACE_QUICK_SORT = 5, COUNTING_SORT = 6;
    private final int SPACING_X = 450;
    private final int NODES_LENGHT = 5;
    private final int Y_POSITION = 150;

    public SortingThread(NodeElement node1, NodeElement node2, NodeElement[] nodes, int operation) {
        this.node1 = node1;
        this.node2 = node2;
        this.nodes = nodes;
        this.operation = operation;
    }

    public SortingThread(NodeElement node1, NodeElement node2, NodeElement[] nodes, int operation, Group root) {
        this.node1 = node1;
        this.node2 = node2;
        this.nodes = nodes;
        this.operation = operation;
        this.root = root;

    }

    public SortingThread(NodeElement node1, NodeElement node2, NodeElement[] nodes, int operation,
            Group root, Score score, Group animation) {
        this.node1 = node1;
        this.node2 = node2;
        this.nodes = nodes;
        this.operation = operation;
        this.root = root;
        this.score = score;
    }

    public SortingThread(int operation, Group root, Score score, NodeElement[] nodes) {
        this.operation = operation;
        this.root = root;
        this.score = score;
        this.nodes = nodes;
    }
    
    public SortingThread(int operation, Group root, Score score, NodeElement[] nodes, 
            CountingBox[] boxes, NodeElement[] sorted) {
        this.operation = operation;
        this.root = root;
        this.score = score;
        this.nodes = nodes;
        this.boxes = boxes;
        this.sorted = sorted;
    }

    public SortingThread(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {

        try {
            
            Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);

            if (this.operation == BUBBLE_SORT) {

                Main.tf.setText(Constants.BUBBLE_SORT);
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.fillSetProgressBar(0);
                this.score.setPoints(0);
                BubbleSort();
                clearNodes(nodes);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.SIMULATION_FINISHED);


                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

                this.score.removeElements();
                this.score.fillSetProgressBar(0);
                Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);

            } else if (this.operation == SELECTION_SORT) {

                Main.tf.setText(Constants.SELECTION_SORT);
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.fillSetProgressBar(0);
                this.score.setPoints(0);

                SelectionSort();

                clearNodes(nodes);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.SIMULATION_FINISHED);
                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

                this.score.removeElements();
                this.score.fillSetProgressBar(0);
                Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);

            } else if (this.operation == INSERTION_SORT) {

                Main.tf.setText(Constants.INSERTION_SORT);
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.fillSetProgressBar(0);
                this.score.setPoints(0);

                InsertionSort();

                clearNodes(nodes);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.SIMULATION_FINISHED);


                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

                this.score.removeElements();
                this.score.fillSetProgressBar(0);
                Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);

            } else if (this.operation == SHELL_SORT) {

                Main.tf.setText(Constants.SHELL_SORT);
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.fillSetProgressBar(0);
                this.score.setPoints(0);

                ShellSort();
                clearNodes(nodes);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.SIMULATION_FINISHED);


                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

                this.score.removeElements();
                this.score.fillSetProgressBar(0);
                Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);


            } else if (this.operation == IN_PLACE_QUICK_SORT) {

                Main.tf.setText(Constants.IN_PLACE_QUICK_SORT);
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.fillSetProgressBar(0);
                this.score.setPoints(0);

                InPlaceQuickSort(0, nodes.length - 1);

                clearNodes(nodes);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.SIMULATION_FINISHED);


                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

                this.score.removeElements();
                this.score.fillSetProgressBar(0);
                Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);

            } else if (this.operation == COUNTING_SORT) {

                Main.tf.setText(Constants.COUNTING_SORT);
                this.score.setAskedQuestions(0);
                this.score.setRightAnswers(0);
                this.score.setWrongAnswers(0);
                this.score.fillSetProgressBar(0);
                this.score.setPoints(0);

                System.out.println("Counting sort");
                CountingSort();

                clearNodes(nodes);
                clearBoxes(boxes);
                
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.SIMULATION_FINISHED);
                DecimalFormat df = new DecimalFormat("#.#");
                String points = df.format(this.score.getPoints()) + "%";
                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);

                this.score.removeElements();
                this.score.fillSetProgressBar(0);
                Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);

            }
            
//            Main.

        } finally {
            Main.tf.setText(Constants.NO_CODE);
                Main.events.setText(Constants.EVENT + "\n\n"
                        + Constants.NO_SIMULATION);
            Main.variables.setText(Constants.VARIABLES + Constants.NO_VARIABLES);
            Main.running = false;
            if (operation != COUNTING_SORT) {
                this.score.removeElements();
            } else {
                this.score.fillSetProgressBar(0);
                this.score.removeCountingElements();
            }
            return;

        }

    }

    public NodeElement[] getNodes() {
        return nodes;
    }

    public void setNodes(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    public void SelectionSort() {

        int index_min;
        Main.variables.setText(Constants.VARIABLES + "index_min = 0    "
                + "i = 0    j = 0    tamanho_vetor = " + nodes.length);
        for (int i = 0; i < nodes.length; i++) {
            Main.variables.setText(Constants.VARIABLES + "index_min = 0    "
                    + "i = 0    j = 0    tamanho_vetor = " + nodes.length);
            this.score.selectText("para ( int i = 0; i < tamanho_vetor; i++ ) {\n");

            this.score.selectText("  index_min = i\n");
            index_min = i;
            Main.variables.setText(Constants.VARIABLES + "index_min = " + index_min + "    "
                    + "i = " + i + "    j = 0    tamanho_vetor = " + nodes.length);

            for (int j = i + 1; j < nodes.length; j++) {
                Main.variables.setText(Constants.VARIABLES + "index_min = " + index_min + "    "
                        + "i = " + i + "    j = " + j + "    tamanho_vetor = " + nodes.length);
                this.score.selectText("    para ( int j = i + 1; j < tamanho_vetor; j++ ) {\n");

                this.score.selectText("      se ( v [ j ] < v [ index_min ] ) {\n");
                if (nodes[j].getElementAsInt() < nodes[index_min].getElementAsInt()) {
                    Main.variables.setText(Constants.VARIABLES + "index_min = " + index_min + "    "
                            + "i = " + i + "    j = " + j + "    tamanho_vetor = " + nodes.length);
                    this.score.selectText("      index_min = j;\n");
                    index_min = j;
                    Main.variables.setText(Constants.VARIABLES + "index_min = " + index_min + "    "
                            + "i = " + i + "    j = " + j + "    tamanho_vetor = " + nodes.length);
                }
            }

            this.score.selectText("    se ( index_min != i ) {\n");
            if (index_min != i) {
                Main.variables.setText(Constants.VARIABLES + "index_min = " + index_min + "    "
                        + "i = " + i + "    j = 0    tamanho_vetor = " + nodes.length);

                Main.canChoose = true;
                Main.chosenElements = 0;
                clearNodes(nodes);

                Question question = new Question(0, i, index_min, nodes);

                if (question.ask(Question.ELEMENT_CHANGE)) {
                    Constants.playQuestionSound(0);
                    this.score.addRightAnswer();
                } else {
                    Constants.playQuestionSound(1);
                    this.score.addWrongAnswer();
                }
                this.score.addTotal();
                this.score.fillSetProgressBar(this.score.getPoints());

                Main.canChoose = false;

                this.score.selectText("      troca(v [ i ], v [ index_min ]);\n");
                swapElements(nodes[i], nodes[index_min], i, index_min, 0);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    public void InsertionSort() {

        int i, j;
        Main.variables.setText(Constants.VARIABLES + "tamanho_vetor = " + nodes.length + "    "
                + "i = 0    j = 0");

        for (i = 1; i < nodes.length; i++) {
            Main.variables.setText(Constants.VARIABLES + "tamanho_vetor = " + nodes.length + "    "
                    + "i = " + i + "    j = 0");
            this.score.selectText("para(i = 1; i < tamanho_vetor; i++){\n\n");

            this.score.selectText("   j = i;\n\n");
            j = i;
            Main.variables.setText(Constants.VARIABLES + "tamanho_vetor = " + nodes.length + "    "
                    + "i = " + i + "    j = " + j);

            while ((nodes[j].getElementAsInt() < nodes[j - 1].getElementAsInt())) {
                Main.variables.setText(Constants.VARIABLES + "tamanho_vetor = " + nodes.length + "    "
                        + "i = " + i + "    j = " + j);
                this.score.selectText("      enquanto(v[ j ] < v[ j - 1 ]) {\n\n");

                Main.canChoose = true;
                Main.chosenElements = 0;
                clearNodes(nodes);

                Question question = new Question(0, j - 1, j, nodes);

                if (question.ask(Question.ELEMENT_CHANGE)) {
                    Constants.playQuestionSound(0);
                    this.score.addRightAnswer();
                } else {
                    Constants.playQuestionSound(1);
                    this.score.addWrongAnswer();
                }
                this.score.addTotal();
                this.score.fillSetProgressBar(this.score.getPoints());

                Main.canChoose = false;

                this.score.selectText("         troca(v [ j - 1], v [ j ]);\n");
                swapElements(nodes[j - 1], nodes[j], j - 1, j, 0);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.score.selectText("         j--.\n\n");
                j--;
                Main.variables.setText(Constants.VARIABLES + "tamanho_vetor = " + nodes.length + "    "
                        + "i = " + i + "    j = " + j);

                this.score.selectText("         se(j == 0) {\n");
                if (j == 0) {
                    Main.variables.setText(Constants.VARIABLES + "tamanho_vetor = " + nodes.length + "    "
                            + "i = " + i + "    j = " + j);
                    this.score.selectText("           quebrar o laço;\n");
                    break;
                }

            }
        }

    }

    public void clearNodes(NodeElement nodes[]) {

        for (NodeElement nodeElement : nodes) {
            nodeElement.getCircle().setFill(Color.rgb(156, 216, 255));
            nodeElement.setColor(0);
        }

    }
    
    public void clearBoxes(CountingBox boxes[]) {
        
        for (CountingBox countingBox : boxes) {
            countingBox.getCountingRectangle().setFill(Color.rgb(165, 42, 42));
            countingBox.getPositionRectangle().setFill(Color.rgb(165, 180, 82));
            countingBox.setColor(0);
        }
        
    }

    public static void clearNodeColor(NodeElement nodes[]) {

        for (NodeElement nodeElement : nodes) {
            nodeElement.getCircle().setFill(Color.rgb(156, 216, 255));
            nodeElement.setColor(0);
        }

    }

    public void BubbleSort() {

        Main.variables.setText(Constants.VARIABLES + "troca = null    "
                + "j = 0    i = 0");
        this.score.selectText("troca = true;\n");
        boolean swapped = true;
        Main.variables.setText(Constants.VARIABLES + "troca = " + swapped + "    "
                + "j = 0    i = 0");
        this.score.selectText("j = 0;\n\n");
        int j = 0;
        Main.variables.setText(Constants.VARIABLES + "troca = " + swapped + "    "
                + "j = " + j + "    i = 0");

        this.score.selectText("enquanto(troca == true) {\n");
        while (swapped) {
            this.score.selectText("  troca = false;\n");
            swapped = false;
            Main.variables.setText(Constants.VARIABLES + "troca = " + swapped + "    "
                    + "j = " + j + "    i = 0");
            this.score.selectText("  j += 1;\n");
            j++;
            Main.variables.setText(Constants.VARIABLES + "troca = " + swapped + "    "
                    + "j = " + j + "    i = 0");

            for (int i = 0; i < nodes.length - j; i++) {
                Main.variables.setText(Constants.VARIABLES + "troca = " + swapped + "    "
                        + "j = " + j + "    i = " + i);
                this.score.selectText("    para(i = 0; i < tamanho_vetor; i++) {\n");

                this.score.selectText("      se(vetor[ i ] > vetor [ i + 1 ] {\n");
                if (nodes[i].getElementAsInt() > nodes[i + 1].getElementAsInt()) {
                    Main.variables.setText(Constants.VARIABLES + "troca = " + swapped + "    "
                            + "j = " + j + "    i = " + i);

                    Main.canChoose = true;
                    Main.chosenElements = 0;
                    clearNodes(nodes);

                    Question question = new Question(0, i, i + 1, nodes);

                    if (question.ask(Question.ELEMENT_CHANGE)) {
                        Constants.playQuestionSound(0);
                        this.score.addRightAnswer();
                    } else {
                        Constants.playQuestionSound(1);
                        this.score.addWrongAnswer();
                    }
                    this.score.addTotal();
                    this.score.fillSetProgressBar(this.score.getPoints());

                    Main.canChoose = false;

                    this.score.selectText("        troca(vetor[ i ], vetor[ i + 1]);\n");
                    swapElements(nodes[i], nodes[i + 1], i, i + 1, 0);

                    nodes[i].setColor(0);
                    nodes[i + 1].setColor(0);


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    swapped = true;
                }
            }
        }

    }

    public void ShellSort() {

        Main.variables.setText(Constants.VARIABLES + "n = 0    "
                + "h = 0    i = 0    j = 0    c = null");
        this.score.selectText("n = tamanho_vetor;\n");
        int n = nodes.length;
        Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                + "h = 0    i = 0    j = 0    c = null");
        this.score.selectText("h = n / 2;\n\n");
        int h = n / 2;
        Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                + "h = " + h + "    i = 0    j = 0    c = null");
        int j;
        NodeElement c;

        while (h > 0) {
            Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                    + "h = " + h + "    i = 0    j = 0    c = null");
            this.score.selectText("enquanto( h > 0) {\n\n");

            for (int i = h; i < n; i++) {

                Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                        + "h = " + h + "    i = " + i + "    j = 0    c = null");
                this.score.selectText("   para (i = h; i < n; i++) {\n");

                this.score.selectText("     c = vetor[ i ];\n");
                c = nodes[i];
                Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                        + "h = " + h + "    i = " + i + "    j = 0    c = " + c.getElementAsInt());
                this.score.selectText("     j = i;\n\n");
                j = i;
                Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                        + "h = " + h + "    i = " + i + "    j = " + j + "    c = " + c.getElementAsInt());

                while (j >= h && nodes[j - h].getElementAsInt() > c.getElementAsInt()) {
                    Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                            + "h = " + h + "    i = " + i + "    j = " + j + "    c = " + c.getElementAsInt());
                    this.score.selectText("       enquanto ( j >= h  E vetor [ j - h ] > c ) {\n");

                    Main.canChoose = true;
                    Main.chosenElements = 0;
                    clearNodes(nodes);

                    Question question = new Question(0, j - h, j, nodes);

                    if (question.ask(Question.ELEMENT_CHANGE)) {
                        Constants.playQuestionSound(0);
                        this.score.addRightAnswer();
                    } else {
                        Constants.playQuestionSound(1);
                        this.score.addWrongAnswer();
                    }
                    this.score.addTotal();
                    this.score.fillSetProgressBar(this.score.getPoints());

                    Main.canChoose = false;

                    this.score.selectText("         troca( vetor[ j - h ], vetor [ j ] );\n");
                    swapElements(nodes[j - h], nodes[j], j - h, j, 0);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    this.score.selectText("         j = j - h;\n");
                    j = j - h;
                    Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                            + "h = " + h + "    i = " + i + "    j = " + j + "    c = " + c.getElementAsInt());

                }

                this.score.selectText("       vetor[ j ] = c;\n");
                nodes[j] = c;
                Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                        + "h = " + h + "    i = " + i + "    j = " + j + "    c = " + c.getElementAsInt());

            }

            this.score.selectText("   h = h / 2;\n");
            h = h / 2;
            Main.variables.setText(Constants.VARIABLES + "n = " + n + "    "
                    + "h = " + h + "    i = 0    j = 0    c = null");

        }
    }

    public void InPlaceQuickSort(int beginning, int end) {

        Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = 0    l = 0    r = 0");
        this.score.selectText("se ( comeco >= fim ) {\n");
        if (beginning >= end) {
            this.score.selectText("  retorne;\n");
            return;
        }

        this.score.selectText("pivo = v [ fim ];\n");
        int pivot = nodes[end].getElementAsInt();
        Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = 0    r = 0");
        
        
        this.score.selectText("l = comeco;\n");
        int l = beginning;
        Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = 0");
        
        this.score.selectText("r = fim - 1;\n\n");
        int r = end - 1;
        Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);

        while (l <= r) {
            Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);
            this.score.selectText("enquanto( l <= r) {\n");

            this.score.selectText("  enquanto ( l <= r e v [ l ] <= pivo ) {\n");
            while (l <= r && nodes[l].getElementAsInt() <= pivot) {
                this.score.selectText("     l++;\n");
                l++;
                Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);
            }

            this.score.selectText("  enquanto ( r >= l e v [ r ] >= pivo ) {\n");
            while (r >= l && nodes[r].getElementAsInt() >= pivot) {
                this.score.selectText("     r--;\n");
                r--;
                Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);
            }

            this.score.selectText("  se ( l < r ) {\n");
            if (l < r) {
                Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);

                Main.canChoose = true;
                Main.chosenElements = 0;
                clearNodes(nodes);

                Question question = new Question(0, l, r, nodes);

                if (question.ask(Question.ELEMENT_CHANGE)) {
                    Constants.playQuestionSound(0);
                    this.score.addRightAnswer();
                } else {
                    Constants.playQuestionSound(1);
                    this.score.addWrongAnswer();
                }
                this.score.addTotal();
                this.score.fillSetProgressBar(this.score.getPoints());

                Main.canChoose = false;

                this.score.selectText("    troca( v [ l ], v [ r ] );\n");
                swapElements(nodes[l], nodes[r], l, r, 0);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);
            }

            Main.canChoose = true;
            Main.chosenElements = 0;
            clearNodes(nodes);

            Question question = new Question(0, l, end, nodes);

            if (question.ask(Question.ELEMENT_CHANGE)) {
                Constants.playQuestionSound(0);
                this.score.addRightAnswer();
            } else {
                Constants.playQuestionSound(1);
                this.score.addWrongAnswer();
            }
            this.score.addTotal();
            this.score.fillSetProgressBar(this.score.getPoints());

            Main.canChoose = false;

            this.score.selectText("  troca( v [ l ], v [ fim ] );\n");
            swapElements(nodes[l], nodes[end], l, end, 0);

            try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            Main.variables.setText(Constants.VARIABLES + "comeco = " + beginning + "    "
                    + "fim = " + end + "\n" + "pivo = " + pivot + "    l = " + l + "    r = " + r);
            
            this.score.selectText("  inPlaceQuickSort(v, comeco, l - 1);\n");
            InPlaceQuickSort(beginning, l - 1);
            this.score.selectText("  inPlaceQuickSort(v, l + 1, fim);");
            InPlaceQuickSort(l + 1, end);

        }


    }

    public void CountingSort() {
        
        
        
        int min, max;
        List<Integer> elementList = new ArrayList<>();
        
        for (NodeElement e : nodes) {
            elementList.add(e.getElementAsInt());
        }
        
        min = Collections.min(elementList);
        max = Collections.max(elementList);
        
        for (int i = 0; i < nodes.length; i++) {
            
            Main.variables.setText(Constants.VARIABLES + "min = " + min + "    "
                    + "max= " + max + "\n" + "atual = " + nodes[i].getElementAsInt()
                    + "     i = " + i);
            this.score.selectText("para (int i = 0; i < vetor.tamanho; i++) {\n\n");
        
            this.score.selectText(" vetorContagem[ vetorContagem[ i ] - minimo]++;\n\n");
            
            Main.canChooseBox = true;
            Main.chosenBoxes = 0;
            clearNodes(nodes);
            clearBoxes(boxes);

            Question question = new Question(0, nodes[i].getElementAsInt() - min, 
                    nodes, boxes);

            if (question.askBoxes(Question.BOX_SELECTION)) {
                Constants.playQuestionSound(0);
                this.score.addRightAnswer();
            } else {
                Constants.playQuestionSound(1);
                this.score.addWrongAnswer();
            }
            this.score.addTotal();
            this.score.fillSetProgressBar(this.score.getPoints());

            Main.canChooseBox = false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            boxes[nodes[i].getElementAsInt() - min].setCountingValue
                    (Integer.parseInt(boxes[nodes[i].getElementAsInt() - min].getElementC()) + 1);
            
            clearBoxes(boxes);
            
        }
        
        
        this.score.selectText("z = 0;\n\n");
        int z = 0;
        Main.variables.setText(Constants.VARIABLES + "min = " + min + "    "
                    + "max= " + max + "\n" + "z = " + z);
        
        for (int i = min; i <= max; i++) {
            
            this.score.selectText("para (int i = minimo; i <= maximo; i++) {\n\n");
            Main.variables.setText(Constants.VARIABLES + "min = " + min + "    "
                    + "max = " + max + "\n" + "z = " + z + "     i = " + i);
            
            while (Integer.parseInt(boxes[i - min].getElementC()) > 0) {
                
                this.score.selectText("  enquanto (vetorContagem[i - minimo] > 0) {\n\n");
                
                Main.canChooseBox = true;
                Main.chosenBoxes = 0;
                clearNodes(nodes);
                clearBoxes(boxes);

                Question question = new Question(0, i - min, nodes, boxes);

                if (question.askBoxes(Question.ELEMENT_SELECTION)) {
                    Constants.playQuestionSound(0);
                    this.score.addRightAnswer();
                } else {
                    Constants.playQuestionSound(1);
                    this.score.addWrongAnswer();
                }
                this.score.addTotal();
                this.score.fillSetProgressBar(this.score.getPoints());

                Main.canChooseBox = false;
                
                
//                nodes[z].setElementAsInt(i);
                
                sorted[z].setElementAsInt(i);
                
                this.score.selectText("    vetor[ z ] = i;\n");
                
                sorted[z].getStackPane().setVisible(true);
                sorted[z].setAnimationElement(i);
//                nodes[z].setAnimationElement(i);

                clearBoxes(boxes);
            
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.score.selectText("    z++;\n");
                z++;
                Main.variables.setText(Constants.VARIABLES + "min = " + min + "    "
                    + "max= " + max + "\n" + "z = " + z);
                
                this.score.selectText("    vetorContagem[i - min]--;\n\n");
                boxes[i - min].setCountingValue(Integer.parseInt(boxes[i - min].getElementC()) - 1);
            }
            
        }
    }
   
    public void swapElements(NodeElement node1, NodeElement node2, int pos1, int pos2, int before) {

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
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;

        }

        nodes[pos1] = nodeChange2;
        nodes[pos2] = nodeChange1;
        clearNodes(nodes);

    }
}
