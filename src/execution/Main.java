/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.NodeElement;
import model.Score;
import threads.MovingThread;

/**
 *
 * @author rafael
 */
public class Main extends Application {

    private NodeElement nodes[];
    private Group animation, root;
    private HBox horizontalBox;
    private Score score;
    private Label scoreLabel;
    private ProgressBar pointProgressBar;
    private ProgressIndicator flowProgressBar;
    private final int NODES_LENGHT = 4;
    public static boolean running = false;
    public static boolean canChoose = false;
    public static int chosenElements = 0;
    final Interpolator interpolator = Interpolator.LINEAR;
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
//        udsijdsaidfj
        initialize(stage);
        stage.show();
    }

    public void initialize(Stage stage) {
        
        stage.setTitle("AlgFX");
        root = new Group();
        score = new Score(this);
        VBox vertical = new VBox(20);
        MenuBar menuBar = new MenuBar();
        MenuItem aboutItem = new MenuItem("Sobre");
        Menu menu = MenuBuilder.create().text("Ajuda").items(aboutItem).build();
        menuBar.getMenus().add(menu);

        vertical.getChildren().add(menuBar);
        nodes = new NodeElement[NODES_LENGHT];

        
        //=============== TESTES ==============
//        for (int i = 1; i < 11; i++) {
//            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, i * 80, 120);
//        }
        for (int i = 1; i < NODES_LENGHT + 1; i++) {
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, i * 80, 120);
        }

        animation = new Group();
        for (NodeElement ne : nodes) {
            animation.getChildren().add(ne.getStackPane());
        }

        Button button1 = new Button("Do Selection");
        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    MovingThread mt = new MovingThread(nodes[0], nodes[NODES_LENGHT - 1], nodes, MovingThread.SELECTION_SORT, root, score);
                    running = true;
                    mt.start();
                }
            }
        });


        Button button = new Button("Do BubbleSort");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    MovingThread mt = new MovingThread(nodes[0], nodes[NODES_LENGHT - 1], nodes, MovingThread.BUBBLE_SORT, root, score);
                    running = true;
                    mt.start();
                }

            }
        });

        Button button2 = new Button("Do Insertion");
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    MovingThread mt = new MovingThread(nodes[0], nodes[NODES_LENGHT - 1], nodes, MovingThread.INSERTION_SORT, root, score);
                    running = true;
                    mt.start();
                }
            }
        });

        Button button3 = new Button("Do Shell");
        button3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    MovingThread mt = new MovingThread(nodes[0], nodes[NODES_LENGHT - 1], nodes, MovingThread.SHELL_SORT, root, score);
                    running = true;
                    mt.start();
                }
            }
        });

        Button button4 = new Button("Do InPlace");
        button4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    MovingThread mt = new MovingThread(nodes[0], nodes[NODES_LENGHT - 1], nodes, MovingThread.SHELL_SORT, root, score);
                    running = true;
                    mt.start();
                }
            }
        });

        Button button5 = new Button("Generate all");
        button5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    elementGeneration();
                }
            }
        });

        Button button6 = new Button("CreateProgressBar");
        button6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    score.createMainProgressBar();
                }
            }
        });

        Button button7 = new Button("FillProgressBar");
        button7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running && pointProgressBar != null) {
                    score.fillProgressBar(0.1);
//                    pointProgressBar.setProgress(pointProgressBar.getProgress() + 0.1);
                }
            }
        });

        horizontalBox = new HBox();
        horizontalBox.setTranslateY(30);

        HBox buttonPlacing = new HBox();
        buttonPlacing.getChildren().addAll(button, button1, button2, button3, button4,
                button5, button6, button7);

        root.getChildren().addAll(buttonPlacing, horizontalBox, animation);
        stage.setScene(new Scene(root, 800, 600));


    }

    public void createProgressBar() {

        root.getChildren().remove(horizontalBox);

        horizontalBox = new HBox();
        horizontalBox.setTranslateY(30);
        pointProgressBar = new ProgressBar(0.0);
        pointProgressBar.setProgress(0.0);
        scoreLabel = new Label(" " + pointProgressBar.getProgress() + "%");

        flowProgressBar = new ProgressIndicator(0.0);
        flowProgressBar.setProgress(0.0);

        horizontalBox.getChildren().addAll(pointProgressBar, scoreLabel, flowProgressBar);
        root.getChildren().add(horizontalBox);
//        addChildrenInRoot(horizontalBox);

    }

    public void addChildrenInRoot(HBox group) {
        root.getChildren().add(group);
    }

    public void elementGeneration() {

        for (NodeElement e : nodes) {
            animation.getChildren().remove(e.getStackPane());
        }

        System.gc();
        nodes = new NodeElement[NODES_LENGHT];

        for (int i = 1; i < NODES_LENGHT + 1; i++) {
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, i * 80, 120);
        }

        for (NodeElement ne : nodes) {
            animation.getChildren().add(ne.getStackPane());
        }
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public ProgressIndicator getFlowProgressBar() {
        return flowProgressBar;
    }

    public void setFlowProgressBar(ProgressIndicator flowProgressBar) {
        this.flowProgressBar = flowProgressBar;
    }

    public HBox getHorizontalBox() {
        return horizontalBox;
    }

    public void setHorizontalBox(HBox horizontalBox) {
        this.horizontalBox = horizontalBox;
    }

    public ProgressBar getPointProgressBar() {
        return pointProgressBar;
    }

    public void setPointProgressBar(ProgressBar pointProgressBar) {
        this.pointProgressBar = pointProgressBar;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }
    
}
