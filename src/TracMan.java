import comp127graphics.*;
import comp127graphics.CanvasWindow;

import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TracMan extends CanvasWindow implements  MouseListener, MouseMotionListener{

    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = CANVAS_WIDTH/2 *3;
    private static final Color CanvasColor = new Color(54, 54, 255, 205);

    public static void main(String[] args) {
        TracMan prog = new TracMan();
    }

    public TracMan(){
        super("TrackMan!",CANVAS_WIDTH,CANVAS_HEIGHT );
        this.setBackground(CanvasColor);
    }

    public void run(){

    }

    public void beginGame(){

    }
    public void resetGame(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
