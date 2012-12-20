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
import model.QueueElement;
import model.Score;
import model.StackElement;

/**
 *
 * @author rafael
 */
public class QueueThread extends Thread {

    private Score score;
    private Main main;
    private int operation;
    private List<QueueElement> queue;
    private QueueElement queueElement;
    private int number;
    private Group animation;
    private boolean answered = false;
    public final int QUEUE_CAPACITY = 5;
    public static final int ENQUEUE = 1;
    public static final int DEQUEUE = 2;
    public static final int FRONT = 3;
    public static final int TOP = 3;
    public final int Y_POSITION = 150;

    public QueueThread(Group animation, List<QueueElement> list, Score score,
            Main main, int operation, int number, QueueElement queueElement) {
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

        this.score.disableQueuePane();
        try {

            if (operation == ENQUEUE) {

                Main.tf.setText(Constants.QUEUE_ENQUEUE);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                        + "tamanho = " + queue.size());

                enqueue(number);
            } else if (operation == DEQUEUE) {
                Main.tf.setText(Constants.QUEUE_DEQUEUE);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                        + "tamanho = " + queue.size());

                dequeue();
            } else if (operation == FRONT) {
                Main.tf.setText(Constants.QUEUE_FRONT);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                        + "tamanho = " + queue.size());

                front();
            }

            Main.tf.setText(Constants.NO_CODE);
            Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                    + Constants.NO_SIMULATION);

        } finally {
            this.score.enableQueuePane();
            Main.running = false;
        }
    }

    public void enqueue(int number) {

        double central = Y_POSITION + 110;
        double x, y;

        Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                + "tamanho = " + queue.size());
        this.score.selectText("  se (tamanho == capacidade) {\n");

        if (queue.size() == QUEUE_CAPACITY) {
            this.score.selectText("    Erro: Não há capacidade para mais elementos!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;

        } else {

            this.score.selectText("  } senao {\n");

            x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 140 + (queue.size() * 70);
            y = central;

            this.score.selectText("    fila[tamanho] = numero;\n");
            queueElement.getStackPane().setVisible(true);
            int cycle = 0;

            do {
                queueElement.getRectangle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                queueElement.getRectangle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);

            moveToLocation(queueElement.getStackPane(),
                    x - queueElement.getX(), y - queueElement.getY());


            this.score.selectText("    tamanho++;\n");
            this.main.getQueue().add(queueElement);
            Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                + "tamanho = " + queue.size());
            this.score.selectText("");

        }
    }

    public void dequeue() {

        Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                + "tamanho = " + queue.size());
        
        this.score.selectText("  se(tamanho == 0) {\n");
        
        if (queue.isEmpty()) {
            
            this.score.selectText("    Erro: Não há elementos à serem retirados!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;
            
        } else {
            
            this.score.selectText("  } senao  {\n");

            this.score.selectText("    fila[0] = null;\n");
            QueueElement element = queue.get(0);
            int cycle = 0;

            do {
                element.getRectangle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                element.getRectangle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);

            moveUpwards(element.getStackPane(), 150);
            element.getStackPane().setVisible(false);
            
            
            for (QueueElement queueElement1 : queue) {
                moveLeft(queueElement1.getStackPane(), 70);
            }
            
            this.score.selectText("    tamanho--;\n");
            queue.remove(0);
            Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
                + "tamanho = " + queue.size());
            this.score.selectText("");
            
        }

    }
    
    public void front() {
        
        Main.tf.setText(Constants.QUEUE_FRONT);
        
        this.score.selectText("  se (tamanho == 0) {\n");
        
        if (queue.isEmpty()) {
            
            this.score.selectText("    Erro: Não há elementos na fila!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;
            
        } else {
            
            this.score.selectText("  } senao   {\n");
            
            this.score.selectText("  retorna fila[0];\n");
            
            QueueElement s = queue.get(0);
            
            int cycle = 0;
            do {
                s.getRectangle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                s.getRectangle().setFill(Color.rgb(156, 216, 255));
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
}
