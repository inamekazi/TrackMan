import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import comp127graphics.Ellipse;
import comp127graphics.Point;

import java.awt.*;


public class Pac extends Ellipse {

    private static final Color PAC_COLOR = new Color(255, 244, 39, 250);
    public double centerX;
    public double centerY;
    public  double width;
    public double height;
    private static final double PAC_RADIUS = 10;

    private CanvasWindow canvas;

    public Pac(CanvasWindow canvas, double centerX, double centerY){
        super(centerX- PAC_RADIUS, centerY - PAC_RADIUS, PAC_RADIUS*2, PAC_RADIUS*2);
        this.centerX = centerX;
        this.centerY = centerY;
        this.setFillColor(PAC_COLOR);
        this.setFilled(true);
        this.canvas = canvas;

        this.canvas.onMouseMove(event ->
                updatePacPosition(event.getPosition()));
        this.canvas.add(this);
    }

    public void updatePacPosition(Point mouse){
        double mouseX = mouse.getX();
        double mouseY = mouse.getY();
        setCenter(mouseX,mouseY);
    }

    public double getCenterX(){
        return this.centerX;
    }

    public double getCenterY(){
        return this.centerY;
    }

}
