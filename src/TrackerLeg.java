import comp127graphics.GraphicsObject;
import comp127graphics.Point;
import java.awt.*;

/**
 * This class represents legs of trackers.
 * @author Paul Cantrell
 */

public class TrackerLeg {
    private final GraphicsObject graphics;
    private final Point restPosition;
    private double rangeOfMotion;
    private boolean anchored;

    /**
     * Critters should instantiate legs from their createGraphics() methods.
     *
     * @param graphics         The visual representation of the leg, positioned at rest.
     * @param rangeOfMotion    How far away from its rest position the leg is allowed to move.
     */
    public TrackerLeg(GraphicsObject graphics, double rangeOfMotion) {
        this.graphics = graphics;
        this.restPosition = graphics.getPosition();
        this.rangeOfMotion = rangeOfMotion;
    }

    public GraphicsObject getGraphics() {
        return graphics;
    }

    /**
     * Called after the body has moved to update the leg motion accordingly.
     * This method does not use a particularly convincing leg motion algorithm;
     * low-budgets Saturday morning cartoons would be proud.
     */
    public void bodyMovedBy(double dx, double dy) {
        Point loc = graphics.getPosition();
        if(anchored) {
            graphics.setPosition(-dx, -dy);
        } else {
            double speed = Math.hypot(dx, dy),
                    targetX = restPosition.getX() + dx / speed * rangeOfMotion,
                    targetY = restPosition.getY() + dy / speed * rangeOfMotion,
                    toTargetX = targetX - loc.getX(),
                    toTargetY = targetY - loc.getY(),
                    distToTarget = Math.hypot(toTargetX, toTargetY);
            graphics.setPosition(
                    toTargetX / distToTarget * speed * 1.5,
                    toTargetY / distToTarget * speed * 1.5);
        }
        loc = graphics.getPosition();

        double distention = Math.hypot(loc.getX() - restPosition.getX(), loc.getY() - restPosition.getY());
        if(distention >= rangeOfMotion)
            anchored = !anchored;
    }

    /**
     * An anchored leg roughly retains its current absolute position on the screen.
     * An unanchored leg quickly moves ahead of the current motion.
     */
    public boolean isAnchored() {
        return anchored;
    }

    public void setAnchored(boolean anchored) {
        this.anchored = anchored;
    }
}
