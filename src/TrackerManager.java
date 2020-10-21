import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsText;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TrackerManager {
    static Map<trackerBody, Integer> trackers = new HashMap<>();
    private static CanvasWindow canvas;
    static List<DefenseMessage> messages = new ArrayList<>();
    private static final String[] MESSAGE = {
            "Customer Interaction Tracker (CIT) is a software and/or process of gathering information about customers interactions against all levels throughout a business. A CIT does not only track customers who have actually bought a product or service, but also keeps track of future prospects and how they interact with sales organisations. ",
            "Web analytics are oftentimes under the essential tracker branch, meaning they are deemed important enough for a website to function properly. Web analytics trackers, such as commonly used Google Analytics, gather information from users such as (but not limited to): Ip addresses, the type of device a user is using, search history, location and demographics, ad views, cookie data, etc. ",
            "Essential trackers are trackers that are necessary for a website to function correctly and perform its basic functions as intended. Essential trackers can collect vast amounts of information from users for the purpose of running a website. Essential trackers can collect information such as, but not limited to dd views, browser and cookie information, internet service, page views, IP addresses, search history and login information. "};

    /**
     * Given a tracker, the tracker will follow PAC at the given speed specified in the second Parameter.
     */
    public TrackerManager(){
    }
    public static void setDescription() {
        for (String description: MESSAGE){
            messages.add(new DefenseMessage(false, description));
        }
    }
    public static void trackPac(Pac pac, CanvasWindow canvasW) {
        canvas = canvasW;
        for (trackerBody testCritter: trackers.keySet()){
            testCritter.setSpeed(trackers.get(testCritter));
            testCritter.setGoal(new Point(
                    pac.getCenter().getX(), // + Math.cos(t) * 5,
                    pac.getCenter().getY())); // Math.cos(t) * 5 + 5));
            testCritter.moveTowardsGoal(0.05);
        }
    }

    public void addTracker(trackerBody tracker, int speed){
        this.trackers.put(tracker, speed);
    }
    public static void sleep() {
        Random rand = new Random();
        for (trackerBody testCritter: trackers.keySet()){
            trackers.put(testCritter, trackers.get(testCritter) / 10 + 5 + rand.nextInt(20) );
        }
    }
    public static void reactivate() {
        Random rand = new Random();
        for (trackerBody testCritter: trackers.keySet()){
            trackers.put(testCritter, trackers.get(testCritter) * 10 + 10+rand.nextInt(100));
        }
    }

    public static void showMessage(int wordsPerLine, Color BoxColor, String description){
        Random rand = new Random();
        boolean shownBefore = false;
        int id = rand.nextInt(messages.size());
        if (description.length() == 0){
            description = messages.get(id).message;
            shownBefore = messages.get(id).shown;
            messages.get(id).shown = true;
        }
        else{
            shownBefore = true;
        }
        String[] myArray = description.split(" ");
        List<String> al = new ArrayList<>();
        al = Arrays.asList(myArray);
        int numOfLines = al.size() / wordsPerLine + 1;
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
        int lineWidth = 25;
        double x = TracMan.getCanvasWidth()/4;
        double y = TracMan.getCanvasHeight()/4;
        comp127graphics.Rectangle rectangle = new Rectangle(x, y, wordsPerLine * 60 + 20,lineWidth * numOfLines + 30);
        rectangle.setFilled(true);
        rectangle.setFillColor(BoxColor);
        canvas.add(rectangle);
        GraphicsText[] texts = new GraphicsText[numOfLines];
        for (int i = 0; i < numOfLines; i++){
            texts[i] = new GraphicsText(lines[i], x + 20,y + i * lineWidth + 30);
            texts[i].setFontSize(16);
            canvas.add(texts[i]);
        }
        canvas.draw();
        if (shownBefore){
            canvas.pause(5000);
        }
        else{
            canvas.pause(11111);
        }

        for (GraphicsText text: texts) {
            canvas.remove(text);
        }
        canvas.remove(rectangle);
    }
}
