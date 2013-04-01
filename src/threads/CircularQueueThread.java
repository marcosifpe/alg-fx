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
import model.NodeElement;
import model.Question;
import model.Score;

/**
 *
 * @author rafael
 */
public class CircularQueueThread extends Thread {

    private Score score;
    private Main main;
    private int operation;
    private List<NodeElement> queue;
    private NodeElement queueElement;
    private int number;
    private Group animation;
    private boolean answered = false;
    public final int QUEUE_CAPACITY = 7;
    public static final int ENQUEUE = 1;
    public static final int DEQUEUE = 2;
    public static final int FRONT = 3;
    public static final int TOP = 3;
    public final int Y_POSITION = 150;

    public CircularQueueThread(Group animation, List<NodeElement> list, Score score,
            Main main, int operation, int number, NodeElement queueElement) {
        this.animation = animation;
        this.queue = list;
        this.score = score;
        this.main = main;
        this.operation = operation;
        this.number = number;
        this.queueElement = queueElement;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {

        this.score.disableCircularQueuePane();
        try {

            if (operation == ENQUEUE) {

                Main.tf.setText(Constants.CIRCULAR_ENQUEUE);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                        + "tamanho = " + queue.size()
                        + "\nhead = " + Main.head + "     tail = " + Main.tail);

                enqueue(number);


            } else if (operation == DEQUEUE) {
                Main.tf.setText(Constants.CIRCULAR_DEQUEUE);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                        + "tamanho = " + queue.size()
                        + "\nhead = " + Main.head + "     tail = " + Main.tail);

                dequeue();

            } else if (operation == FRONT) {

                Main.tf.setText(Constants.CIRCULAR_FRONT);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                        + "tamanho = " + queue.size()
                        + "\nhead = " + Main.head + "     tail = " + Main.tail);
                front();
            }

            Main.tf.setText(Constants.NO_CODE);
            Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                    + Constants.NO_SIMULATION);

        } finally {
            this.score.enableCircularQueuePane();
            Main.running = false;
        }

    }

    public void changeHeadPosition() {

        double x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65;

        if (main.head > 7) {
            main.head = 0;
        }

        switch (main.head) {

            case 0:
                main.headElement.getStackPane().setTranslateX(x);
                main.headElement.getStackPane().setTranslateY(40.0);
                break;
            case 1:
                main.headElement.getStackPane().setLayoutX(x + 170.0);
                main.headElement.getStackPane().setLayoutY(120.0);
                break;
            case 2:
                main.headElement.getStackPane().setLayoutX(x + 210.0);
                main.headElement.getStackPane().setLayoutY(200.0);
                break;
            case 3:
                main.headElement.getStackPane().setLayoutX(x + 170.0);
                main.headElement.getStackPane().setLayoutY(280.0);
                break;
            case 4:
                main.headElement.getStackPane().setLayoutX(x);
                main.headElement.getStackPane().setLayoutY(380.0);
                break;
            case 5:
                main.headElement.getStackPane().setLayoutX(x - 130.0);
                main.headElement.getStackPane().setLayoutY(280.0);
                break;
            case 6:
                main.headElement.getStackPane().setLayoutX(x - 180.0);
                main.headElement.getStackPane().setLayoutY(200.0);
                break;
            case 7:
                main.headElement.getStackPane().setLayoutX(x - 130.0);
                main.headElement.getStackPane().setLayoutY(120.0);
                break;
        }


    }

    public void changeTailPosition() {

        double x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65;

        if (main.tail > 7) {
            main.tail = 0;
        }

        switch (main.tail) {

            case 0:
                main.tailElement.getStackPane().setLayoutX(x + 40.0);
                main.tailElement.getStackPane().setLayoutY(40.0);
                break;
            case 1:
                main.tailElement.getStackPane().setLayoutX(x + 170.0);
                main.tailElement.getStackPane().setLayoutY(160.0);
                break;
            case 2:
                main.tailElement.getStackPane().setLayoutX(x + 210.0);
                main.tailElement.getStackPane().setLayoutY(240.0);
                break;
            case 3:
                main.tailElement.getStackPane().setLayoutX(x + 170.0);
                main.tailElement.getStackPane().setLayoutY(320.0);
                break;
            case 4:
                main.tailElement.getStackPane().setLayoutX(x + 40.0);
                main.tailElement.getStackPane().setLayoutY(410.0);
                break;
            case 5:
                main.tailElement.getStackPane().setLayoutX(x - 130.0);
                main.tailElement.getStackPane().setLayoutY(320.0);
                break;
            case 6:
                main.tailElement.getStackPane().setLayoutX(x - 180.0);
                main.tailElement.getStackPane().setLayoutY(240.0);
                break;
            case 7:
                main.tailElement.getStackPane().setLayoutX(x - 130.0);
                main.tailElement.getStackPane().setLayoutY(160.0);
                break;
        }


    }

    public void enqueue(int number) {

        double central = Y_POSITION + 110;
        double x, y;

        Main.variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                + "tamanho = " + queue.size()
                + "\nhead = " + Main.head + "     tail = " + Main.tail);
        this.score.selectText("  se ( (capacidade - head + tail) % capacidade \n           == capacidade - 1) {\n");


        if (queue.size() == QUEUE_CAPACITY || Math.abs(main.head - main.tail) >= QUEUE_CAPACITY) {
            this.score.selectText("    Erro: A fila está cheia!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;

        } else {

            this.score.selectText("  } senao {\n");

            x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 140 + (queue.size() * 70);
            y = central;


            //======== QUESTION =========
            Main.canChooseHeadTail = true;
            Main.chosenHeadTails = 0;
            clearIndexes();
            Question question = new Question(0, "Tail", Main.headElement, Main.tailElement);

            if (question.askCircularTail(Question.HEAD_TAIL_ENQUEUE, 1)) {
                Constants.playQuestionSound(0);
                this.score.addRightAnswer();
            } else {
                Constants.playQuestionSound(1);
                this.score.addWrongAnswer();
            }

            this.score.addTotal();
            this.score.fillSetProgressBar(this.score.getPoints());

            Main.canChooseHeadTail = false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            clearIndexes();
            //====== END QUESTION =======

            queueElement.getStackPane().setVisible(true);

            int cycle = 0;

            do {
                queueElement.getCircle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                queueElement.getCircle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);


            moveToLocation(queueElement.getStackPane(),
                    main.getCircularContainer()[main.tail].getX() - queueElement.getX() + 10.0,
                    main.getCircularContainer()[main.tail].getY() - queueElement.getY() + 10.0);
            main.getCircularContainer()[main.tail].setNode(queueElement);


            this.score.selectText("    fila[ tail ] = elemento;\n");
            main.getCircularQueue().add(queueElement);

            this.score.selectText("    tail = (tail + 1) % capacidade;\n");
            main.tail++;
            changeTailPosition();

            Main.variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                    + "tamanho = " + queue.size()
                    + "\nhead = " + Main.head + "     tail = " + Main.tail);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void dequeue() {

        Main.variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                + "tamanho = " + queue.size()
                + "\nhead = " + Main.head + "     tail = " + Main.tail);

        this.score.selectText("  se((capacidade - head + tail) % capacidade == 0) {\n");

        if (queue.isEmpty() || main.head == main.tail) {

            this.score.selectText("    Erro: A fila está vazia!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;

        } else {

            this.score.selectText("  } senao {\n");

            //======== QUESTION =========
            Main.canChooseHeadTail = true;
            Main.chosenHeadTails = 0;
            clearIndexes();
            Question question = new Question(0, "Head", Main.headElement, Main.tailElement);

            if (question.askCircularHead(Question.HEAD_TAIL_DEQUEUE, 1)) {
                Constants.playQuestionSound(0);
                this.score.addRightAnswer();
            } else {
                Constants.playQuestionSound(1);
                this.score.addWrongAnswer();
            }

            this.score.addTotal();
            this.score.fillSetProgressBar(this.score.getPoints());

            Main.canChooseHeadTail = false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            clearIndexes();
            //====== END QUESTION =======

            this.score.selectText("    fila[ head ] = null;\n");
            NodeElement element = main.getCircularContainer()[main.head].getNode();
            int cycle = 0;

            do {
                element.getCircle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                element.getCircle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);

            moveUpwards(element.getStackPane(), 150);
            element.getStackPane().setVisible(false);

            main.getCircularContainer()[main.head].setNode(null);
            main.getCircularQueue().remove(element);

            this.score.selectText("    head = (head + 1) % capacidade;\n");
            main.head++;
            changeHeadPosition();
            
            Main.variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                + "tamanho = " + queue.size()
                + "\nhead = " + Main.head + "     tail = " + Main.tail);
            
            try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }

        }

    }

    public void front() {

        Main.tf.setText(Constants.CIRCULAR_FRONT);
        
        Main.variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                + "tamanho = " + queue.size()
                + "\nhead = " + Main.head + "     tail = " + Main.tail);

        this.score.selectText("  se((capacidade - head + tail) % capacidade == 0) {\n");

        if (queue.isEmpty()) {

            this.score.selectText("    Erro: A fila está vazia!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;

        } else {

            this.score.selectText("  } senao {\n");
            
            //======== QUESTION =========
            Main.canChooseHeadTail = true;
            Main.chosenHeadTails = 0;
            clearIndexes();
            Question question = new Question(0, "Head", Main.headElement, Main.tailElement);

            if (question.askCircularHead(Question.HEAD_TAIL_FRONT, 1)) {
                Constants.playQuestionSound(0);
                this.score.addRightAnswer();
            } else {
                Constants.playQuestionSound(1);
                this.score.addWrongAnswer();
            }

            this.score.addTotal();
            this.score.fillSetProgressBar(this.score.getPoints());

            Main.canChooseHeadTail = false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            clearIndexes();
            //====== END QUESTION =======
            
            
            this.score.selectText("    retorna fila[ head ];\n");
            
            NodeElement s = main.getCircularContainer()[main.head].getNode();

            int cycle = 0;
            do {
                s.getCircle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.getCircle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);

            this.score.selectText("");

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

    public void moveUpwards(StackPane pane, int quantity) {

        int i = quantity;

        while (i != 0) {

            pane.setTranslateY(pane.getTranslateY() - 1);

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i--;

        }

    }

    public void clearNodes(List<NodeElement> nodes) {

        for (NodeElement q : queue) {
            q.getCircle().setFill(Color.rgb(156, 216, 255));
            q.setColor(0);
        }

    }

    public void clearIndexes() {
        Main.headElement.getRectangle().setFill((Color.rgb(156, 216, 255)));
        Main.tailElement.getRectangle().setFill((Color.rgb(156, 216, 255)));
        Main.headElement.setColor(0);
        Main.tailElement.setColor(0);

    }
}
