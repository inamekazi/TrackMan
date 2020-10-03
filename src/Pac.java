import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import comp127graphics.Ellipse;
import comp127graphics.Point;
import comp127graphics.events.Key;

import java.awt.*;


public class Pac extends Ellipse implements Runnable{

    private static final Color PAC_COLOR = new Color(255, 244, 39, 250);
    public double centerX;
    public double centerY;
    public double width;
    public double speed;
    public double height;
    private static final double PAC_RADIUS = 10;

    public Pac( double centerX, double centerY, double speed){
        super(centerX- PAC_RADIUS, centerY - PAC_RADIUS, PAC_RADIUS*2, PAC_RADIUS*2);
        this.centerX = centerX;
        this.centerY = centerY;
        this.setFillColor(PAC_COLOR);
        this.setFilled(true);
        this.speed = speed;
    }

    @Override
    public void run() {
        this.setPosition(this.getCenterX(), getCenterY() - speed);
    }

    public void updatePacPosition(Point mouse){
        double mouseX = mouse.getX();
        double mouseY = mouse.getY();
        this.setCenter(mouseX,mouseY);
    }
    /**
    * Updates the position of the pac based on the key the user pressed.
    */
    public void updatePacPositionByKey(Key key) {
        double speed = this.getSpeed();
        if (key.toString().equalsIgnoreCase("UP_ARROW") || key.toString().equalsIgnoreCase("W")) {
            if (!hitUpperBoundary()) {
                this.setPosition(this.getCenterX(), getCenterY() - speed);
            }
        }
        if (key.toString().equalsIgnoreCase("RIGHT_ARROW") || key.toString().equalsIgnoreCase("D")) {
            if(!hitRightBoundary()){
                this.setPosition(this.getCenterX() + speed, getCenterY());
            }
        }
        if (key.toString().equalsIgnoreCase("LEFT_ARROW") || key.toString().equalsIgnoreCase("A")) {
            if(!hitLeftBoundary()){
                this.setPosition(this.getCenterX() - speed, getCenterY());
            }
        }
        if (key.toString().equalsIgnoreCase("DOWN_ARROW") || key.toString().equalsIgnoreCase("S")) {
            if(!hitLowerBoundary()){
                this.setPosition(this.getCenterX(), getCenterY() + speed);
            }
        }
    }

    /**
     * Return true if the ball has hit the upper boundary.
     */
    private boolean hitUpperBoundary(){
        return !(getCenter().getY() - PAC_RADIUS > Boundary.upperY);
    }
    /**
     * Return true if the ball has hit the lower boundary.
     */
    private boolean hitLowerBoundary(){
        return !(getCenter().getY() + PAC_RADIUS < Boundary.lowerY);
    }
    /**
     * Return true if the ball has hit the left boundary.
     */
    private boolean hitLeftBoundary(){
        return !(getCenter().getX() - PAC_RADIUS > Boundary.leftX);
    }
    /**
     * Return true if the ball has hit the right boundary.
     */
    private boolean hitRightBoundary(){
        return !(getCenter().getX() + PAC_RADIUS < Boundary.rightX);
    }

    /**
    * Gets the speed of the ball.
    */
    public double getSpeed() {
        return speed;
    }

    public double getCenterX(){
        return this.getPosition().getX();
    }

    public double getCenterY(){
        return this.getPosition().getY();
    }

}
