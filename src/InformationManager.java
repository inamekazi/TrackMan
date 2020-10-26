import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsText;
import comp127graphics.Rectangle;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This class manages the information a user has.
 *
 * @author Lu Li
 * */
public class InformationManager {
    private static String[] information = {
            "Your browsing behavior!",
            "Your address, IP address, etc.",
            "Your browsing history, shopping history, etc.",
    };
    public static List<String> lostInfoSofar = new ArrayList<>();
    private static Map<Integer, List<String>> info = new HashMap<>();

    static double x = TracMan.getCanvasWidth() * 5 / 6;
    static double y = TracMan.getCanvasHeight()  / 10;
    static CanvasWindow canvasWindow;
    static Color color = new Color(255, 117, 93, 120);
    private static void populateInfo(){
        info.put(0, new ArrayList<String>());
        info.put(1, new ArrayList<String>());
        info.put(2, new ArrayList<String>());

        info.get(0).add("Your browsing behaviors!");
        info.get(0).add("How do you interact with web pages.");
        info.get(0).add("How do you interact with web pages.");

        info.get(1).add("Your IP address, etc.");
        info.get(1).add("The type of device you are using.");
        info.get(1).add("Your search history.");
        info.get(1).add("Your location and demographics!");
        info.get(1).add("Your ad views.");
        info.get(1).add("Your cookie data!");

        info.get(2).add("What you bought recently?");
        info.get(2).add("Your shopping behaviors!");
    }
    public static void setCanvasWindow(CanvasWindow canvasWindow1){
        canvasWindow = canvasWindow1;
        Rectangle informationBox = new Rectangle(x - 10, y + 10,TracMan.getCanvasWidth()/6 , TracMan.getCanvasWidth() / 8);
        informationBox.setFilled(true);
        informationBox.setFillColor(color);
        canvasWindow.add(informationBox);
        GraphicsText intro = new GraphicsText("Information that has been collected:", x - 60 ,y - 10);
        intro.setFontSize(15);
        canvasWindow.add(intro);
        populateInfo();
        System.out.println(info.get(1));
    }
    public static void generateInformationMessage(int i){
        GraphicsText text = new GraphicsText();
        text.setPosition(x, y + 25);
        Random rand = new Random();
        System.out.println(info.get(i));
        System.out.println(i);
        String lost = info.get(i).get(rand.nextInt(info.get(i).size()));
        text.setText(lost);
        lostInfoSofar.add(lost);
        canvasWindow.add(text);
        y += 25;
    };
}
