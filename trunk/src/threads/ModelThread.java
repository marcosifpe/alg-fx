/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import model.NodeElement;

/**
 *
 * @author rafael
 */
public class ModelThread extends Thread {
    
    private NodeElement nodes[];
    private NodeElement node1;
    private NodeElement node2;
    private final Interpolator interpolator = Interpolator.LINEAR;
    private int operation;
    public final static int BUBBLE_SORT = 1, SELECTION_SORT = 2, INSERTION_SORT = 3;

    public NodeElement getNode1() {
        return node1;
    }

    public void setNode1(NodeElement node1) {
        this.node1 = node1;
    }

    public NodeElement getNode2() {
        return node2;
    }

    public void setNode2(NodeElement node2) {
        this.node2 = node2;
    }

    public NodeElement[] getNodes() {
        return nodes;
    }

    public void setNodes(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
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
                Logger.getLogger(SortingThread.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;

        }

        int temp1X, temp2X;
        temp1X = (int) node1X;
        temp2X = (int) node2X;
        
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

    }
    
}
