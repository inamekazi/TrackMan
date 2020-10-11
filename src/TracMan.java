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
import java.util.Random;

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

        generateNewAdBlock();

        TrackerAd trackerAd = new TrackerAd();
        GraphicsGroup graphics = trackerAd.getGraphics();
        graphics.setPosition(80.0 , 80.0  );
        canvas.add(graphics);
        canvas.animate(()->run(trackerAd));
//        canvas.add(trackerAd);
    }

    public void generateNewAdBlock(){
        Random r = new Random();
        double randomX = 0 + (CANVAS_WIDTH - 0) * r.nextDouble();
        double randomY = 0 + (CANVAS_HEIGHT - 0) * r.nextDouble();
        AdBlock adBlock= new AdBlock(randomX,randomY,10,10);
        canvas.add(adBlock);
    }

    public void moveAdBlock(AdBlock adBlock, int CANVAS_WIDTH, int CANVAS_HEIGHT){
        // if gotten by pac, delete current and add new block
        adBlock.setNewPosition(CANVAS_WIDTH, CANVAS_HEIGHT);
        // elif 10 seconds have passed, delete current block and add new one

    }

    public void run(trackerBody testCritter) {
        testCritter.setSpeed(300);
        testCritter.setGoal(new Point(
                pac.getCenter().getX() , // + Math.cos(t) * 5,
                pac.getCenter().getY() )); // Math.cos(t) * 5 + 5));
        testCritter.moveTowardsGoal(0.05);

        canvas.pause(50);
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