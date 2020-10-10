import comp127graphics.*;

import java.awt.*;
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
    private long startTime = System.currentTimeMillis();
    private GraphicsText timeLived;

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

//        Image mario = new Image(10,10,"chrome.jpg");
        Image browser = new Image(0,0,"1600.jpg");

//        canvas.add(mario);
        canvas.add(browser);
        canvas.add(pac);
        defenseManager = new DefenseManager(canvas);
        defenseManager.generateRandomDefense();

        timeLived = new GraphicsText("Time live: 0", 30, 50);
        canvas.add(timeLived);

        canvas.animate(()-> generateTimeText());

        TrackerAd trackerAd = new TrackerAd();
        TrackerSiteAnalytics trackerSA = new TrackerSiteAnalytics();

        GraphicsGroup graphics = trackerAd.getGraphics();
        graphics.setPosition(80.0, 80.0);
        graphics.setAllType(trackerAd);

        GraphicsGroup graphicsSA = trackerSA.getGraphics();
        graphicsSA.setPosition(180.0, 180.0);
        graphicsSA.setAllType(trackerSA);

//        canvas.add(graphics);
//        canvas.add(graphicsSA);
        canvas.add(new Ellipse(graphics.getX()+40, graphics.getY()+40, 1,1));

//        canvas.animate(() -> trackPac(trackerAd, 30));
//
//
//        canvas.animate(() -> trackPac(trackerSA, 30));
        canvas.animate(() -> ifInterestWithPac(pac));
        canvas.pause(1);
        updatePacPosinTrac(pac);
    }

    /**
     * Given a tracker, the tracker will follow PAC at the given speed specified in the second Parameter.
     */
    public void trackPac(trackerBody testCritter, double speed) {
        testCritter.setSpeed(speed);
        testCritter.setGoal(new Point(
                pac.getCenter().getX() , // + Math.cos(t) * 5,
                pac.getCenter().getY() )); // Math.cos(t) * 5 + 5));
        testCritter.moveTowardsGoal(0.05);
        canvas.pause(20);
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
//            generateLossScreen();

        }
        pac.collideswithDefense(defenseManager);

    }
    private void afterCollision(){
        canvas.pause(10);
        Rectangle popUp = new Rectangle(200,200,100,100);
        popUp.setFillColor(PopUpColor);
        popUp.setFilled(true);
        canvas.add(popUp);
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
        this.canvas.animate(() -> canvas.onKeyDown(event -> pac.updatePacPositionByKey(event.getKey())));
//        canvas.onMouseMove(event -> pac.updatePacPosition(event.getPosition()));
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



}