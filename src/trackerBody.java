
import comp127graphics.GraphicsGroup;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The base class for all critters.
 * Actual critters must implement buildGraphics and call addLeg and addEye.
 *
 * @author Paul Cantrell
 */
public abstract class trackerBody extends GraphicsGroup {
    private final GraphicsGroup graphics;
    private double speed;
    private List<Eye> eyes;
    private List<Leg> legs;
    private comp127graphics.Point goal;

    protected double xOffset = 40.0;
    protected double yOffset = 40.0;

    public trackerBody() {
        eyes = new ArrayList<Eye>();
        legs = new ArrayList<Leg>();
        graphics = new GraphicsGroup(0,0);
        buildGraphics();
    }

    /**
     * @return the underlying graphics component.
     */
    public GraphicsGroup getGraphics() {
        return graphics;
    }

    /**
     *
     * @return the amount to offset the initial location of the graphic
     *         in the x direction when using setLocation.
     */
    public double getxOffset() {
        return xOffset;
    }

    /**
     *
     * @return the amount to offset the initial location of the graphic
     *         in the y direction when using setLocation.
     */
    public double getyOffset() {
        return yOffset;
    }

    /**
     * Concrete classes must override this and use it to draw the shape.
     */
    protected abstract void buildGraphics();


    public double getSizeTracker() {
        return Math.hypot(getGraphics().getWidth(), getGraphics().getHeight());
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Point getGoal() {
        return goal;
    }

    public void setGoal(comp127graphics.Point goal) {
        this.goal = goal;
    }

    public void moveTowardsGoal(double dt) {


        double dx = goal.getX() - graphics.getX() - getxOffset(), dy = goal.getY() - graphics.getY() - getyOffset(), dist = Math.hypot(dx, dy);
        moveBy(     // 2 m/s  5m   0.4m/s
                dx * getSpeed() / 20,
//                dx * getSpeed()/dist,
                dy * getSpeed() / 20,
                dt);
    }

    public void moveBy(double dx, double dy, double dt) {
        graphics.setPosition(graphics.getX() + dx * dt, graphics.getY() + dy * dt);
        for(Eye eye : eyes)
            eye.lookInDirectionOf(dx, dy, 0.5);

        for(Leg leg : legs)
            leg.bodyMovedBy(dx * dt, dy * dt);
    }

    /**
     * Adds a leg to the critter.
     * @param leg
     */
    protected void addLeg(Leg leg) {
        getGraphics().add(leg.getGraphics());
        legs.add(leg);
        leg.setAnchored(legs.size() % 2 == 0);
    }

    /**
     * Adds an eye to the critter.
     * @param eye
     */
    protected void addEye(Eye eye, int x, int y) {
        eye.getGraphics().setPosition(x, y);
        getGraphics().add(eye.getGraphics());
        eyes.add(eye);
    }
}
