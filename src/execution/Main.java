/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BinaryNode;
import model.NodeElement;
import model.Score;
import threads.InsertionThread;
import threads.MovingThread;

/**
 *
 * @author rafael
 */
public class Main extends Application {

    private NodeElement nodes[];
    private Group animation, root;
    private HBox horizontalBox;
    private Main main;
    private Score score;
    private Label scoreLabel;
    private ProgressBar pointProgressBar;
    private ProgressIndicator flowProgressBar;
    private FlowPane fp, binaryTreeFp;
    private FlowPane flowpane, questionPane;
    private TextField numberField;
    private boolean decision = false;
    private int returningNumber = -2;
    private boolean binaryTreeOn = false;
    private List<BinaryNode> binaryTree;
    private boolean hitButton = false;
    private final int MAX_TREE_HEIGHT = 4;
    private final int NODES_LENGHT = 4;
    private final int SPACING_X = 450;
    private final int SLEEP_TIME = 50;
    private final int Y_POSITION = 150;
    public static boolean running = false;
    public static boolean canChoose = false;
    public static boolean insertionNumberChosen = false;
    public static TextArea events;
    public static TextArea variables;
    public static FlowPane scoring;
    public static TextArea tf;
    public static int chosenElements = 0;
    public static int numberSet = -1;
    private final Interpolator interpolator = Interpolator.LINEAR;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initialize(stage);
        stage.show();
    }

    public void initializeTreeElement() {
        binaryTree = new ArrayList<>();
    }

    public List<BinaryNode> getBinaryTree() {
        return binaryTree;
    }

    public void setBinaryTree(List<BinaryNode> binaryTree) {
        this.binaryTree = binaryTree;
    }

    public FlowPane getBinaryTreeFp() {
        return binaryTreeFp;
    }

    public void setBinaryTreeFp(FlowPane binaryTreeFp) {
        this.binaryTreeFp = binaryTreeFp;
    }

    public boolean isBinaryTreeOn() {
        return binaryTreeOn;
    }

    public void setBinaryTreeOn(boolean binaryTreeOn) {
        this.binaryTreeOn = binaryTreeOn;
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public FlowPane getFlowpane() {
        return flowpane;
    }

    public void setFlowpane(FlowPane flowpane) {
        this.flowpane = flowpane;
    }

    public FlowPane getFp() {
        return fp;
    }

    public void setFp(FlowPane fp) {
        this.fp = fp;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public NodeElement[] getNodes() {
        return nodes;
    }

    public void setNodes(NodeElement[] nodes) {
        this.nodes = nodes;
    }

    public TextField getNumberField() {
        return numberField;
    }

    public void setNumberField(TextField numberField) {
        this.numberField = numberField;
    }

    public FlowPane getQuestionPane() {
        return questionPane;
    }

    public void setQuestionPane(FlowPane questionPane) {
        this.questionPane = questionPane;
    }

    public int getReturningNumber() {
        return returningNumber;
    }

    public void setReturningNumber(int returningNumber) {
        this.returningNumber = returningNumber;
    }
    
    public void createNumberQuestion() {
        
        events.setText(Constants.EVENT + "\n\n"
                    + Constants.TREE_INSERTION);
        
        binaryTreeFp.setDisable(true);

        returningNumber = -2;

        questionPane = new FlowPane();
        questionPane.setPrefHeight(250);
        questionPane.setPrefWidth(120);
        questionPane.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        questionPane.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        Label question = new Label("Qual o elemento que deseja inserir?");
        question.setId("sort");
        question.setPrefWidth(250);
        question.setPrefHeight(50);

        numberField = new TextField();
        numberField.setPrefWidth(250);
        numberField.setPrefHeight(50);

        Button insert = new Button("Inserir");
        insert.setPrefWidth(125);
        insert.setPrefHeight(30);
        Button cancel = new Button("Cancelar");
        cancel.setPrefWidth(125);
        cancel.setPrefHeight(30);

        decision = false;
        numberSet = -1;

        insert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                try {
                    if (Integer.parseInt(numberField.getText()) != -1) {
                        
                        int number = Integer.parseInt(numberField.getText());
                        
                        InsertionThread it = new InsertionThread(number, main, score);
                        running = true;
                        Platform.runLater(it);

                    } else {
                        events.setText(Constants.EVENT + "\n\n" + Constants.INVALID_NUMBER);
                        hitButton = false;
                    }
                } catch (NumberFormatException ex) {
                    events.setText(Constants.EVENT + "\n\n" + Constants.INVALID_NUMBER);
                    hitButton = false;
                }

            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                root.getChildren().remove(questionPane);
                binaryTreeFp.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);
        
    }
    
    public void removeQuestion() {
        root.getChildren().remove(questionPane);
        binaryTreeFp.setDisable(false);
    }

    public Group getAnimation() {
        return animation;
    }

    public void setAnimation(Group animation) {
        this.animation = animation;
    }

    public boolean isHitButton() {
        return hitButton;
    }

    public void setHitButton(boolean hitButton) {
        this.hitButton = hitButton;
    }

    public void createElements() {

        nodes = new NodeElement[NODES_LENGHT];

        for (int i = 1; i < NODES_LENGHT + 1; i++) {
            nodes[i - 1] = new NodeElement(40.0, Integer.toString((int) (i + Math.random() * 200)), 2, (i * 80) + SPACING_X, Y_POSITION);
        }

        for (NodeElement ne : nodes) {
            animation.getChildren().add(ne.getStackPane());
        }


    }
    
    public void createTreeElement(int number, double x, double y) {

//        BinaryNode node = new BinaryNode(null, null, null, 30.0, Integer.toString(number),
//                Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.6, 40.0, 0);
        BinaryNode node = new BinaryNode(BinaryNode.ROOT, 30.0, Integer.toString(number),
                Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.6, 40.0, 0);
//        BinaryNode node = new BinaryNode(null, null, null, 30.0, Integer.toString(number),
//                800.0, 40.0, 0);

        binaryTree.add(node);
        animation.getChildren().add(node.getStackPane());
        

    }

    public void removeElements() {

        for (NodeElement e : nodes) {
            e.getStackPane().setTranslateY(9000);
        }

        System.gc();
        nodes = new NodeElement[NODES_LENGHT];

    }

    public void initialize(Stage stage) {

        initializeTreeElement();
        
        main = this;

        fp = new FlowPane();
        stage.setTitle("AlgFX");
        root = new Group();
        score = new Score(this);

        tf = new TextArea(Constants.NO_CODE);
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
        final ToggleGroup soundToggle = new ToggleGroup();

        RadioMenuItem onItem = new RadioMenuItem("Ligado");
        RadioMenuItem offItem = new RadioMenuItem("Desligado");

        onItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Constants.PLAY_SOUNDS = true;
            }
        });
        offItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Constants.PLAY_SOUNDS = false;
            }
        });

        onItem.setToggleGroup(soundToggle);
        offItem.setToggleGroup(soundToggle);
        Menu soundMenu = new Menu("Áudio");
        soundMenu.getItems().add(onItem);
        soundMenu.getItems().add(offItem);
        soundToggle.getToggles().get(0).setSelected(true);
        Menu audioMenu = MenuBuilder.create().text("Opções").items(soundMenu).build();
        Menu menu = MenuBuilder.create().text("Ajuda").items(aboutItem).build();
        menuBar.getMenus().addAll(audioMenu, menu);
        vertical.getChildren().addAll(menuBar);

        animation = new Group();

        Button button = new Button("Bubble Sort");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    createElements();
                    MovingThread mt = new MovingThread(MovingThread.BUBBLE_SORT, root, score, nodes);
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
                    createElements();
                    MovingThread mt = new MovingThread(MovingThread.SELECTION_SORT, root, score, nodes);
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
                    createElements();
                    MovingThread mt = new MovingThread(MovingThread.INSERTION_SORT, root, score, nodes);
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
                    createElements();
                    MovingThread mt = new MovingThread(MovingThread.SHELL_SORT, root, score, nodes);
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
                    createElements();
                    MovingThread mt = new MovingThread(MovingThread.IN_PLACE_QUICK_SORT, root, score, nodes);
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
        
        Button countingButton = new Button("Counting Sort");
        countingButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                }
            }
        });

        Button binaryTreeButton = new Button("Árvore Binária");
        binaryTreeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (!binaryTreeOn && !running) {

                    binaryTreeFp = new FlowPane();
                    binaryTreeFp.setId("text-f");
                    binaryTreeFp.setPrefWidth(200);
                    binaryTreeFp.setPrefHeight(195);
                    binaryTreeFp.setTranslateX(102);
                    binaryTreeFp.setTranslateY(431);
                    Label lb = new Label("    Árvore Binária de Pesquisa:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    binaryTreeFp.getChildren().add(lb);

                    Button search = new Button("Pesquisa");
                    search.setPrefWidth(200);
                    search.setPrefHeight(37);
                    Button insertion = new Button("Inserção");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            
                            createNumberQuestion();
//                            Platform.runLater(new InsertionThread(main, score));

                        }
                    });

                    Button removal = new Button("Remoção");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(37);
                    Button back = new Button("Voltar");
                    back.setPrefWidth(200);
                    back.setPrefHeight(37);
                    back.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            root.getChildren().remove(binaryTreeFp);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            binaryTreeOn = false;
                        }
                    });

                    binaryTreeFp.getChildren().add(search);
                    binaryTreeFp.getChildren().add(insertion);
                    binaryTreeFp.getChildren().add(removal);
                    binaryTreeFp.getChildren().add(back);

                    root.getChildren().add(binaryTreeFp);

                    for (Node n : root.getChildren()) {
                        if (n != binaryTreeFp && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    binaryTreeOn = true;

                }
            }
        });

        horizontalBox = new HBox();
        horizontalBox.setTranslateY(29);

        button.setPrefWidth(200);
        button1.setPrefWidth(200);
        button2.setPrefWidth(200);
        button3.setPrefWidth(200);
        countingButton.setPrefWidth(200);
        button4.setPrefWidth(200);
        button5.setPrefWidth(200);
        button6.setPrefWidth(200);
        button7.setPrefWidth(200);
        button8.setPrefWidth(200);
//        binaryTreeButton.setPrefWidth(200);
        binaryTreeButton.setPrefWidth(401);

        flowpane = new FlowPane();
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
        tilePane1.setPrefColumns(1);
        tilePane1.setPrefRows(4);

        tilePane.getChildren().addAll(button,
                button1, button2, button3, countingButton, button4);

        tilePane1.getChildren().addAll(binaryTreeButton, button6, button7, button8);
        flowpane.getChildren().addAll(sorting, tilePane, datastructures, tilePane1);


        events = new TextArea(" Eventos: " + "\n\n" + Constants.NO_SIMULATION);
        events.setEditable(false);
        events.setId("event");
        events.setTranslateX(404);
        events.setTranslateY(601);
        events.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 406);
        events.setPrefHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 655);

        variables = new TextArea(Constants.VARIABLES + Constants.NO_VARIABLES);
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

        root.getChildren().addAll(vertical, tf, horizontalBox,
                flowpane, events, variables, scoring, animation);

        createProgressBar();
        Scene scene = new Scene(root, 800, 600, Color.DARKSLATEGRAY);
//        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

    }

    public void selectText(String code) {

        tf.requestFocus();
        String s = tf.getText();
        int i = s.indexOf(code);
        tf.positionCaret(i);
        tf.selectForward();
        tf.selectRange(i, (i + code.length()));
        sleep(SLEEP_TIME * 15);

    }

    public void sleep(int time) {

        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.getMessage();
        }

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
