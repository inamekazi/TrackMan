import comp127graphics.*;
import comp127graphics.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public abstract class Defenses extends GraphicsGroup {
    private final GraphicsGroup graphics;
    private static String description;
    private static CanvasWindow canvas;
    private static double x;
    private static double y;
    private static Color defenseColor = new Color(200,80,100,13 );

    List<String> nam = new ArrayList<>();

    public GraphicsGroup getGraphics() {
        return graphics;
    }

    public static void setDescription(String description) {
        Defenses.description = description;
    }

    public Defenses(CanvasWindow canvasInput, double x, double y) {
        canvas = canvasInput;
        Ellipse defenseBody = new Ellipse(x,y,40,40);
        defenseBody.setFilled(true);
        defenseBody.setFillColor(defenseColor);
        graphics = new GraphicsGroup();
        graphics.add(defenseBody);
        canvas.add(graphics);

        System.out.println("hereeee");
    }

    public static void showMessage(){
        Rectangle rectangle = new Rectangle(100, 100,100,100);
        GraphicsText text = new GraphicsText(description, 100, 100);
        text.setFontSize(20);
        rectangle.setFilled(true);
        rectangle.setFillColor(defenseColor);
        System.out.println("description:");
        System.out.println(description);
        canvas.add(rectangle);
        canvas.add(text);
    }

}
