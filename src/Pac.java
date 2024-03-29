import comp127graphics.*;
import comp127graphics.Point;
import comp127graphics.events.Key;
import java.awt.*;
import java.util.Map;

/**
 * This class represents the user in the TrackMan game.
 * @author Lu Li, Alejandro Aguilar
 * */

public class Pac extends Ellipse implements Runnable {
    private static final Color PAC_COLOR = new Color(255, 244, 39, 250);
    public double centerX;
    public double centerY;
    public double width;
    public double speed;
    private Map<String, List> info;
    public CanvasWindow canvas;
    Long defenseStart = null;
    private static Color BoxColor = new Color(110, 219, 255, 194);
    Long incentiveStart = null;
    private static final double PAC_RADIUS = 10;

    public static double getPacRadius() {
        return PAC_RADIUS;
    }

    public Pac(double centerX, double centerY, double speed, CanvasWindow canvas) {
        super(centerX - PAC_RADIUS, centerY - PAC_RADIUS, PAC_RADIUS * 2, PAC_RADIUS * 2);
        this.centerX = centerX;
        this.centerY = centerY;
        this.setFillColor(PAC_COLOR);
        this.setFilled(true);
        this.speed = speed;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        this.setPosition(this.getCenterX(), getCenterY() - speed);
    }

    public void updatePacPosition(Point mouse) {
        double mouseX = mouse.getX();
        double mouseY = mouse.getY();
        this.setCenter(mouseX, mouseY);
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
            if (!hitRightBoundary()) {
                this.setPosition(this.getCenterX() + speed, getCenterY());
            }
        }
        if (key.toString().equalsIgnoreCase("LEFT_ARROW") || key.toString().equalsIgnoreCase("A")) {
            if (!hitLeftBoundary()) {
                this.setPosition(this.getCenterX() - speed, getCenterY());
            }
        }
        if (key.toString().equalsIgnoreCase("DOWN_ARROW") || key.toString().equalsIgnoreCase("S")) {
            if (!hitLowerBoundary()) {
                this.setPosition(this.getCenterX(), getCenterY() + speed);
            }
        }
    }

    /**
     * Return true if the ball has hit the upper boundary.
     */
    private boolean hitUpperBoundary() {
        return !(getCenter().getY() - PAC_RADIUS > Boundary.upperY);
    }

    /**
     * Return true if the ball has hit the lower boundary.
     */
    private boolean hitLowerBoundary() {
        return !(getCenter().getY() + PAC_RADIUS < Boundary.lowerY);
    }

    /**
     * Return true if the ball has hit the left boundary.
     */
    private boolean hitLeftBoundary() {
        return !(getCenter().getX() - PAC_RADIUS > Boundary.leftX);
    }

    /**
     * Return true if the ball has hit the right boundary.
     */
    private boolean hitRightBoundary() {
        return !(getCenter().getX() + PAC_RADIUS < Boundary.rightX);
    }

    /**
     * Gets the speed of the ball.
     */
    public double getSpeed() {
        return speed;
    }

    public double getCenterX() {
        return this.getPosition().getX();
    }

    public double getCenterY() {
        return this.getPosition().getY();
    }

    public boolean collideswithAdvertisement() {
        if(canvas.getElementAt(getCenterX(), getCenterY()) != null && canvas.getElementAt(getCenterX(), getCenterY()).getType() instanceof TrackerAd){
        return true;
        }
        return false;
    }
    public boolean collideswithAnalytics() {
        if (canvas.getElementAt(getCenterX(), getCenterY()) != null && canvas.getElementAt(getCenterX(), getCenterY()).getType() instanceof TrackerSiteAnalytics) {
            System.out.println("IT HIT ANALYTICS");
            return true;
        }
        return false;
    }
    public boolean collideswithDefense(DefenseManager defenseManager, TrackerManager trackerManager) {
        if (canvas.getElementAt(getCenterX(), getCenterY()) != null && canvas.getElementAt(getCenterX(), getCenterY()).getType() instanceof Defenses) {
            int i = Defenses.showMessage(10, BoxColor, "");
            defenseManager.removeCurrentDefense();
            defenseStart = System.currentTimeMillis();
            trackerManager.sleep(i);
            return true;
        }
        if (defenseStart != null && System.currentTimeMillis() - defenseStart > 2000){
            defenseStart = null;
            defenseManager.generateRandomDefense();
        }
        return false;
    }

    public boolean collideWithIncentive(IncentiveManager incentiveManager) {
        if (canvas.getElementAt(getCenterX(), getCenterY()) != null && canvas.getElementAt(getCenterX(), getCenterY()).getType() instanceof Incentives) {
            incentiveManager.removeCurrentIncentive();
            incentiveStart = System.currentTimeMillis();
            return true;
        }
        if (incentiveStart != null && System.currentTimeMillis() - incentiveStart > 2000){
            incentiveStart = null;
            incentiveManager.generateRandomIncentive();
        }
        return false;
    }
}



