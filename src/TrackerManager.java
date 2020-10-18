import comp127graphics.CanvasWindow;
import comp127graphics.Point;

import java.util.*;

public class TrackerManager {
    static Map<trackerBody, Integer> trackers = new HashMap<>();
    /**
     * Given a tracker, the tracker will follow PAC at the given speed specified in the second Parameter.
     */
    public TrackerManager(){
    }
    public static void trackPac(Pac pac, CanvasWindow canvas) {
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
        for (trackerBody testCritter: trackers.keySet()){
            trackers.put(testCritter, trackers.get(testCritter) / 10);
        }
    }
    public static void reactivate() {
        Random rand = new Random();
        int slowATracker = rand.nextInt(trackers.size());
        int i = 0;
        for (trackerBody testCritter: trackers.keySet()){
            if (i == slowATracker){
                trackers.put(testCritter, trackers.get(testCritter) * 10 + 100);
            }
            i ++;
        }
    }
}
