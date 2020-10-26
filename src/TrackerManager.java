import comp127graphics.CanvasWindow;
import comp127graphics.Point;
import java.util.*;
import java.util.List;

/**
 * This class manages all the trackers on the screen. It performs operations such as slowing down trackers,
 * reactivating trackers, etc.
 *
 * @author Lu Li
 * */

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
    public static void sleep(int i) {
        Random rand = new Random();
        if (i == 0){
            for (trackerBody testCritter: trackers.keySet()){
                trackers.put(testCritter, 0);
            }
        }
        else{
            for (trackerBody testCritter: trackers.keySet()){
                trackers.put(testCritter, trackers.get(testCritter) / 10 + 5 + rand.nextInt(20) );
            }
        }
    }
    public static void reactivate() {
        Random rand = new Random();
        for (trackerBody testCritter: trackers.keySet()){
            trackers.put(testCritter, trackers.get(testCritter) * 10 + 10+rand.nextInt(100));
        }
    }
}
