import comp127graphics.*;
import comp127graphics.Image;
import comp127graphics.Rectangle;
import java.awt.*;
import java.util.*;
import java.util.List;
/**
 * This class represents different available tools users have to prevent their information from being collected
 * by third-party trackers.
 *
 * @author Lu Li
 * */

public abstract class Defenses extends GraphicsGroup {
    private final GraphicsGroup graphics;
    private static CanvasWindow canvas;
    private static double x;
    private static double y;
    private final int SIZE = 40;
    private static Color defenseColor = new Color(200,80,100,13 );

    static List<DefenseMessage> messages = new ArrayList<>();
    List<String> nam = new ArrayList<>();
    private static final String[] MESSAGE = {
            "You have obtained an Ad-Blocker! Ad blockers are a great way to avoid getting your data collected! They do so by blocking requests from your browser to domains that are known to belong to trackers! The blockers can also restrict the types of cookies that domains can set! Enjoy being advertisement tracker free!",
            "Browsers have built-in options to block third-party cookies! Blocking these cookies can protect you from third-party trackers by blocking cookie requests! But beware, more sophisticated trackers do not require these cookies. They can still get data from you through the exploitation of your browser cache!",
            "Worried about website trackers obtaining information like your location? Do not worry. Using a VPN is an effective way to prevent the leaking of your address and location! Install a VPN, and you fall under the radar!",
            "Due to regulations such as CCPR and GDPR, websites have privacy policies that inform users how their data is being collected and used. A right under these protections that you are given is top Opt-Out of data collection! This Opt-Out feature allows you to deny a website the right to collect data about you! Bye Bye trackers!",
            "Believe it or not, there are dedicated browser extensions, such as Ghostery and Privacy Badger, that will allow you to block trackers directly from a website! Tools like these tell you how many trackers exist, how they are embedded in the website, and what type of information they collect from you. Do not let these trackers get away with it, download tracker blockers now!",
            "A popular search engine used by the public is Google. However, Google contributes greatly to the tracking and collection of your data. Google does so by gathering the searches you make, as well as creating popular software for other companies to use to track your data such as Google Analytics and Google Tag Manager. Avoid getting your data collected by switching to DuckDuckGo, a search engine that does not track you!"};


    public GraphicsGroup getGraphics() {
        return graphics;
    }
    public static void setDescription() {
        for (String description: MESSAGE){
            messages.add(new DefenseMessage(false, description));
        }
    }

    public Defenses(CanvasWindow canvasInput, double x, double y) {
        canvas = canvasInput;
        Image defenseBody = new Image(x, y, "defense.png", SIZE);
        graphics = new GraphicsGroup();
        graphics.add(defenseBody);
        canvas.add(graphics);
    }

    public static int showMessage(int wordsPerLine, Color BoxColor, String description){
        Random rand = new Random();
        boolean shownBefore = false;
        int id = rand.nextInt(messages.size());
        if (description.length() == 0){
            description = messages.get(id).message;
            shownBefore = messages.get(id).shown;
            messages.get(id).shown = true;
            if (id == 0){
                TracMan.increaseScore(50);
                GraphicsText add = new GraphicsText("+ 50", 550, 50);
                add.setFontSize(50);
                canvas.add(add);
                canvas.draw();
                canvas.pause(2000);
                canvas.remove(add);
            }
            else {
                TracMan.increaseScore(5);
                GraphicsText add = new GraphicsText("+ 5", 550, 50);
                add.setFontSize(40);
                canvas.add(add);
                canvas.draw();
                canvas.pause(2000);
                canvas.remove(add);
            }
        }
        else{
            shownBefore = true;
        }
        String[] myArray = description.split(" ");
        List<String> al = new ArrayList<>();
        al = Arrays.asList(myArray);
        int numOfLines = al.size() / wordsPerLine + 1;
        String[] lines = new String[numOfLines];
        for(int i = 0; i < numOfLines; i++){
            String curLine = "";
            for (int j = 0; j < wordsPerLine; j ++){
                int curIndex = i * wordsPerLine + j;
                if (curIndex < myArray.length){
                    curLine = curLine.concat(" "+ myArray[i * wordsPerLine + j]);
                }
            }
            lines[i] = curLine;
        }
        int lineWidth = 25;
        double x = TracMan.getCanvasWidth()/7;
        double y = TracMan.getCanvasHeight()/4;
        Rectangle rectangle = new Rectangle(x, y, wordsPerLine * 60 + 20,lineWidth * numOfLines + 30);
        rectangle.setFilled(true);
        rectangle.setFillColor(BoxColor);
        canvas.add(rectangle);
        GraphicsText[] texts = new GraphicsText[numOfLines];
        for (int i = 0; i < numOfLines; i++){
            texts[i] = new GraphicsText(lines[i], x + 20,y + i * lineWidth + 30);
            texts[i].setFontSize(16);
            canvas.add(texts[i]);
        }
        canvas.draw();
        if (shownBefore){
            canvas.pause(3000);
        }
        else{
            canvas.pause(8000);
        }

        for (GraphicsText text: texts) {
            canvas.remove(text);
        }
        canvas.remove(rectangle);
        return id;
    }
}
