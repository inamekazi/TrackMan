import comp127graphics.*;
import comp127graphics.Image;
import comp127graphics.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class Incentives extends GraphicsGroup {
    private final GraphicsGroup graphics;
    private static CanvasWindow canvas;
    private static double x;
    private static double y;
    private final int SIZE = 40;
    private static Color defenseColor = new Color(200,80,100,80 );

    List<String> nam = new ArrayList<>();

    public GraphicsGroup getGraphics() {
        return graphics;
    }
    public Incentives(CanvasWindow canvasInput, double x, double y) {
        canvas = canvasInput;

        Image defenseBody = new Image(x, y, "surfing.png",SIZE);
        graphics = new GraphicsGroup();
        graphics.add(defenseBody);
        canvas.add(graphics);
    }
}
