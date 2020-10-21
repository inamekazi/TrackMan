import comp127graphics.CanvasWindow;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class DefenseManager {
    private CanvasWindow canvas;
    private Defenses defense;


    public DefenseManager(CanvasWindow canvas) {

        this.canvas = canvas;
        Defenses.setDescription();
    }

    public void generateRandomDefense(){
        Random random = new Random();
        double x = random.nextDouble() * (TracMan.getCanvasWidth() - Pac.getPacRadius() * 2);
        double y = random.nextDouble() * (TracMan.getCanvasHeight() - Pac.getPacRadius() * 2);
        defense = new AdBlock(canvas, x, y);
        defense.getGraphics().setAllType(defense);
        canvas.add(defense);
    }
    public void removeCurrentDefense(){
        canvas.remove(defense.getGraphics());
    }

}
