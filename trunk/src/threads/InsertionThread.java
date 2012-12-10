/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import execution.Constants;
import execution.Main;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BinaryNode;
import model.NodeElement;
import model.Score;

/**
 *
 * @author rafael
 */
public class InsertionThread extends Thread {

    private final Score score;
    private final Main main;
    private int number;
    private boolean answered = false;
    public static final int INSERTION_TREE = 1;

    public InsertionThread(int number, Main main, Score score) {
        this.number = number;
        this.main = main;
        this.score = score;
    }

    public boolean hasAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
    
    @Override
    public void run() {
        execute();
    }

    public void execute() {

        try {
            Main.events.setText(Constants.EVENT + "\n\n"
                    + Constants.NO_SIMULATION);
            this.main.removeQuestion();
            this.main.getBinaryTreeFp().setDisable(true);
            System.out.println("Vai inserir o: " + number);
            insertNumber(number);
        
        } finally {
            
            this.main.getBinaryTreeFp().setDisable(false);
            Main.running = false;
            return;
        }
        
    }
    
    public void insertNumber(int number) {
        
        if (this.main.getBinaryTree().isEmpty()) {
            System.out.println("If");
            this.main.createTreeElement(number, Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.6, 40.0);
//            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, (i * 80) + SPACING_X, Y_POSITION);
//            BinaryNode node = new BinaryNode(null, null, null, 40.0, number + "", 
//                    800.0, 40.0, 0);
//            this.main.getBinaryTree().add(node);
//            
//            this.main.getAnimation().getChildren().add(node.getStackPane());
            
        } else {
            System.out.println("Else!");
            int size = this.main.getBinaryTree().size();
            this.main.createTreeElement(number, this.main.getBinaryTree().get(size - 1).getX() - 70, 
                    this.main.getBinaryTree().get(size - 1).getY() + 80);
        }
        
    }
    
}
