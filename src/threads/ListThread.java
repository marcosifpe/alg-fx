/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import execution.Constants;
import execution.Main;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.ListElement;
import model.Score;
import model.StackElement;

/**
 *
 * @author rafael
 */
public class ListThread extends Thread {

    private final Score score;
    private Main main;
    private int operation;
    private List<ListElement> list;
    private ListElement listElement;
    private int position;
    private int number;
    private boolean hideLeft = false, hideRight = false;
    private Group animation;
    private boolean answered = false;
    public final int LIST_CAPACITY = 5;
    public static final int INSERTION = 1;
    public static final int REMOVAL = 2;
    public static final int EMPTY = 3;
    public final int Y_POSITION = 150;

    public ListThread(Group animation, List<ListElement> list, Score score,
            Main main, int operation, int number, ListElement listElement,
            int position) {
        this.animation = animation;
        this.list = list;
        this.score = score;
        this.main = main;
        this.operation = operation;
        this.number = number;
        this.listElement = listElement;
        this.position = position;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        
//        try {
//            Runtime.getRuntime().exec( "cmd /c cls" ) ; 
//        } catch (IOException ex) {
//            Logger.getLogger(ListThread.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        this.score.disableListPane();

        try {

            if (operation == INSERTION) {
                Main.tf.setText(Constants.LIST_INSERT);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + LIST_CAPACITY + "    "
                        + "tamanho = " + list.size());

                insertElement(number, position);
            }

        } finally {

            this.score.enableListPane();
            Main.running = false;

        }
    }

    public void insertElement(int number, int position) {

        double central = Y_POSITION + 110;
        double x, y;

        if (position > list.size() || list.size() == LIST_CAPACITY) {
            //POSICAO INVÁLIDA.
            return;

        } else {

            if (position < list.size()) {
                for (int i = list.size() - 1; i >= position; i--) {
                    moveRight(list.get(i).getStackPane(), 118);
                }
            }
            
            x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 210 + (position * 118);
            y = central;

            listElement.getStackPane().setVisible(true);
            int cycle = 0;

            do {
                listElement.getCircle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                listElement.getCircle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);

            moveToLocation(listElement.getStackPane(),
                    x - listElement.getX(), y - listElement.getY());
            list.add(position, listElement);
            
            
            for (int i = 0; i < list.size(); i++) {
                
                try {
                
                if (i == 0) {
                    
                    list.get(i).getPrevious().setVisible(false);
                    
                    if (i + 1 > list.size()) {
                        list.get(i).getNext().setVisible(false);
                    }
                    if (i == 0 && list.get(i + 1) != null) {
                        list.get(i).getNext().setVisible(true);
                    }
                    
                } else if ( (i + 1) <= list.size() - 1) {
                    
                    if (list.get(i + 1) == null) {
                        list.get(i).getNext().setVisible(false);
                        list.get(i).getPrevious().setVisible(true);
                    } else {
                        list.get(i).getNext().setVisible(true);
                        list.get(i).getPrevious().setVisible(true);
                    }
                    
                } else {
                    list.get(i).getNext().setVisible(true);
                    list.get(i).getPrevious().setVisible(true);
                }
                
                if (i == list.size() - 1) {
                    list.get(i).getNext().setVisible(false);
                }
                
                } catch (IndexOutOfBoundsException ex) { 
                    hideLeft = true;
                    hideRight = true;
                }
                
            }
            
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
    
}
