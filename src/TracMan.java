import comp127graphics.CanvasWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TracMan {

    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = CANVAS_WIDTH / 2 * 3;
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
    }

    public void generatePac() {
        pac = new Pac(canvas,CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
    }

    public void run() {
    }

    public void beginGame() {
    }

    public void resetGame() {
    }

}