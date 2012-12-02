/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import java.awt.Toolkit;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private TextArea tf, events;
    private final int NODES_LENGHT = 4;
    private final int SPACING_X = 450;
    public static boolean running = false;
    public static boolean canChoose = false;
    public static int chosenElements = 0;
    private final Interpolator interpolator = Interpolator.LINEAR;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initialize(stage);
        stage.show();
    }

    public void initialize(Stage stage) {
        
        stage.setTitle("AlgFX");
        root = new Group();
        score = new Score(this);

        tf = new TextArea("Teste" + "\nTeste2" + "\nasdf sss");
        tf.setEditable(false);
        tf.getStyleClass().add("text-f");
        tf.setId("text-f");
        tf.setPrefWidth(400);
        tf.setPrefHeight(400);
        tf.setTranslateX(1);
        tf.setTranslateY(30);
        tf.setFocusTraversable(false);

        VBox vertical = new VBox(20);
        final MenuBar menuBar = new MenuBar();
        menuBar.setPrefWidth(4000);

        MenuItem aboutItem = new MenuItem("Sobre");
        Menu menu = MenuBuilder.create().text("Ajuda").items(aboutItem).build();
        menuBar.getMenus().add(menu);
        vertical.getChildren().addAll(menuBar);

        nodes = new NodeElement[NODES_LENGHT];

        for (int i = 1; i < NODES_LENGHT + 1; i++) {
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, (i * 80) + SPACING_X, 120);
        }

        animation = new Group();
        for (NodeElement ne : nodes) {
            animation.getChildren().add(ne.getStackPane());
        }

        Button button1 = new Button("Selection Sort");
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


        Button button = new Button("Bubble Sort");
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

        Button button2 = new Button("Insertion Sort");
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

        Button button3 = new Button("Shell Sort");
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

        Button button4 = new Button("In-Place QuickSort");
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
                }
            }
        });
        Button button8 = new Button("SelectText");
        button8.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                tf.requestFocus();
                String s = tf.getText();
                int i = s.indexOf("as");
                tf.positionCaret(i);
                tf.selectEnd();
                System.out.println(tf.getSelectedText());

            }
        });

        horizontalBox = new HBox();
        horizontalBox.setTranslateY(29);

        button.setPrefWidth(200);
        button1.setPrefWidth(200);
        button2.setPrefWidth(200);
        button3.setPrefWidth(200);
        button4.setPrefWidth(200);
        button5.setPrefWidth(135);
        button6.setPrefWidth(135);
        button7.setPrefWidth(135);
        button8.setPrefWidth(135);

        FlowPane flowpane = new FlowPane();
        flowpane.maxWidth(450);
        flowpane.setPrefHeight(1000);
        flowpane.setId("flow");
        flowpane.setTranslateY(431);
        
        
        TilePane tilePane = new TilePane();
        tilePane.maxWidth(450);
        tilePane.setPrefColumns(2);
        tilePane.setPrefRows(4);
        
        Label sorting = new Label(" Algoritmos de Ordenação: ");
        sorting.setId("sort");
        sorting.setPrefWidth(401);
        sorting.setPrefHeight(30);
        Label datastructures = new Label(" Estruturas de Dados: ");
        datastructures.setId("sort");
        datastructures.setPrefWidth(401);
        datastructures.setPrefHeight(30);
        
        tilePane.getChildren().addAll(button, 
                button1, button2, button3, button4);
        flowpane.getChildren().addAll(sorting, tilePane, datastructures);
        
        
        events = new TextArea(" Eventos: ");
        events.setId("event");
        events.setTranslateX(402);
        events.setTranslateY(550);
        events.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 395);
        
        root.getChildren().addAll(vertical, tf, horizontalBox, animation, flowpane, events);
        root.setId("pane");

//        Scene scene = new Scene(root, 800, 600, Color.DARKSLATEGRAY);
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

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
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, (i * 80) + SPACING_X, 120);
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
