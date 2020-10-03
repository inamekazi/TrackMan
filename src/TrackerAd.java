
import comp127graphics.Arc;
import comp127graphics.Ellipse;

import java.awt.*;
import java.util.ArrayList;

import comp127graphics.GraphicsGroup;
import comp127graphics.Rectangle;

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

//        Rectangle leftShoe = new Rectangle(40,105, 10,10);
//        leftShoe.setStrokeColor(Fill_Body_Color);
//        leftShoe.setFillColor(new Color(1,1,1));
//        leftShoe.setFilled(true);

//        addLeg(new Leg(leftLeg, 2));   // this leg will be animated when running CritterParty
//        addLeg(new Leg(leftShoe, 2));   // this leg will be animated when running CritterParty

        Rectangle rightLeg = new Rectangle(50,80,10,10);
        rightLeg.setStrokeColor(Fill_Body_Color);
        rightLeg.setFillColor(Stroke_Color);
        rightLeg.setFilled(true);
//        addLeg(new Leg(rightLeg, 2));   // this leg will be animated when running CritterParty


        Eye leftEye = new Eye(5, 0.8, 0.18, Color.BLUE);
        Eye rightEye = new Eye(5, 0.8, 0.18, Color.BLUE);
        addEye(leftEye, 42, 40);
        addEye(rightEye, 58, 40);
        Arc smile = new Arc(40,40,20,20,180,120);
        smile.setStrokeColor(Color.BLACK);
        getGraphics().add(smile);
    }
}
