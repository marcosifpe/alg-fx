/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package execution;

//RESOLUÇÃO MÍNIMA 1280 x 768 (Ajustar pontuação)
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.*;
import threads.*;

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
    public static Label scoreLabel;
    private ProgressBar pointProgressBar;
    private ProgressIndicator flowProgressBar;
    private FlowPane fp, binaryTreeFlowPane, vectorFlowPane;
    private FlowPane stackFlowPane, listFlowPane, queueFlowPane, circularQueueFlowPane;
    private FlowPane flowpane, questionPane;
    private TextField numberField;
    private int tempMin, tempMax;
    private NodeElement sortedVector[];
    private boolean decision = false;
    private int returningNumber = -2;
    private int listPosition;
    private int listNumber;
    private boolean binaryTreeOn = false;
    private boolean vectorOn = false;
    private boolean stackOn = false;
    private boolean listOn = false;
    private boolean circularListOn = false;
    private boolean queueOn = false;
    private List<BinaryNode> binaryTree;
    private List<StackElement> stack;
    private List<QueueElement> queue;
    private List<NodeElement> circularQueue;
    private List<ListElement> list;
    private List<VectorElement> vector;
    private List<NodeElement> vectorElements;
    private int actualCapacity;
    private boolean hitButton = false;
    private final int MAX_TREE_HEIGHT = 4;
    private final int NODES_LENGHT = 4;
    private final int SPACING_X = 450;
    private final int SLEEP_TIME = 50;
    private final int Y_POSITION = 150;
    private final int COUNTING_LENGHT = 8;
    private final int BOXES_LENGHT = 8;
    public int count = 0;
    public static boolean running = false;
    public static boolean canChoose = false;
    public static boolean canChooseBox = false;
    public static boolean canChooseStack = false;
    public static boolean canChooseQueue = false;
    public static boolean canChooseHeadTail = false;
    public static boolean canChooseCircular = false;
    public static boolean insertionNumberChosen = false;
    public static TextArea events;
    public static TextArea variables;
    public static FlowPane scoring;
    public static TextArea tf;
    public static StackElement headElement, tailElement;
    public static int chosenElements = 0;
    public static int chosenBoxes = 0;
    public static int chosenStacks = 0;
    public static int chosenQueues = 0;
    public static int chosenHeadTails = 0;
    public static int chosenCirculars = 0;
    public static int numberSet = -1;
    public static int vectorCapacity = 2;
    public static int head = 0, tail = 0;
    public static int circularPosition = 0;
    public static int circularSize = 0;
    private final Interpolator interpolator = Interpolator.LINEAR;
    private VectorElement circularContainer[];
    private FlowPane pane1 = new FlowPane();
    private NodeElement testElement;
    private VectorElement sumVector[];
    private VectorElement tempVector[];
    private NodeElement countingVector[];
    private CountingBox countingBoxes[];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        pane1.prefHeightProperty().bind(stage.heightProperty());
        pane1.prefWidthProperty().bind(stage.widthProperty());
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

    private FlowPane messagePane;
    
    public void createMessagePane(String text) {
        
        messagePane = new FlowPane();
        messagePane.setPrefHeight(400);
        messagePane.setPrefWidth(400);
        messagePane.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        messagePane.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
//        Toolkit.getDefaultToolkit().getScreenSize().getWidth()
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
                tf.setText(Constants.NO_CODE);

            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createQueueNumberQuestion() {

        events.setText(Constants.EVENT + "\n\n"
                + Constants.TREE_INSERTION);

        queueFlowPane.setDisable(true);

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

                        QueueElement qe = new QueueElement(50, 100, Integer.toString(number),
                                1, 457, 100);

                        qe.getStackPane().setVisible(false);
                        animation.getChildren().add(qe.getStackPane());
                        QueueThread st = new QueueThread(animation, queue, score,
                                main, QueueThread.ENQUEUE, number, qe);
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
                queueFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);

            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createCircularQueueNumberQuestion() {

        tf.setText(Constants.CIRCULAR_ENQUEUE);
        events.setText(Constants.EVENT + "\n\n"
                + Constants.TREE_INSERTION);

        circularQueueFlowPane.setDisable(true);

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

                        NodeElement qe = new NodeElement(30.0, Integer.toString(number), 1, 457, 50);
                        qe.getStackPane().setVisible(false);

                        animation.getChildren().add(qe.getStackPane());
//                        QueueThread st = new QueueThread(animation, queue, score,
//                                main, QueueThread.ENQUEUE, number, qe);
                        CircularQueueThread st = new CircularQueueThread(animation,
                                circularQueue, score, main, CircularQueueThread.ENQUEUE,
                                number, qe);
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
                circularQueueFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);

            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createListPositionQuestion() {

        events.setText(Constants.EVENT + "\n\n"
                + Constants.INSERT_POSITION);

        listFlowPane.setDisable(true);

        returningNumber = -2;

        questionPane = new FlowPane();
        questionPane.setPrefHeight(250);
        questionPane.setPrefWidth(120);
        questionPane.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        questionPane.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        Label question = new Label("Qual a posição destinada ao elemento?");
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
                        listPosition = number;

                        ListElement le = new ListElement(40.0, Integer.toString(listNumber),
                                1, 457, 100);
                        le.getNext().setVisible(false);
                        le.getPrevious().setVisible(false);
                        le.getStackPane().setVisible(false);
                        animation.getChildren().add(le.getStackPane());
                        ListThread st = new ListThread(animation, list, score,
                                main, ListThread.INSERTION, listNumber, le, listPosition);
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
                listFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);

            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createVectorPositionQuestion() {

        events.setText(Constants.EVENT + "\n\n"
                + Constants.INSERT_POSITION);

        vectorFlowPane.setDisable(true);

        returningNumber = -2;

        questionPane = new FlowPane();
        questionPane.setPrefHeight(250);
        questionPane.setPrefWidth(120);
        questionPane.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        questionPane.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        Label question = new Label("Qual a posição destinada ao elemento?");
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
                        listPosition = number;

                        NodeElement element = new NodeElement(30.0,
                                Integer.toString(listNumber),
                                0, 457, 100);

                        element.getStackPane().setVisible(false);
                        animation.getChildren().add(element.getStackPane());
                        actualCapacity = vector.size();

                        VectorThread vt = new VectorThread(animation,
                                vector, score, main, VectorThread.INSERTION,
                                number, element, listPosition, actualCapacity,
                                vectorElements);
                        running = true;
                        root.getChildren().remove(questionPane);
                        vt.start();
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
                vectorFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);

            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createListPositionRemovalQuestion() {

        events.setText(Constants.EVENT + "\n\n"
                + Constants.REMOVE_POSITION);

        listFlowPane.setDisable(true);

        returningNumber = -2;

        questionPane = new FlowPane();
        questionPane.setPrefHeight(250);
        questionPane.setPrefWidth(120);
        questionPane.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        questionPane.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        Label question = new Label("Qual a posição destinada à remoção?");
        question.setId("sort");
        question.setPrefWidth(250);
        question.setPrefHeight(50);

        numberField = new TextField();
        numberField.setPrefWidth(250);
        numberField.setPrefHeight(50);

        Button insert = new Button("Remover");
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
                        listPosition = number;

                        ListThread st = new ListThread(animation, list, score,
                                main, ListThread.REMOVAL, 0, null, listPosition);
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
                listFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);
            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createListNumberQuestion() {

        events.setText(Constants.EVENT + "\n\n"
                + Constants.TREE_INSERTION);

        listFlowPane.setDisable(true);

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

        Button insert = new Button("Próximo");
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
                        listNumber = number;
                        root.getChildren().remove(questionPane);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
//                        tf.setText(Constants.NO_CODE);
                        createListPositionQuestion();

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
                listFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);

            }
        });

        questionPane.getChildren().addAll(question, numberField, insert, cancel);
        root.getChildren().add(questionPane);

    }

    public void createVectorNumberQuestion() {

        events.setText(Constants.EVENT + "\n\n"
                + Constants.TREE_INSERTION);

        vectorFlowPane.setDisable(true);

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

        Button insert = new Button("Próximo");
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
                        listNumber = number;
                        root.getChildren().remove(questionPane);

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                        createVectorPositionQuestion();

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
                vectorFlowPane.setDisable(false);
                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                tf.setText(Constants.NO_CODE);

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
    

    public void createCountingSortElements() {


        countingVector = new NodeElement[COUNTING_LENGHT];
        sortedVector = new NodeElement[COUNTING_LENGHT];

        for (int i = 1; i < COUNTING_LENGHT + 1; i++) {
            countingVector[i - 1] = new NodeElement(40.0, Integer.toString((int) (Math.random() * 8)), 2, (i * 80) + SPACING_X, Y_POSITION);
            sortedVector[i - 1] = new NodeElement(40.0, Integer.toString(0), 0, (i * 80) + SPACING_X, Y_POSITION + 100.0);
        }

        for (NodeElement ne : countingVector) {
            animation.getChildren().add(ne.getStackPane());
        }

        for (NodeElement ne : sortedVector) {
            animation.getChildren().add(ne.getStackPane());
            ne.getStackPane().setVisible(false);
        }

        createCountingBoxes();
    }

    public void createCountingBoxes() {

        List<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < countingVector.length; i++) {
            tempList.add(countingVector[i].getElementAsInt());
        }
        tempMin = Collections.min(tempList);
        tempMax = Collections.max(tempList);

        countingBoxes = new CountingBox[BOXES_LENGHT];

        for (int i = 1; i < BOXES_LENGHT + 1; i++) {

            countingBoxes[i - 1] = new CountingBox(80.0, 80.0, Integer.toString(0),
                    Integer.toString(i - 1), 1, (i * 80) + SPACING_X, Y_POSITION + 210);

        }

        for (CountingBox ne : countingBoxes) {
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

    public void removeCountingElements() {

        for (NodeElement e : countingVector) {
            e.getStackPane().setTranslateY(9000);
        }

        for (NodeElement e : sortedVector) {
            e.getStackPane().setTranslateY(9000);
        }

        for (CountingBox e : countingBoxes) {
            e.getStackPane().setTranslateY(9000);
        }

        System.gc();
        countingVector = new NodeElement[COUNTING_LENGHT];
        sortedVector = new NodeElement[COUNTING_LENGHT];
        countingBoxes = new CountingBox[BOXES_LENGHT];

    }

    public void removeVector() {

        for (VectorElement e : vector) {
            e.getStackPane().setTranslateY(9000);
            if (e.getNode() != null) {
                e.getNode().getStackPane().setTranslateY(9000);
            }
        }

        System.gc();

        vectorCapacity = 2;
        vectorElements.clear();
        vector = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            vector.add(new VectorElement(80.0, 80.0, "0", 0,
                    Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 300 + (vector.size() * 90), Y_POSITION + 110, null));

            if (i > 1) {
                vector.get(i).getStackPane().setVisible(false);
            }

        }

    }

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

    public void removeStackElements() {

        if (!stack.isEmpty()) {
            for (StackElement e : stack) {
                e.getStackPane().setTranslateY(9000);
            }

            System.gc();
            stack = new ArrayList<>();

        }

    }

    public void removeQueueElements() {

        if (!queue.isEmpty()) {
            for (QueueElement e : queue) {
                e.getStackPane().setTranslateY(9000);
            }

            System.gc();
            queue = new ArrayList<>();

        }

    }

    public void removeListElements() {

        if (!list.isEmpty()) {
            for (ListElement e : list) {
                e.getStackPane().setTranslateY(9000);
            }
            System.gc();
            list = new ArrayList<>();
        }

    }

    public void removeCircularQueueElements() {

        if (!circularQueue.isEmpty()) {

            for (NodeElement e : circularQueue) {
                e.getStackPane().setTranslateY(9000);
            }

            System.gc();
            circularQueue = new ArrayList<>();
        }

    }
    private Stage dialogStage;
    private int qu = 0;

    public void initialize(Stage stage) {

        initializeTreeElement();
        stack = new ArrayList<>();
        queue = new ArrayList<>();
        list = new ArrayList<>();
        circularQueue = new ArrayList<>(8);
        vector = new ArrayList<>(8);
        vectorElements = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            vector.add(new VectorElement(80.0, 80.0, "0", 0,
                    Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65
                    - 300 + (vector.size() * 90), Y_POSITION + 110, null));

            if (i > 1) {
                vector.get(i).getStackPane().setVisible(false);
            }

        }

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
        tf.setPrefHeight(423);
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

        RadioMenuItem saBubble = new RadioMenuItem("Bubble Sort");
        RadioMenuItem saSelection = new RadioMenuItem("Selection Sort");
        RadioMenuItem saInsertion = new RadioMenuItem("Insertion Sort");
        RadioMenuItem saShell = new RadioMenuItem("Shell Sort");
        RadioMenuItem saQuick = new RadioMenuItem("In-Place Quick Sort");
        RadioMenuItem saCounting = new RadioMenuItem("Counting Sort");

        RadioMenuItem dsVector = new RadioMenuItem("Vetor");
        RadioMenuItem dsStack = new RadioMenuItem("Pilha");
        RadioMenuItem dsQueue = new RadioMenuItem("Fila");
        RadioMenuItem dsCircularQueue = new RadioMenuItem("Fila Circular");
        RadioMenuItem dsList = new RadioMenuItem("Lista");

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
        Menu saMenu = MenuBuilder.create().text("Algoritmos de Ordenação").
                items(saBubble, saSelection, saInsertion, saShell, saCounting,
                saQuick).build();
        Menu dsMenu = MenuBuilder.create().text("Estruturas de Dados").
                items(dsVector, dsStack, dsQueue, dsCircularQueue, dsList).build();
        menuBar.getMenus().addAll(audioMenu, saMenu, dsMenu, menu);
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

        Button countingButton = new Button("Counting Sort");
        countingButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!running) {
                    createCountingSortElements();
                    SortingThread mt = new SortingThread(SortingThread.COUNTING_SORT,
                            root, score, countingVector, countingBoxes, sortedVector);
                    running = true;
                    mt.start();
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
//                            createNumberQuestion();
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

                    for (VectorElement element : vector) {
                        animation.getChildren().addAll(element.getStackPane());
                    }

                    Main.variables.setText(Constants.VARIABLES
                            + "capacidade atual = " + vectorCapacity
                            + "    tamanho atual = " + vectorElements.size()
                            + "    tamanho máximo = " + vector.size());


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
                    insertion.setPrefHeight(49);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            tf.setText(Constants.VECTOR_ADD);
                            createVectorNumberQuestion();
                        }
                    });

                    Button removal = new Button("Remoção");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(49);

                    removal.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            System.out.println("To do");
                        }
                    });

                    Button back = new Button("Voltar");
                    back.setPrefWidth(200);
                    back.setPrefHeight(50);
                    back.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                Main.tf.setText(Constants.NO_CODE);
                                Main.variables.setText(Constants.VARIABLES
                                        + Constants.NO_VARIABLES);
                                Main.events.setText(Constants.EVENT
                                        + Constants.LINE_BREAK
                                        + Constants.NO_SIMULATION);
                                removeVector();
                                root.getChildren().remove(vectorFlowPane);
                                for (Node n : root.getChildren()) {

                                    n.setDisable(false);

                                }
                                vectorOn = false;
                            }

                        }
                    });

//                    vectorFlowPane.getChildren().add(initialize);
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

                    score.setAskedQuestions(0);
                    score.setRightAnswers(0);
                    score.setWrongAnswers(0);
                    score.fillSetProgressBar(0);
                    score.setPoints(0);

                    Main.tf.setText(Constants.NO_CODE);
                    Main.events.setText(Constants.EVENT + "\n\n"
                            + Constants.NO_SIMULATION);

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

                    Button initialize = new Button("Top");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);

                    initialize.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                running = true;
                                stackFlowPane.setDisable(true);
                                tf.setText(Constants.STACK_TOP);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + stack.size());
                                StackThread st = new StackThread(animation, stack, score,
                                        main, StackThread.TOP, 0, null);
                                st.start();
                                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                            }
                        }
                    });

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
                            if (!running) {

                                DecimalFormat df = new DecimalFormat("#.#");
                                String points = df.format(score.getPoints()) + "%";
                                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);
//                                score.removeElements();
                                score.fillSetProgressBar(0);

                                Main.tf.setText(Constants.NO_CODE);
                                Main.variables.setText(Constants.VARIABLES
                                        + Constants.NO_VARIABLES);
                                Main.events.setText(Constants.EVENT
                                        + Constants.LINE_BREAK
                                        + Constants.NO_SIMULATION);
                                removeStackElements();
                                root.getChildren().remove(stackFlowPane);
                                for (Node n : root.getChildren()) {

                                    n.setDisable(false);

                                }
                                stackOn = false;
                            }
                        }
                    });

                    stackFlowPane.getChildren().add(insertion);
                    stackFlowPane.getChildren().add(removal);
                    stackFlowPane.getChildren().add(initialize);
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

                    score.setAskedQuestions(0);
                    score.setRightAnswers(0);
                    score.setWrongAnswers(0);
                    score.fillSetProgressBar(0);
                    score.setPoints(0);

                    Main.tf.setText(Constants.NO_CODE);
                    Main.events.setText(Constants.EVENT + "\n\n"
                            + Constants.NO_SIMULATION);

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

                    Button initialize = new Button("Front");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);

                    initialize.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                running = true;
                                queueFlowPane.setDisable(true);
                                tf.setText(Constants.QUEUE_FRONT);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + stack.size());
                                QueueThread st = new QueueThread(animation, queue, score,
                                        main, QueueThread.FRONT, 0, null);
                                st.start();
                                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                            }
                        }
                    });

                    Button insertion = new Button("Enqueue");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                tf.setText(Constants.QUEUE_ENQUEUE);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + queue.size());
                                createQueueNumberQuestion();
                            }
                        }
                    });

                    Button removal = new Button("Dequeue");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(37);

                    removal.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {

                                running = true;
                                queueFlowPane.setDisable(true);
                                tf.setText(Constants.QUEUE_DEQUEUE);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + queue.size());
                                QueueThread st = new QueueThread(animation, queue, score,
                                        main, QueueThread.DEQUEUE, 0, null);
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
                            if (!running) {

                                DecimalFormat df = new DecimalFormat("#.#");
                                String points = df.format(score.getPoints()) + "%";
                                JOptionPane.showMessageDialog(null, "Pontuação:  " + points);
//                                score.removeElements();
                                score.fillSetProgressBar(0);

                                Main.tf.setText(Constants.NO_CODE);
                                Main.variables.setText(Constants.VARIABLES
                                        + Constants.NO_VARIABLES);
                                Main.events.setText(Constants.EVENT
                                        + Constants.LINE_BREAK
                                        + Constants.NO_SIMULATION);
                                removeQueueElements();
                                root.getChildren().remove(queueFlowPane);
                                for (Node n : root.getChildren()) {

                                    n.setDisable(false);

                                }
                                queueOn = false;
                            }
                        }
                    });

                    queueFlowPane.getChildren().add(insertion);
                    queueFlowPane.getChildren().add(removal);
                    queueFlowPane.getChildren().add(initialize);
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
                    insertion.setPrefHeight(49);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            createListNumberQuestion();
                        }
                    });

                    Button removal = new Button("Remover");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(49);

                    removal.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                tf.setText(Constants.LIST_REMOVE);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 5 + "    "
                                        + "tamanho = " + list.size());
                                createListPositionRemovalQuestion();
                            }
                        }
                    });

                    Button back = new Button("Voltar");
                    back.setPrefWidth(200);
                    back.setPrefHeight(50);
                    back.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {

                            DecimalFormat df = new DecimalFormat("#.#");
                            String points = df.format(score.getPoints()) + "%";
                            JOptionPane.showMessageDialog(null, "Pontuação:  " + points);
                            score.fillSetProgressBar(0);

                            Main.tf.setText(Constants.NO_CODE);
                            Main.variables.setText(Constants.VARIABLES
                                    + Constants.NO_VARIABLES);
                            Main.events.setText(Constants.EVENT
                                    + Constants.LINE_BREAK
                                    + Constants.NO_SIMULATION);
                            removeListElements();
                            root.getChildren().remove(listFlowPane);
                            for (Node n : root.getChildren()) {

                                n.setDisable(false);

                            }
                            listOn = false;
                        }
                    });

//                    listFlowPane.getChildren().add(initialize);
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

        Button circularQueueButton = new Button("Fila Circular");
        circularQueueButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                if (!circularListOn && !running) {

                    //========================
                    double x = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.65;

                    circularContainer = new VectorElement[8];
                    circularContainer[0] = new VectorElement(80.0, 80.0, null, 0, x, 80.0, null, false);
                    circularContainer[1] = new VectorElement(80.0, 80.0, null, 0, x + 80.0, 120.0, null, false);
                    circularContainer[2] = new VectorElement(80.0, 80.0, null, 0, x + 120.0, 200.0, null, false);
                    circularContainer[3] = new VectorElement(80.0, 80.0, null, 0, x + 80.0, 280.0, null, false);
                    circularContainer[4] = new VectorElement(80.0, 80.0, null, 0, x, 320.0, null, false);
                    circularContainer[5] = new VectorElement(80.0, 80.0, null, 0, x - 80.0, 280.0, null, false);
                    circularContainer[6] = new VectorElement(80.0, 80.0, null, 0, x - 120.0, 200.0, null, false);
                    circularContainer[7] = new VectorElement(80.0, 80.0, null, 0, x - 80.0, 120.0, null, false);

                    for (int i = 0; i < circularContainer.length; i++) {
                        animation.getChildren().add(circularContainer[i].getStackPane());
                    }

                    head = 0;
                    tail = 0;
                    circularPosition = 0;

                    headElement = new StackElement(40.0, 30.0, "Head", 0, x, 40.0, 1);
                    tailElement = new StackElement(40.0, 30.0, "Tail", 0, x + 40.0, 40.0, 1);

                    animation.getChildren().add(headElement.getStackPane());
                    animation.getChildren().add(tailElement.getStackPane());
                    //========================

                    circularQueueFlowPane = new FlowPane();
                    circularQueueFlowPane.setId("text-f");
                    circularQueueFlowPane.setPrefWidth(200);
                    circularQueueFlowPane.setPrefHeight(195);
                    circularQueueFlowPane.setTranslateX(102);
                    circularQueueFlowPane.setTranslateY(431);
                    Label lb = new Label("    Fila Circular:");
                    lb.setId("sort");
                    lb.setPrefWidth(200);
                    lb.setPrefHeight(40);
                    circularQueueFlowPane.getChildren().add(lb);

                    Button initialize = new Button("Front");
                    initialize.setPrefWidth(200);
                    initialize.setPrefHeight(37);
                    
                    initialize.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {
                                running = true;
                                circularQueueFlowPane.setDisable(true);
                                tf.setText(Constants.CIRCULAR_FRONT);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                                        + "tamanho = " + circularQueue.size());
                                CircularQueueThread st = new CircularQueueThread(animation, circularQueue, score,
                                        main, CircularQueueThread.FRONT, 0, null);
                                st.start();
                                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
                            }
                        }
                    });

                    Button insertion = new Button("Enqueue");
                    insertion.setPrefWidth(200);
                    insertion.setPrefHeight(37);

                    insertion.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {

                            createCircularQueueNumberQuestion();
                        }
                    });

                    Button removal = new Button("Dequeue");
                    removal.setPrefWidth(200);
                    removal.setPrefHeight(37);

                    removal.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            if (!running) {

                                tf.setText(Constants.CIRCULAR_ENQUEUE);
                                variables.setText(Constants.VARIABLES + "capacidade = " + 7 + "    "
                                        + "tamanho = " + circularQueue.size());
                                running = true;
                                circularQueueFlowPane.setDisable(true);

                                CircularQueueThread st = new CircularQueueThread(animation, circularQueue, score,
                                        main, CircularQueueThread.DEQUEUE, 0, null);
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

                            DecimalFormat df = new DecimalFormat("#.#");
                            String points = df.format(score.getPoints()) + "%";
                            JOptionPane.showMessageDialog(null, "Pontuação:  " + points);
                            score.fillSetProgressBar(0);

                            Main.tf.setText(Constants.NO_CODE);
                            Main.variables.setText(Constants.VARIABLES
                                    + Constants.NO_VARIABLES);
                            Main.events.setText(Constants.EVENT
                                    + Constants.LINE_BREAK
                                    + Constants.NO_SIMULATION);
//                            removeListElements();
                            removeCircularQueueElements();
                            for (int i = 0; i < circularContainer.length; i++) {
                                circularContainer[i].getStackPane().setTranslateY(9000);
                            }
                            headElement.getStackPane().setTranslateY(9000);
                            tailElement.getStackPane().setTranslateY(9000);

                            root.getChildren().remove(circularQueueFlowPane);
                            for (Node n : root.getChildren()) {
                                n.setDisable(false);
                            }
                            circularListOn = false;
                        }
                    });

                    circularQueueFlowPane.getChildren().add(initialize);
                    circularQueueFlowPane.getChildren().add(insertion);
                    circularQueueFlowPane.getChildren().add(removal);
                    circularQueueFlowPane.getChildren().add(back);

                    root.getChildren().add(circularQueueFlowPane);

                    for (Node n : root.getChildren()) {
                        if (n != circularQueueFlowPane && n == flowpane) {
                            n.setDisable(true);
                        }
                    }

                    circularListOn = true;

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
        binaryTreeButton.setPrefWidth(401);
        vectorButton.setPrefWidth(401);
        stackButton.setPrefWidth(401);
        listButton.setPrefWidth(401);
        circularQueueButton.setPrefWidth(401);
        queueButton.setPrefWidth(401);

        Button createButton = new Button("Criar teste");
        createButton.setPrefWidth(200);
        createButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                testElement = new NodeElement(30.0, "3", 0, 800, 260);
                root.getChildren().add(testElement.getStackPane());
            }
        });
        Button createButton1 = new Button("Mudar element");
        createButton1.setPrefWidth(200);
        createButton1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                int random = (int) (Math.random() * 100);
                System.out.println("Random: " + random);
                testElement.setElement(random + "");
                testElement.getText().setText(testElement.getElement());
            }
        });

        flowpane = new FlowPane();
        flowpane.maxWidth(450);
        flowpane.setPrefHeight(1000);
        flowpane.setId("flow");
        flowpane.setTranslateY(454);

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
        tilePane1.setPrefRows(6);
        StackPane spn = new StackPane();
        spn.maxWidth(450);
        spn.getChildren().addAll(createButton, createButton1);

        tilePane.getChildren().addAll(button,
                button1, button2, button3, countingButton, button4);
//        tilePane.getChildren().addAll(button,
//                button1, button2, button3, countingButton, button4, createButton, createButton1);

//        tilePane1.getChildren().addAll(binaryTreeButton, vectorButton, stackButton,
//                queueButton, listButton);
        tilePane1.getChildren().addAll(vectorButton, stackButton, queueButton,
                circularQueueButton, listButton);
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
    private FlowPane dialogFlowPane;

    public void createConfirmDialog(String questionText, int correct) {

        boolean result;
        //Tipo pra ver se é sim ou não, ou então de introduzir número.
        events.setText(Constants.EVENT + "\n\n"
                + Constants.TREE_INSERTION);

        //Disable em tudo.
//        for (Node n : root.getChildren()) {
//            if (n != questionPane || n != events || n != variables || n != tf) {
//                n.setDisable(true);
//            }
//        }

        flowpane.setDisable(true);

//        dialogFlowPane.setDisable(true);
        returningNumber = -2;

        questionPane = new FlowPane();
        questionPane.setPrefHeight(250);
        questionPane.setPrefWidth(120);
        questionPane.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        questionPane.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);

        Label question = new Label("Questão");
        question.setId("sort");
        question.setPrefWidth(250);
        question.setPrefHeight(50);

//        numberField = new TextField();
//        numberField.setPrefWidth(250);
//        numberField.setPrefHeight(50);
        Label text = new Label(" " + questionText);
        text.setPrefWidth(250);
        text.setPrefHeight(50);
        text.setId("text-question");


        Button insert = new Button("Sim");
        insert.setPrefWidth(125);
        insert.setPrefHeight(30);
        Button cancel = new Button("Não");
        cancel.setPrefWidth(125);
        cancel.setPrefHeight(30);

        decision = false;
        numberSet = -1;
//        numberField.setVisible(false);

        insert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
//                    if (Integer.parseInt(numberField.getText()) != -1) {
//
//                        int number = Integer.parseInt(numberField.getText());
//
//                        StackElement se = new StackElement(100, 50, Integer.toString(number),
//                                1, 457, 100);
//
//                        se.getStackPane().setVisible(false);
//                        animation.getChildren().add(se.getStackPane());
//                        StackThread st = new StackThread(animation, stack, score,
//                                main, StackThread.INSERTION, number, se);
//                        running = true;
//                        root.getChildren().remove(questionPane);
//                        st.start();
//                        events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
//
//                    } else {
//                        events.setText(Constants.EVENT + "\n\n" + Constants.INVALID_NUMBER);
//                        hitButton = false;
//                    }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
//                root.getChildren().remove(questionPane);
//                stackFlowPane.setDisable(false);
//                events.setText(Constants.EVENT + "\n\n" + Constants.NO_SIMULATION);
//                tf.setText(Constants.NO_CODE);
            }
        });

        questionPane.getChildren().addAll(question, text, insert, cancel);
        root.getChildren().add(questionPane);

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

    public FlowPane getBinaryTreeFlowPane() {
        return binaryTreeFlowPane;
    }

    public void setBinaryTreeFlowPane(FlowPane binaryTreeFlowPane) {
        this.binaryTreeFlowPane = binaryTreeFlowPane;
    }

    public FlowPane getListFlowPane() {
        return listFlowPane;
    }

    public void setListFlowPane(FlowPane listFlowPane) {
        this.listFlowPane = listFlowPane;
    }

    public FlowPane getQueueFlowPane() {
        return queueFlowPane;
    }

    public void setQueueFlowPane(FlowPane queueFlowPane) {
        this.queueFlowPane = queueFlowPane;
    }

    public FlowPane getVectorFlowPane() {
        return vectorFlowPane;
    }

    public void setVectorFlowPane(FlowPane vectorFlowPane) {
        this.vectorFlowPane = vectorFlowPane;
    }

    public List<QueueElement> getQueue() {
        return queue;
    }

    public void setQueue(List<QueueElement> queue) {
        this.queue = queue;
    }

    public List<ListElement> getList() {
        return list;
    }

    public void setList(List<ListElement> list) {
        this.list = list;
    }

    public List<VectorElement> getVector() {
        return vector;
    }

    public void setVector(List<VectorElement> vector) {
        this.vector = vector;
    }

    public List<NodeElement> getVectorElements() {
        return vectorElements;
    }

    public void setVectorElements(List<NodeElement> vectorElements) {
        this.vectorElements = vectorElements;
    }

    public VectorElement[] getCircularContainer() {
        return circularContainer;
    }

    public void setCircularContainer(VectorElement[] circularContainer) {
        this.circularContainer = circularContainer;
    }

    public List<NodeElement> getCircularQueue() {
        return circularQueue;
    }

    public void setCircularQueue(List<NodeElement> circularQueue) {
        this.circularQueue = circularQueue;
    }

    public FlowPane getCircularQueueFlowPane() {
        return circularQueueFlowPane;
    }

    public void setCircularQueueFlowPane(FlowPane circularQueueFlowPane) {
        this.circularQueueFlowPane = circularQueueFlowPane;
    }
}
