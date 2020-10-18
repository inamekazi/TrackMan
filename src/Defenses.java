import comp127graphics.*;
import comp127graphics.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public abstract class Defenses extends GraphicsGroup {
    private final GraphicsGroup graphics;
    private static String description;
    private static CanvasWindow canvas;
    private static double x;
    private static double y;
    private static Color defenseColor = new Color(200,80,100,13 );
    private static Color BoxColor = new Color(200,80,200,13 );

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

    public static void showMessage(int wordsPerLine){
        Rectangle rectangle = new Rectangle(20,80,650,80);

        String[] myArray = description.split(" ");
        System.out.println(myArray.length);
        List<String> al = new ArrayList<>();
        al = Arrays.asList(myArray);
        int numOfLines = al.size() / wordsPerLine;
        String[] lines = new String[numOfLines];
        for(int i = 0; i < numOfLines; i++){
            String curLine = "";
            for (int j = 0; j < wordsPerLine; j ++){
                int curIndex = i * wordsPerLine + j;
                if (curIndex < myArray.length){
                    curLine = curLine.concat(" "+ myArray[i * wordsPerLine + j]);
                    System.out.println(curLine);
                }
            }
            lines[i] = curLine;
            System.out.println(curLine);
        }
        int lineWidth = 15;
        rectangle.setFilled(true);
        rectangle.setFillColor(BoxColor);
        canvas.add(rectangle);
        GraphicsText[] texts = new GraphicsText[numOfLines];
        for (int i = 0; i < numOfLines; i++){
            texts[i] = new GraphicsText(lines[i], 20,100 + i * lineWidth);
            texts[i].setFontSize(16);
            canvas.add(texts[i]);
        }
        canvas.draw();
        canvas.pause(11111);
        for (GraphicsText text: texts) {
            canvas.remove(text);
        }
        canvas.remove(rectangle);
    }
}
