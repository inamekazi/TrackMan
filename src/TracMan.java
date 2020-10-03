import comp127graphics.CanvasWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import comp127graphics.GraphicsGroup;
import comp127graphics.Point;
import comp127graphics.events.Key;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

public class TracMan {
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = CANVAS_WIDTH / 2 * 3;

    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    private static final Color CanvasColor = new Color(54, 54, 255, 205);
    private Pac pac;
    public CanvasWindow canvas;

    public static void main(String[] args) {
        TracMan tracMan = new TracMan();
    }

    public TracMan() {
        canvas = new CanvasWindow("TrackGame", CANVAS_WIDTH,CANVAS_HEIGHT);
        canvas.setBackground(CanvasColor);
        generatePac();
        updatePacPosinTrac(pac);
    }
    public void generatePac() {
        double speed = 1;
        pac = new Pac(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, speed);
        canvas.add(pac);

        TrackerAd trackerAd = new TrackerAd();
        GraphicsGroup graphics = trackerAd.getGraphics();
        graphics.setPosition(40.0 + trackerAd.getxOffset(), 40.0 + trackerAd.getyOffset());
        canvas.add(graphics);
        run(trackerAd);
//        canvas.add(trackerAd);
    }
    @SuppressWarnings("InfiniteLoopStatement")
    public void run(trackerBody testCritter) {
        testCritter.setSpeed(10);
        Point center = testCritter.getGraphics().getPosition();
        double t = 0;
        while(true) {
            testCritter.setGoal(new Point2D.Double(
                    center.getX() + Math.cos(t) * 5 + 5,
                    center.getY() + Math.sin(t) * 5 + 5));
            testCritter.moveTowardsGoal(0.05);

            canvas.pause(50);
            t = (t + 0.1) % (Math.PI*2);
        }
    }

    public void run() {
    }

    public void beginGame() {
    }

    public void resetGame() {
    }

    public void updatePacPosinTrac(Pac pac){
        this.canvas.animate(() -> canvas.onKeyDown(event -> pac.updatePacPositionByKey(event.getKey())));
//        canvas.onMouseMove(event -> pac.updatePacPosition(event.getPosition()));
    }
}