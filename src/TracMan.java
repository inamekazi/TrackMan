import comp127graphics.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import comp127graphics.Image;
import comp127graphics.Rectangle;

/**
 * This is the main interface for our game: TrackMan - an educational game on online trackers and
 * available tools to protect oneself from being tracked.
 *
 * @author Lu Li, Alejandro Aguilar.
 * */

public class TracMan {
    private static final int CANVAS_WIDTH = 1300;
    private static final int CANVAS_HEIGHT = CANVAS_WIDTH / 2;
    private static final Color CanvasColor = new Color(54, 54, 255, 205);
    private static final Color PopUpColor = new Color(183, 255, 252, 205);
    private Pac pac;
    private static Color BoxColor = new Color(255, 130, 101, 199);

    private GraphicsText livesMessage;
    private double numberOfLives = 4;
    private GraphicsText lossMessage;
    private static double score = 0;
    public CanvasWindow canvas;
    public Rectangle popUp;
    private DefenseManager defenseManager;
    private IncentiveManager incentiveManager;
    private TrackerManager trackerManager;
    private long startTime = System.currentTimeMillis();
    private GraphicsText timeLived;
    private static GraphicsText scoreDisplay;
    private Image chrome = new Image(10,10,"chromenew.png", 100);
    private Image safari = new Image(10,10,"safarinew.png", 100);
    private Image firefox = new Image(10,10,"firefox.png", 100);
    private Image beginImage = new Image(CANVAS_WIDTH/3,CANVAS_HEIGHT/8,"beginScreen.jpg", CANVAS_WIDTH /3);
    private Image[] browsers = {chrome, safari, firefox};
    private Image curIm;
    private int curMinute = 0;
    private static final String[] MESSAGE = {
            "Customer Interaction Tracker (CIT) is a software and/or process of gathering information about customers interactions against all levels throughout a business. A CIT does not only track customers who have actually bought a product or service, but also keeps track of future prospects and how they interact with sales organisations. ",
            "Web analytics are oftentimes under the essential tracker branch, meaning they are deemed important enough for a website to function properly. Web analytics trackers, such as commonly used Google Analytics, gather information from users such as (but not limited to): Ip addresses, the type of device a user is using, search history, location and demographics, ad views, cookie data, etc. ",
            "Ad-trackers are used to collects data  relating  to  a  user’s  browsing,  such  as  the  products  they  have viewed, put in their shopping cart and purchased. For each navigation event, they also collect the data and time of the event, the URL of the page or the name of the application on which the event took place.  These data will be used for recommendation algorithms to provide ads that are better tailored to a user’s interests."};

    public static void main(String[] args) {
        TracMan tracMan = new TracMan();
        tracMan.runGame();
    }

    public TracMan() {
        canvas = new CanvasWindow("TrackGame", CANVAS_WIDTH,CANVAS_HEIGHT);
        canvas.setBackground(CanvasColor);

    }
    private void showIntroPic(){
        formatString(5,
                "Welcome to TracMan. In this game,");
    }

    public void generatePac() {
        double speed = 0.6;
        pac = new Pac(CANVAS_WIDTH - Pac.getPacRadius() * 2, CANVAS_HEIGHT - Pac.getPacRadius() * 2, speed, canvas);
        canvas.add(chrome);
        curIm = chrome;
        canvas.add(pac);
        InformationManager.setCanvasWindow(canvas);
        defenseManager = new DefenseManager(canvas);
        defenseManager.generateRandomDefense();

        incentiveManager = new IncentiveManager(canvas);
        incentiveManager.generateRandomIncentive();
        trackerManager = new TrackerManager();
        TrackerManager.setDescription();

        timeLived = new GraphicsText("Time live: 0", 150, 50);
        scoreDisplay = new GraphicsText("Score is: 0", 350, 50);
        scoreDisplay.setFontSize(20);
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
        canvas.add(new Ellipse(graphics.getX() + 40, graphics.getY() + 40, 1,1));
        trackerManager.addTracker(trackerAd, 100);
        trackerManager.addTracker(trackerSA, 200);
        canvas.animate(() -> TrackerManager.trackPac(pac, canvas));
        canvas.animate(() -> ifInterestWithPac(pac));
        canvas.animate(() -> updateBgPic());
        canvas.pause(1);
        updatePacPosinTrac(pac);
    }



    private void updateBgPic(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;

        if (elapsedSeconds - curMinute > 40){
            curMinute += 40;
            canvas.remove(curIm);
            Image newIm = browsers[new Random().nextInt(3)];
            while(newIm == curIm){
                newIm = browsers[new Random().nextInt(3)];
            }
            curIm = newIm;
            canvas.add(newIm);
            Color BoxColor = new Color(255, 214, 87, 194);
            Defenses.showMessage(13, BoxColor, "You are now switched to a new browser! All your previous ad blockers are gone!");
            TrackerManager.reactivate();
        }
    }

    /**
     * Checks if Pac touched any object in the Canvas and updates the
     * game based on who it touched.
     */
    protected void ifInterestWithPac(Pac pac){
        if(pac.collideswithAdvertisement()){
            Random rand = new Random();
            int i = rand.nextInt(MESSAGE.length);
            afterCollision(i);
        }
        if (pac.collideswithAnalytics()){
            Random rand = new Random();
            int i = rand.nextInt(MESSAGE.length);
            afterCollision(i);
        }
        if(pac.collideswithAnalytics() && numberOfLives ==0 ||
                (pac.collideswithAdvertisement() && numberOfLives ==0) ){
            generatePopUp();
            generateLoseMessage();
        }
        pac.collideswithDefense(defenseManager, trackerManager);
        if(pac.collideWithIncentive(incentiveManager)){
            increaseScore(5);
        }

    }

    private void afterCollision(int i){
        InformationManager.generateInformationMessage(i);
        Defenses.showMessage(13, BoxColor, MESSAGE[i]);

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
//        canvas.add(beginImage);
//        canvas.draw();
//        canvas.pause(20000);
//        canvas.remove(beginImage);
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
        lossMessage = new GraphicsText("Game Over! Your Score is:" + score, CANVAS_WIDTH/4,CANVAS_HEIGHT/4);
        List<String> lost = InformationManager.lostInfoSofar;
        lossMessage.setFontSize(30);
        canvas.removeAll();
        int i = 0;
        GraphicsText intro = new GraphicsText("Information of you that has been collected:", CANVAS_WIDTH/4 ,CANVAS_HEIGHT/3 - 20);
        canvas.add(intro);
        for (String text : lost){
            GraphicsText lostInfo = new GraphicsText(text, CANVAS_WIDTH/4 ,CANVAS_HEIGHT/3 + i);
            i += 20;
            canvas.add(lostInfo);
        }

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
    protected static void increaseScore(double increment){
        score += increment;
        scoreDisplay.setFontSize(25);
        scoreDisplay.setText("Score is:" + score);
    }
    public void formatString(int wordsPerLine,String description){
        Rectangle rectangle = new Rectangle(20,80,650,80);
        String[] myArray = description.split(" ");
        List<String> al = Arrays.asList(myArray);
        int numOfLines = al.size() / wordsPerLine;
        String[] lines = new String[numOfLines];
        for(int i = 0; i < numOfLines; i++){
            String curLine = "";
            for (int j = 0; j < wordsPerLine; j ++){
                int curIndex = i * wordsPerLine + j;
                if (curIndex < myArray.length){
                    curLine = curLine.concat(" "+ myArray[i * wordsPerLine + j]);
                }
            }
            lines[i] = curLine;
        }
        int lineWidth = 15;
        rectangle.setFilled(true);
        rectangle.setFillColor(BoxColor);
        canvas.add(rectangle);
        GraphicsText[] texts = new GraphicsText[numOfLines];
        for (int i = 0; i < numOfLines; i++){
            texts[i] = new GraphicsText(lines[i], 20,100 + i * lineWidth);
            texts[i].setFontSize(16);
            canvas.add(texts[i]);
        }
        canvas.draw();
        canvas.pause(11111);
        for (GraphicsText text: texts) {
            canvas.remove(text);
        }
        canvas.remove(rectangle);
    }
}