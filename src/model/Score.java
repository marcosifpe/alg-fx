/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import execution.Main;
import java.text.DecimalFormat;

/**
 *
 * @author rafael
 */
public class Score {
    
    private Main main;
    private int askedQuestions;
    private int rightAnswers;
    private int wrongAnswers;
    private double points;
    private double progress;

    public Score(Main main) {
        
        this.main = main;
        this.askedQuestions = 0;
        this.rightAnswers = 0;
        this.wrongAnswers = 0;
        this.points = 0.0;
        this.progress = 0.0;
    }
    
    public void selectText(String code) {
        this.main.selectText(code);
    }
    
    public void createElements() {
        this.main.createElements();
    }
    
    public void removeElements() {
        this.main.removeElements();
    }
    
    public void createMainProgressBar() {
        this.main.createProgressBar();
    }
    
    public void fillSetProgressBar(double percentage) {
        
        System.out.println("Percent: " + percentage / 100.0);
        
        main.getPointProgressBar().setProgress(percentage / 100.0);
        
        System.out.println("Progress: " + main.getPointProgressBar().getProgress());
        System.out.println("Points: " + getPoints());
        
//        DecimalFormat df = new DecimalFormat("#.#");
//        main.getScoreLabel().setText("  aa" + df.format(getPoints()) + "%");
//        main.getScoreLabel().setText("  aa%");
        
    }
    
    public void fillProgressBar(double percentage) {
        
        if (main.getPointProgressBar().getProgress() + percentage >= 1.0) {
            
            main.getPointProgressBar().setProgress(1.0);
            
        } else {
            
            main.getPointProgressBar().setProgress((main.getPointProgressBar().getProgress() + percentage));
        
        }
        
        DecimalFormat df = new DecimalFormat("#.#");
        main.getScoreLabel().setText("  " + df.format(main.getPointProgressBar().getProgress() * 100.0) + "%");
        
    }

    public int getAskedQuestions() {
        return askedQuestions;
        
    }

    public void setAskedQuestions(int askedQuestions) {
        this.askedQuestions = askedQuestions;
    }

    public double getPoints() {
        points = (double) (((double) rightAnswers / (double) askedQuestions) * 100);
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(int wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
    
    public void addRightAnswer() {
        this.rightAnswers++;
    }
    
    public void addWrongAnswer() {
        this.wrongAnswers++;
    }
    
    public void addTotal() {
        this.askedQuestions++;
    }
}