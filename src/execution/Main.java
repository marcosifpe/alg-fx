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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
    private TextArea tf;
    private FlowPane fp;
    private final int NODES_LENGHT = 4;
    private final int SPACING_X = 450;
    private final int Y_POSITION = 150;
    public static boolean running = false;
    public static boolean canChoose = false;
    public static TextArea events;
    public static TextArea variables;
    public static FlowPane scoring;
    public static int chosenElements = 0;
    private final Interpolator interpolator = Interpolator.LINEAR;
    public boolean theSwitch = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initialize(stage);
        stage.show();
    }

    public void initialize(Stage stage) {
        
        fp = new FlowPane();
        stage.setTitle("AlgFX");
        root = new Group();
        score = new Score(this);

        tf = new TextArea(Constants.BUBBLE_SORT);
        tf.setEditable(false);
        tf.setFocusTraversable(false);
        tf.getStyleClass().add("text-f");
        tf.setId("text-f");
        tf.setPrefWidth(400);
        tf.setPrefHeight(400);
        tf.setTranslateX(1);
        tf.setTranslateY(30);
        tf.setFocusTraversable(false);

        VBox vertical = new VBox(20);
        final MenuBar menuBar = new MenuBar();
        menuBar.setPrefWidth(3000);

        MenuItem aboutItem = new MenuItem("Sobre");
        Menu menu = MenuBuilder.create().text("Ajuda").items(aboutItem).build();
        menuBar.getMenus().add(menu);
        vertical.getChildren().addAll(menuBar);

        nodes = new NodeElement[NODES_LENGHT];

        for (int i = 1; i < NODES_LENGHT + 1; i++) {
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, (i * 80) + SPACING_X, Y_POSITION);
        }

        animation = new Group();
        for (NodeElement ne : nodes) {
            animation.getChildren().add(ne.getStackPane());
        }
        
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
                    MovingThread mt = new MovingThread(nodes[0], nodes[NODES_LENGHT - 1], nodes, MovingThread.IN_PLACE_QUICK_SORT, root, score);
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
        
        Button toTest = new Button("TESTING");
        toTest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                if (!theSwitch) {
                fp = new FlowPane();
                fp.setId("text-f");
                fp.setPrefWidth(200);
                fp.setPrefHeight(200);
                Label lb = new Label("Teste:");
                lb.setPrefWidth(200);
                fp.getChildren().add(lb);
                
                Button b1 = new Button("Sim");
                b1.setPrefWidth(100);
                Button b2 = new Button("Não");
                b2.setPrefWidth(100);
                fp.getChildren().add(b1);
                fp.getChildren().add(b2);
                
                root.getChildren().add(fp);
                theSwitch = true;
                
                } else {
                    root.getChildren().remove(fp);
                    theSwitch = false;
                }
            }
        });

        horizontalBox = new HBox();
        horizontalBox.setTranslateY(29);

        button.setPrefWidth(200);
        button1.setPrefWidth(200);
        button2.setPrefWidth(200);
        button3.setPrefWidth(200);
        button4.setPrefWidth(200);
        button5.setPrefWidth(200);
        button6.setPrefWidth(200);
        button7.setPrefWidth(200);
        button8.setPrefWidth(200);
        toTest.setPrefWidth(200);

        FlowPane flowpane = new FlowPane();
        flowpane.maxWidth(450);
        flowpane.setPrefHeight(1000);
        flowpane.setId("flow");
        flowpane.setTranslateY(431);
        
        
        TilePane tilePane = new TilePane();
        tilePane.maxWidth(450);
        tilePane.setPrefColumns(2);
        tilePane.setPrefRows(3);
        
        Label sorting = new Label(" Algoritmos de Ordenação: ");
        sorting.setId("sort");
        sorting.setPrefWidth(401);
        sorting.setPrefHeight(30);
        
        Label datastructures = new Label(" Estruturas de Dados: ");
        datastructures.setId("sort");
        datastructures.setPrefWidth(401);
        datastructures.setPrefHeight(30);
        
        TilePane tilePane1 = new TilePane();
        tilePane1.maxWidth(450);
        tilePane1.setPrefColumns(2);
        tilePane1.setPrefRows(3);
        
        tilePane.getChildren().addAll(button, 
                button1, button2, button3, button4, toTest);
        
        tilePane1.getChildren().addAll(button5, button6, button7, button8);
        flowpane.getChildren().addAll(sorting, tilePane, datastructures, tilePane1);
        
        
        events = new TextArea(" Eventos: " + "\n\n" + Constants.NO_SIMULATION);
        events.setEditable(false);
        events.setId("event");
        events.setTranslateX(404);
        events.setTranslateY(601);
        events.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 406);
        events.setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 655);
        
        variables = new TextArea(" Variáveis: " + "\n\n" + Constants.NO_VARIABLES);
        variables.setEditable(false);
        variables.setId("event");
        variables.setTranslateX(404);
        variables.setTranslateY(500);
        variables.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 800);
        variables.setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 669);
        
        scoring = new FlowPane();
        
        Label sc = new Label(" Pontuação:");
        sc.setId("points");
        sc.setPrefWidth(382);
        
        
        scoring.setMaxWidth(382);
        scoring.setId("event");
        scoring.setTranslateX(973);
        scoring.setTranslateY(500);
        scoring.setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 669);
        scoring.getChildren().add(sc);
        
        root.getChildren().addAll(vertical, tf, horizontalBox, animation, 
                flowpane, events, variables, scoring);
        
        createProgressBar();
        Scene scene = new Scene(root, 800, 600, Color.DARKSLATEGRAY);
//        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }
    
    public void createProgressBar() {

        scoring.getChildren().remove(horizontalBox);

        horizontalBox = new HBox();
        horizontalBox.setTranslateY(30);
        pointProgressBar = new ProgressBar(0.0);
        pointProgressBar.setPrefWidth(300);
        pointProgressBar.setProgress(0.0);
        scoreLabel = new Label(" " + pointProgressBar.getProgress() + "%");

        flowProgressBar = new ProgressIndicator(0.0);
        flowProgressBar.setProgress(0.0);
        
        horizontalBox.getChildren().addAll(new Label("       "), pointProgressBar);
        scoring.getChildren().add(horizontalBox);

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
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, (i * 80) + SPACING_X, Y_POSITION);
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
