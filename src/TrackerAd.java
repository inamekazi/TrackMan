import comp127graphics.Arc;
import comp127graphics.Ellipse;
import java.awt.*;
import comp127graphics.Rectangle;

/**
 * This is a class for the Advertisement Tracker.
 * Adopted from Critters Lab at Macalester College COMP 127.
 * @author Lu Li.
 * */

public class TrackerAd extends trackerBody{
    public static final Color Stroke_Color = new Color(218, 236, 255),
            Fill_Color = new Color(218, 236, 255),
            Fill_Body_Color = new Color(218, 236, 255);
    public TrackerAd() {
//        super(1,2,3,4);
        buildGraphics();
    }
    @Override
    protected void buildGraphics() {
        Ellipse duck = new Ellipse(30,20,40,40);
        duck.setStrokeColor(Stroke_Color);
        duck.setFilled(true);
        duck.setFillColor(Fill_Color);
        getGraphics().add(duck);

        Rectangle body = new Rectangle(30,40,40,40);
        body.setStrokeColor(Stroke_Color);
        body.setFilled(true);
        body.setFillColor(Fill_Color);
        getGraphics().add(body);

        Rectangle lowerBody = new Rectangle(30,60,40,20);
        lowerBody.setStrokeColor(Stroke_Color);
        lowerBody.setFilled(true);
        lowerBody.setFillColor(Fill_Body_Color);
        getGraphics().add(lowerBody);

        Rectangle leftLeg = new Rectangle(40,80,10,10);
        leftLeg.setStrokeColor(Fill_Body_Color);
        leftLeg.setFillColor(Stroke_Color);
        leftLeg.setFilled(true);

        Rectangle rightLeg = new Rectangle(50,80,10,10);
        rightLeg.setStrokeColor(Fill_Body_Color);
        rightLeg.setFillColor(Stroke_Color);
        rightLeg.setFilled(true);

        TrackerEye leftEye = new TrackerEye(7, 0.4, 0.18, Color.BLUE);
        TrackerEye rightEye = new TrackerEye(7, 0.4, 0.18, Color.BLUE);
        addEye(leftEye, 42, 40);
        addEye(rightEye, 58, 40);
        Arc smile = new Arc(40,40,20,20,180,120);
        smile.setStrokeColor(Color.BLACK);
        getGraphics().add(smile);
    }
}
