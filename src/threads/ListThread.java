/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import execution.Constants;
import execution.Main;
import java.util.List;
import javafx.scene.Group;
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
    private int number;
    private Group animation;
    private boolean answered = false;
    public final int LIST_CAPACITY = 5;
    public static final int INSERTION = 1;
    public static final int REMOVAL = 2;
    public static final int EMPTY = 3;
    public final int Y_POSITION = 150;

    public ListThread(Group animation, List<ListElement> list, Score score,
            Main main, int operation, int number, ListElement listElement) {
        this.animation = animation;
        this.list = list;
        this.score = score;
        this.main = main;
        this.operation = operation;
        this.number = number;
        this.listElement = listElement;
    }

    @Override
    public void run() {
        execute();
    }
    
    public void execute() {
        
        this.score.disableListPane();
        
        try {
            
            if (operation == INSERTION) {
                Main.tf.setText(Constants.STACK_PUSH);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + LIST_CAPACITY + "    "
                        + "tamanho = " + list.size());
                
//                pushElement(number);
            }
            
        } finally {
            
            this.score.enableListPane();
            Main.running = false;
            
        }        
    }
    
}
