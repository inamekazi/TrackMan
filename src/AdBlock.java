import comp127graphics.*;
import comp127graphics.Rectangle;
import  comp127graphics.GraphicsGroup;
import java.awt.*;

public class AdBlock extends Rectangle {

    private static final Color ADBLOCK_COLOR = new Color(255, 34, 34, 250);
    public double centerX;
    public double centerY;
    public double width;
    public double height;

    // Rectangle
    public AdBlock (double centerX, double centerY, double width, double height){
        super(centerX, centerY, width, height);
        this.centerX = centerX;
        this.centerY = centerY;
        setFillColor(ADBLOCK_COLOR);
        setFilled(true);
    }

//    Polygon Attempt
//
//    public int[] xpoints = {0,0,1,2,3,3,2,1};
//    public int[] ypoints = {1,2,3,3,2,1,0,0};
//
//    public AdBlock (int[] xpoints, int[] ypoints, int points){
//        super(xpoints, ypoints, points);
//        setFillColor(ADBLOCK_COLOR);
//        setFilled(true);
//    }

}
