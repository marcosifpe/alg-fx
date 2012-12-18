/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import javafx.application.Platform;

/**
 *
 * @author rafael
 */
public class AuxiliaryThread extends Thread {
    
    public static final int STACK_INSERT = 1;
    private int operation;
    private StackThread stackThread;
    
    public AuxiliaryThread(StackThread stackThread, int operation) {
        this.stackThread = stackThread;
        this.operation = operation;
    }

    @Override
    public void run() {
        execute();
    }
    
    public void execute() {
        
        if (operation == STACK_INSERT) {
//            if (stackThread.execute()) {
                
//            }
                
            
//            Platform.runLater(stackThread);
        }
        
    }
    
    
}
