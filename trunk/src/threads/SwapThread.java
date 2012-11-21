/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import model.NodeElement;

/**
 *
 * @author rafael
 */
public class SwapThread extends Thread {

    NodeElement nodes[];
    NodeElement node1;
    NodeElement node2;
    final Interpolator interpolator = Interpolator.LINEAR;

    public SwapThread(NodeElement[] nodes, NodeElement node1, NodeElement node2) {
        this.nodes = nodes;
        this.node1 = node1;
        this.node2 = node2;
    }
    
    @Override
    public void run() {
        execute();
    }
    
    public void execute() {
        createSwitchingForwards(node1, node2, node1.getX(), node1.getY(), node2.getX(), node2.getY());
    }
    
    public void createSwitchingForwards(NodeElement node1, NodeElement node2, double positionX, double positionY, double destinationX, double destinationY) {
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2), node1.getStackPane());
        translateTransition1.setFromY(positionY - 30);
        translateTransition1.setToY(positionY + 40);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(2), node1.getStackPane());
        translateTransition2.setFromX(positionX - 30);
        translateTransition2.setToX(destinationX + 40);

        TranslateTransition translateTransition3 = new TranslateTransition(Duration.seconds(2), node1.getStackPane());
        translateTransition3.setFromY(positionY + 40);
        translateTransition3.setToY(destinationY - 30);

        SequentialTransition sequentialTransition = SequentialTransitionBuilder.create().children(translateTransition1, translateTransition2, translateTransition3).cycleCount(1).autoReverse(false).build();
        sequentialTransition.play();

    }
    
    
    
}
