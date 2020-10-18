import comp127graphics.*;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

import comp127graphics.Image;
import comp127graphics.Point;
import comp127graphics.Rectangle;

public class TracMan {
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = CANVAS_WIDTH / 2 * 3;
    private static final Color CanvasColor = new Color(54, 54, 255, 205);
    private static final Color PopUpColor = new Color(183, 255, 252, 205);
    private Pac pac;
    private GraphicsText livesMessage;
    private double numberOfLives = 4;
    private GraphicsText lossMessage;
    private double score = 0;
    public CanvasWindow canvas;
    public Rectangle popUp;
    private DefenseManager defenseManager;
    private IncentiveManager incentiveManager;
    private TrackerManager trackerManager;
    private long startTime = System.currentTimeMillis();
    private GraphicsText timeLived;
    private GraphicsText scoreDisplay;
    private Image chrome = new Image(10,10,"chromenew.png", 100);
    private Image safari = new Image(10,10,"safarinew.png", 100);
    private Image firefox = new Image(10,10,"firefox.png", 100);
    private Image[] browsers = {chrome, safari, firefox};
    private Image curIm;
    private int curMinute = 0;
    public static void main(String[] args) {
        TracMan tracMan = new TracMan();
        tracMan.runGame();
    }

    public TracMan() {
        canvas = new CanvasWindow("TrackGame", CANVAS_WIDTH,CANVAS_HEIGHT);
        canvas.setBackground(CanvasColor);
    }

    public void generatePac() {
        double speed = 0.6;
        pac = new Pac(CANVAS_WIDTH - Pac.getPacRadius() * 2, CANVAS_HEIGHT - Pac.getPacRadius() * 2, speed, canvas);

        canvas.add(chrome);
        curIm = chrome;
        canvas.add(pac);
        defenseManager = new DefenseManager(canvas);
        defenseManager.generateRandomDefense();
        incentiveManager = new IncentiveManager(canvas);
        incentiveManager.generateRandomIncentive();
        trackerManager = new TrackerManager();

        timeLived = new GraphicsText("Time live: 0", 30, 50);
        scoreDisplay = new GraphicsText("Score is: 0", 300, 50);
        canvas.add(timeLived);
        canvas.add(scoreDisplay);

        canvas.animate(()-> generateTimeText());

        TrackerAd trackerAd = new TrackerAd();
        TrackerSiteAnalytics trackerSA = new TrackerSiteAnalytics();

        GraphicsGroup graphics = trackerAd.getGraphics();
        graphics.setPosition(30.0, 30.0);
        graphics.setAllType(trackerAd);

        GraphicsGroup graphicsSA = trackerSA.getGraphics();
        graphicsSA.setPosition(580.0, 580.0);
        graphicsSA.setAllType(trackerSA);

        canvas.add(graphics);
        canvas.add(graphicsSA);
        canvas.add(new Ellipse(graphics.getX()+40, graphics.getY()+40, 1,1));
        trackerManager.addTracker(trackerAd, 100);
        trackerManager.addTracker(trackerSA, 130);
        canvas.animate(() -> TrackerManager.trackPac(pac, canvas));
        canvas.animate(() -> ifInterestWithPac(pac));
        canvas.animate(() -> updateBgPic());
        canvas.pause(1);
        updatePacPosinTrac(pac);
    }



    private void updateBgPic(){
        long[] times = getTimeLived();
        if (times[1] - curMinute > 20){

            curMinute += 20;
            canvas.remove(curIm);
            Image newIm = browsers[new Random().nextInt(3)];
            while(newIm == curIm){
                newIm = browsers[new Random().nextInt(3)];
            }
            curIm = newIm;
            canvas.add(newIm);
            Rectangle alert = new Rectangle(CANVAS_WIDTH/4, CANVAS_HEIGHT/6, CANVAS_WIDTH/2, CANVAS_HEIGHT/3);
            alert.setFilled(true);
            alert.setFillColor(new Color(20,40,100, 30));
            GraphicsText text = new GraphicsText();
            text.setPosition(CANVAS_WIDTH/4, CANVAS_HEIGHT/6);
            text.setText("You are now switched to a new browser! All your previous ad blockers are gone!");
            TrackerManager.reactivate();
            canvas.add(text);
            text.setFontSize(15);
            canvas.add(alert);
            canvas.draw();
            canvas.pause(3000);
            canvas.remove(text);
            canvas.remove(alert);
        }
    }

    /**
     * Checks if Pac touched any object in the Canvas and updates the
     * game based on who it touched.
     */
    protected void ifInterestWithPac(Pac pac){
        if(pac.collideswithAdvertisement()){
            afterCollision();
        }
        if (pac.collideswithAnalytics()){
            afterCollision();
        }
        if(pac.collideswithAnalytics() && numberOfLives ==0 ||
                (pac.collideswithAdvertisement() && numberOfLives ==0) ){
            generatePopUp();
        }
        if(pac.collideswithDefense(defenseManager)){
            System.out.println("here");
            trackerManager.sleep();
        }

        pac.collideWithIncentive(incentiveManager);
        if(pac.collideWithIncentive(incentiveManager)){
            increaseScore(5);
        }

    }

    private void afterCollision(){
        canvas.pause(10);
        Rectangle popUp = new Rectangle(200,200,100,100);
        popUp.setFillColor(PopUpColor);
        popUp.setFilled(true);

        canvas.add(popUp);
        canvas.draw();

        canvas.pause(10);
        canvas.pause(2000);
        canvas.remove(livesMessage);
        this.numberOfLives--;
        generateLivesText();
    }
    /**
     * Given an object of type Pac, this function will update the position of Pac
     * depending on the keys Pressed in keyboard (W,A,S,D and the arrow keys).
     */
    public void updatePacPosinTrac(Pac pac){
        canvas.onMouseMove(event -> pac.updatePacPosition(event.getPosition()));
    }

    /**
     * denerates the main objects of the Game TrackPac
     */
    public void beginGame() {
        generatePac();
        generateLivesText();
    }

    /**
     * Removes every object in canvas
     */
    public void resetGame() {
        canvas.removeAll();
    }

    /**
     * This Method Initializes the Game!
     */
    public void runGame(){
        resetGame();
        beginGame();
    }

    public void generateLoseMessage(){
        lossMessage = new GraphicsText("Game Over! Your Score is:" + score, CANVAS_WIDTH/2,CANVAS_HEIGHT/2 );
        lossMessage.setFontSize(10);
        canvas.add(lossMessage);
    }

    public void generateLivesText(){
        livesMessage = new GraphicsText("Lives remaining: " + numberOfLives,  CANVAS_WIDTH/2, 15);
        livesMessage.setFontSize(15);
        canvas.add(livesMessage);
    }

    /**
     * This method creates the screen a player sees once all their lives are taken away
     */
    public void generateLossScreen(){
        canvas.removeAll();
        generateLoseMessage();
    }

    public double calculateScore(){
        return 1.1;
    }

    public void generateScoreMessage(){
    }
    public long[] getTimeLived(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60 ;
        long elapsedMinutes = elapsedSeconds / 60;

        long[] ans = new long[2];
        ans[0] = elapsedMinutes;
        ans[1] = secondsDisplay;
        return ans;
    }


    public void generateTimeText(){
        long[] times = getTimeLived();
        timeLived.setFontSize(20);
        timeLived.setText("Time Lived is:" + times[0] + ":" + times[1]);
    }

    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public void generatePopUp(){
        popUp = new Rectangle(200,200,100,100);
        popUp.setFillColor(PopUpColor);
        popUp.setFilled(true);
        canvas.add(popUp);
    }

    /***
     * increase the score if hits a surfing incentive
     * @param increment
     */
    private void increaseScore(double increment){
        score += increment;
        scoreDisplay.setFontSize(25);
        scoreDisplay.setText("Score is:" + score);
    }



}