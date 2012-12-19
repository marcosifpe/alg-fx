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
import model.Score;
import model.StackElement;

/**
 *
 * @author rafael
 */
public class StackThread extends Thread {

    private final Score score;
    private Main main;
    private int operation;
    private List<StackElement> stack;
    private StackElement stackElement;
    private int number;
    private Group animation;
    private boolean answered = false;
    public static final int INSERTION = 1;
    public static final int REMOVAL = 2;
    public static final int TOP = 3;
    public final int Y_POSITION = 150;

    public StackThread(Group animation, List<StackElement> list, Score score,
            Main main, int operation, int number, StackElement stackElement) {
        this.animation = animation;
        this.stack = list;
        this.score = score;
        this.main = main;
        this.operation = operation;
        this.number = number;
        this.stackElement = stackElement;
    }

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        this.score.disableStackPane();
        try {
            
            if (operation == INSERTION) {

                Main.tf.setText(Constants.STACK_PUSH);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                        + "tamanho = " + stack.size());
                
                pushElement(number);
            } else if (operation == REMOVAL) {
                
                Main.tf.setText(Constants.STACK_POP);
                Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.UNAVAILABLE_EVENT);
                Main.variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                        + "tamanho = " + stack.size());
                popElement();
                
            }
            
            Main.tf.setText(Constants.NO_CODE);
            Main.events.setText(Constants.EVENT + Constants.LINE_BREAK
                        + Constants.NO_SIMULATION);
            
        } finally {
            
            
            this.score.enableStackPane();
            Main.running = false;

        }

    }

    public void pushElement(int number) {

        double central = Y_POSITION + 110;
        double x, y;

        this.main.selectText("  se (tamanho == capacidade) {\n");

        if (stack.size() == 5) {

            Constants.playQuestionSound(1);
            this.score.selectText("    Erro: Não há capacidade para mais elementos!\n");
            this.score.selectText("");
            return;

        } else {

            this.score.selectText("  } senao {\n");

            this.score.selectText("    pilha[tamanho] = numero;\n");
            
            if (stack.isEmpty()) {

                x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65;
                y = central;

            } else {
                if (stack.size() < 3) {

                    for (StackElement stackElement1 : stack) {
                        moveDownwards(stackElement1.getStackPane(), 70);
                        
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    
                    x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65;
                    y = central;

                } else {

                    x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65;
                    y = (Y_POSITION + (250) - (stack.size() * 70));

                }
            }

            this.main.getStack().add(stackElement);
            stackElement.getStackPane().setVisible(true);
            int cycle = 0;
            
            do {
                stackElement.getRectangle().setFill(Color.rgb(181, 97, 116));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                stackElement.getRectangle().setFill(Color.rgb(156, 216, 255));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                cycle++;
            } while (cycle != 5);
            
            moveToLocation(stackElement.getStackPane(), 
                    x - stackElement.getX(), y - stackElement.getY());
            
            this.score.selectText("    tamanho++;\n");
            Main.variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                    + "tamanho = " + stack.size());
            this.score.selectText("");
            

        }

    }
    
    public void popElement() {
        
        this.score.selectText("  se(tamanho == 0) {\n");
        if (stack.isEmpty()) {
            this.score.selectText("    Erro: Não há elementos à serem retirados!\n");
            Constants.playQuestionSound(1);
            this.score.selectText("");
            return;
        } else {
            this.score.selectText("  } senao  {\n");
            
            this.score.selectText("    pilha[tamanho - 1] = null;\n");
            if (stack.size() > 3) {    
                //====ESCOLHER O ELEMENTO ===
                
                //==== FIM ====
                StackElement s = stack.get(stack.size() - 1);
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
                
                moveToLocation(stack.get(stack.size() - 1).getStackPane(), 
                        stack.get(stack.size() - 1).getStackPane().getTranslateX() - 150, 
                        0);
                stack.get(stack.size() - 1).getStackPane().setVisible(false);
                
                this.score.selectText("    tamanho--;\n");
                stack.remove(stack.size() - 1);
                
                
            } else {
                
                StackElement s = stack.get(stack.size() - 1);
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
                
                moveToLocation(stack.get(stack.size() - 1).getStackPane(), 
                        stack.get(stack.size() - 1).getStackPane().getTranslateX() - 150, 
                        0);
                stack.get(stack.size() - 1).getStackPane().setVisible(false);
                
                
                stack.remove(stack.size() - 1);
                
                for (int i = stack.size() - 1; i >= 0; i--) {
                    moveUpwards(stack.get(i).getStackPane(), 70);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                this.score.selectText("    tamanho--;\n");
                
                
            }
            
        }
        this.score.selectText("");
        Main.variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                        + "tamanho = " + stack.size());
        
    }

    public void moveToLocation(StackPane pane, double x, double y) {

        double i = 0;

        while (i < y) {

            pane.setTranslateY(pane.getTranslateY() + 1);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }

        i = 0;

        while (i < x) {


            pane.setTranslateX(pane.getTranslateX() + 1);
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
