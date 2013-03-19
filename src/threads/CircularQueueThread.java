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
import model.Score;

/**
 *
 * @author rafael
 */
public class CircularQueueThread extends Thread{
    
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

//                Main.tf.setText(Constants.QUEUE_ENQUEUE);
//                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
//                        + Constants.UNAVAILABLE_EVENT);
//                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                        + "tamanho = " + queue.size());

                enqueue(number);
            } else if (operation == DEQUEUE) {
//                Main.tf.setText(Constants.QUEUE_DEQUEUE);
//                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
//                        + Constants.UNAVAILABLE_EVENT);
//                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                        + "tamanho = " + queue.size());

                dequeue();
            } else if (operation == FRONT) {
//                Main.tf.setText(Constants.QUEUE_FRONT);
//                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
//                        + Constants.UNAVAILABLE_EVENT);
//                Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                        + "tamanho = " + queue.size());

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

//        Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                + "tamanho = " + queue.size());
//        this.score.selectText("  se (tamanho == capacidade) {\n");

        if (queue.size() == QUEUE_CAPACITY || Math.abs(main.head - main.tail) >= QUEUE_CAPACITY) {
//            this.score.selectText("    Erro: Não há capacidade para mais elementos!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;

        } else {

//            this.score.selectText("  } senao {\n");

            x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 140 + (queue.size() * 70);
            y = central;

//            this.score.selectText("    fila[tamanho] = numero;\n");
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

//            moveToLocation(queueElement.getStackPane(),
//                    x - queueElement.getX(), y - queueElement.getY());
            moveToLocation(queueElement.getStackPane(), 
                    main.getCircularContainer()[main.tail].getX() - queueElement.getX() + 10.0, 
                    main.getCircularContainer()[main.tail].getY() - queueElement.getY() + 10.0);
            main.getCircularContainer()[main.tail].setNode(queueElement);
            
            main.getCircularQueue().add(queueElement);
            
            main.tail++;
            changeTailPosition();
            
//            this.score.selectText("    tamanho++;\n");
//            this.main.getCircularQueue().add(queueElement);
//            Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                + "tamanho = " + queue.size());
//            this.score.selectText("");

        }
    }

    public void dequeue() {

//        Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                + "tamanho = " + queue.size());
//        
//        this.score.selectText("  se(tamanho == 0) {\n");
        
        if (queue.isEmpty() || main.head == main.tail) {
            
//            this.score.selectText("    Erro: Não há elementos à serem retirados!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;
            
        } else {
            
//            this.score.selectText("  } senao  {\n");
//
//            this.score.selectText("    fila[0] = null;\n");
//            NodeElement element = queue.get(0);
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
            
            main.head++;
            changeHeadPosition();
            
//            this.score.selectText("    tamanho--;\n");
//            queue.remove(0);
//            Main.variables.setText(Constants.VARIABLES + "capacidade = " + QUEUE_CAPACITY + "    "
//                + "tamanho = " + queue.size());
//            this.score.selectText("");
            
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
            
            NodeElement s = queue.get(0);
            
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
    
}
