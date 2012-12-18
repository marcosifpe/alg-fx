/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BinaryNode;
import model.NodeElement;
import model.Score;
import model.StackElement;
import threads.AuxiliaryThread;
import threads.TreeThread;
import threads.SortingThread;
import threads.StackThread;

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
    private FlowPane fp, binaryTreeFlowPane, vectorFlowPane;
    private FlowPane stackFlowPane, listFlowPane, queueFlowPane;
    private FlowPane flowpane, questionPane;
    private TextField numberField;
    private boolean decision = false;
    private int returningNumber = -2;
    private boolean binaryTreeOn = false;
    private boolean vectorOn = false;
    private boolean stackOn = false;
    private boolean listOn = false;
    private boolean queueOn = false;
    private List<BinaryNode> binaryTree;
    private List<StackElement> stack;
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
        return binaryTreeFlowPane;
    }

    public void setBinaryTreeFp(FlowPane binaryTreeFp) {
        this.binaryTreeFlowPane = binaryTreeFp;
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
        
        binaryTreeFlowPane.setDisable(true);

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
                        
                        TreeThread tt = new TreeThread(number, main, score, TreeThread.INSERTION);
                        running = true;
                        Platform.runLater(tt);

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
                binaryTreeFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);
        
    }
    
    public void testStackCreation(int number) {
        double central = Y_POSITION + 110;
        StackElement element = null;
        
        if (stack.isEmpty()) {
                element = new StackElement(100, 50, Integer.toString(number),
                        0, Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65,
                        central);
            } else {
                if (stack.size() < 3) {

                    for (int i = 0; i < stack.size(); i++) {
                        moveVertically(stack.get(i).getStackPane(), 70);
                        
                        try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StackThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    }
                    
                    

                    element = new StackElement(100, 50, Integer.toString(number),
                            0, Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65,
                            central);
                } else {
                    element = new StackElement(100, 50, Integer.toString(number),
                            0, Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65,
                            (Y_POSITION + (250) - (stack.size() * 70)));
                }
            }
        
        System.out.println("Element: " + element.getElement());
        animation.getChildren().add(element.getStackPane());
        stack.add(element);
    }

    public FlowPane getStackFlowPane() {
        return stackFlowPane;
    }

    public void setStackFlowPane(FlowPane stackFlowPane) {
        this.stackFlowPane = stackFlowPane;
    }
    
    
    public void createStackNumberQuestion() {
        
        events.setText(Constants.EVENT + "\n\n"
                    + Constants.TREE_INSERTION);
        
        stackFlowPane.setDisable(true);

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
                        
                        StackElement se = new StackElement(100, 50, Integer.toString(number), 
                                1, 457, 100);
                        
                        se.getStackPane().setVisible(false);
                        animation.getChildren().add(se.getStackPane());
                        StackThread st = new StackThread(animation, stack, score, 
                                main, StackThread.INSERTION, number, se);
                        running = true;
                        root.getChildren().remove(questionPane);
                        st.start();
                        events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);

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
                stackFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);
        
    }
    
    public void removeQuestion() {
        root.getChildren().remove(questionPane);
        binaryTreeFlowPane.setDisable(false);
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
    
    public void removeElements() {

        for (NodeElement e : nodes) {
            e.getStackPane().setTranslateY(9000);
        }

        System.gc();
        nodes = new NodeElement[NODES_LENGHT];

    }
    
    public int count = 0;
    public void createTreeElement(int number, double x, double y) {

        BinaryNode node;
        
        if (binaryTree.isEmpty()) {
            node = new BinaryNode(BinaryNode.ROOT, 30.0, Integer.toString(number),
                    x, y, count);
        } else {
            node = new BinaryNode(BinaryNode.NORMAL_NODE, 30.0, Integer.toString(number),
                    x, y, count);
        }
        count++;
        
        binaryTree.add(node);
        animation.getChildren().add(node.getStackPane());
        

    }
    
    public void removeTreeElements() {
        
        for (BinaryNode e : binaryTree) {
            e.getStackPane().setTranslateY(9000);
        }

        System.gc();
        binaryTree = new ArrayList<>();
        
    }
    
    public void animationStackElement(StackPane pane) {
        animation.getChildren().add(pane);
    }
    
    public void addToAnimation(StackPane pane) {
        animation.getChildren().add(pane);
    }
    
    public void moveVertically(StackPane pane, int quantity) {
        
        int i = 0;
        
        while (i != quantity) {
            
            pane.setTranslateY(pane.getTranslateY() + 1);
            sleep(5);
            i++;
            
        }
        
    }
    
    public void removeStackElements() {
        
        if (!stack.isEmpty()) {
            for (StackElement e : stack) {
                e.getStackPane().setTranslateY(9000);
            }

            System.gc();
            stack = new ArrayList<>();

        }
        
    }
    
    public void initialize(Stage stage) {

        initializeTreeElement();
        stack = new ArrayList<>();
        
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
                    SortingThread mt = new SortingThread(SortingThread.BUBBLE_SORT, root, score, nodes);
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
                    SortingThread mt = new SortingThread(SortingThread.SELECTION_SORT, root, score, nodes);
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
                    SortingThread mt = new SortingThread(SortingThread.INSERTION_SORT, root, score, nodes);
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
                    SortingThread mt = new SortingThread(SortingThread.SHELL_SORT, root, score, nodes);
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
                    SortingThread mt = new SortingThread(SortingThread.IN_PLACE_QUICK_SORT, root, score, nodes);
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

                    binaryTreeFlowPane = new FlowPane();
                    binaryTreeFlowPane.setId("text-f");
                    binaryTreeFlowPane.setPrefWidth(200);
                    binaryTreeFlowPane.setPrefHeight(195);
                    binaryTreeFlowPane.setTranslateX(102);
                    binaryTreeFlowPane.setTranslateY(431);
                    Label lb = new Label("    Árvore Binária de Pesquisa:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    binaryTreeFlowPane.getChildren().add(lb);

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
                            removeTreeElements();
                            root.getChildren().remove(binaryTreeFlowPane);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            binaryTreeOn = false;
                        }
                    });

                    binaryTreeFlowPane.getChildren().add(search);
                    binaryTreeFlowPane.getChildren().add(insertion);
                    binaryTreeFlowPane.getChildren().add(removal);
                    binaryTreeFlowPane.getChildren().add(back);

                    root.getChildren().add(binaryTreeFlowPane);

                    for (Node n : root.getChildren()) {
                        if (n != binaryTreeFlowPane && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    binaryTreeOn = true;

                }
            }
        });
        
        Button vectorButton = new Button("Vetor");
        vectorButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!vectorOn && !running) {

                    vectorFlowPane = new FlowPane();
                    vectorFlowPane.setId("text-f");
                    vectorFlowPane.setPrefWidth(200);
                    vectorFlowPane.setPrefHeight(195);
                    vectorFlowPane.setTranslateX(102);
                    vectorFlowPane.setTranslateY(431);
                    Label lb = new Label("    Vetor:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    vectorFlowPane.getChildren().add(lb);

                    Button initialize = new Button("IsEmpty");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);
                    Button insertion = new Button("Inserção");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            
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
//                            removeTreeElements();
                            root.getChildren().remove(vectorFlowPane);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            vectorOn = false;
                        }
                    });

                    vectorFlowPane.getChildren().add(initialize);
                    vectorFlowPane.getChildren().add(insertion);
                    vectorFlowPane.getChildren().add(removal);
                    vectorFlowPane.getChildren().add(back);

                    root.getChildren().add(vectorFlowPane);

                    for (Node n : root.getChildren()) {
                        if (n != vectorFlowPane && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    vectorOn = true;

                }
            }
        });
        
        Button stackButton = new Button("Pilha");
        stackButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
              if (!stackOn && !running) {

                    stackFlowPane = new FlowPane();
                    stackFlowPane.setId("text-f");
                    stackFlowPane.setPrefWidth(200);
                    stackFlowPane.setPrefHeight(195);
                    stackFlowPane.setTranslateX(102);
                    stackFlowPane.setTranslateY(431);
                    Label lb = new Label("    Pilha:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    stackFlowPane.getChildren().add(lb);

                    Button initialize = new Button("IsEmpty");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);
                    Button insertion = new Button("Push");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                tf.setText(Constants.STACK_PUSH);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + stack.size());
                                createStackNumberQuestion();
                            }
                        }
                    });

                    Button removal = new Button("Pop");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(37);
                    
                    removal.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                
                                running = true;
                                stackFlowPane.setDisable(true);
                                tf.setText(Constants.STACK_POP);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + stack.size());
                                StackThread st = new StackThread(animation, stack, score,
                                        main, StackThread.REMOVAL, 0, null);
                                st.start();
                                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                                
                            }
                        }
                    });
                    
                    Button back = new Button("Voltar");
                    back.setPrefWidth(200);
                    back.setPrefHeight(37);
                    back.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if(!running) {
                            Main.tf.setText(Constants.NO_CODE);
                            Main.variables.setText(Constants.VARIABLES + 
                                    Constants.NO_VARIABLES);
                            Main.events.setText(Constants.EVENT + 
                                    Constants.LINE_BREAK +
                                    Constants.NO_SIMULATION);
                            removeStackElements();
                            root.getChildren().remove(stackFlowPane);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            stackOn = false;
                            }
                        }
                    });

                    stackFlowPane.getChildren().add(initialize);
                    stackFlowPane.getChildren().add(insertion);
                    stackFlowPane.getChildren().add(removal);
                    stackFlowPane.getChildren().add(back);

                    root.getChildren().add(stackFlowPane);

                    for (Node n : root.getChildren()) {
                        if (n != stackFlowPane && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    stackOn = true;

                }  
                
            }
        });
        
        Button queueButton = new Button("Fila");
        queueButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!queueOn && !running) {

                    queueFlowPane = new FlowPane();
                    queueFlowPane.setId("text-f");
                    queueFlowPane.setPrefWidth(200);
                    queueFlowPane.setPrefHeight(195);
                    queueFlowPane.setTranslateX(102);
                    queueFlowPane.setTranslateY(431);
                    Label lb = new Label("    Fila:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    queueFlowPane.getChildren().add(lb);

                    Button initialize = new Button("IsEmpty");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);
                    Button insertion = new Button("Inserir");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            
                        }
                    });

                    Button removal = new Button("Remover");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(37);
                    
                    Button back = new Button("Voltar");
                    back.setPrefWidth(200);
                    back.setPrefHeight(37);
                    back.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
//                            removeTreeElements();
                            root.getChildren().remove(queueFlowPane);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            queueOn = false;
                        }
                    });

                    queueFlowPane.getChildren().add(initialize);
                    queueFlowPane.getChildren().add(insertion);
                    queueFlowPane.getChildren().add(removal);
                    queueFlowPane.getChildren().add(back);

                    root.getChildren().add(queueFlowPane);

                    for (Node n : root.getChildren()) {
                        if (n != queueFlowPane && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    queueOn = true;

                }  
            }
        });
        
        Button listButton = new Button("Lista");
        listButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                if (!listOn && !running) {

                    listFlowPane = new FlowPane();
                    listFlowPane.setId("text-f");
                    listFlowPane.setPrefWidth(200);
                    listFlowPane.setPrefHeight(195);
                    listFlowPane.setTranslateX(102);
                    listFlowPane.setTranslateY(431);
                    Label lb = new Label("    Lista:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    listFlowPane.getChildren().add(lb);

                    Button initialize = new Button("IsEmpty");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);
                    Button insertion = new Button("Inserir");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            
                        }
                    });

                    Button removal = new Button("Remover");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(37);
                    
                    Button back = new Button("Voltar");
                    back.setPrefWidth(200);
                    back.setPrefHeight(37);
                    back.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
//                            removeTreeElements();
                            root.getChildren().remove(listFlowPane);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            listOn = false;
                        }
                    });

                    listFlowPane.getChildren().add(initialize);
                    listFlowPane.getChildren().add(insertion);
                    listFlowPane.getChildren().add(removal);
                    listFlowPane.getChildren().add(back);

                    root.getChildren().add(listFlowPane);

                    for (Node n : root.getChildren()) {
                        if (n != listFlowPane && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    listOn = true;

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
        binaryTreeButton.setPrefWidth(401);
        vectorButton.setPrefWidth(401);
        stackButton.setPrefWidth(401);
        listButton.setPrefWidth(401);
        queueButton.setPrefWidth(401);

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

        tilePane1.getChildren().addAll(binaryTreeButton, vectorButton, stackButton, 
                queueButton, listButton);
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

    public List<StackElement> getStack() {
        return stack;
    }

    public void setStack(List<StackElement> stack) {
        this.stack = stack;
    }
    
}
