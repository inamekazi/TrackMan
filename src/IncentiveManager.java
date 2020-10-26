import comp127graphics.CanvasWindow;
import java.util.Random;

/**
 * This class manages all the incentives.
 * @author Lu Li
 * */

public class IncentiveManager {
    private CanvasWindow canvas;
    private Incentives incentive;
    public IncentiveManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }
    public void generateRandomIncentive(){
        Random random = new Random();
        double x = random.nextDouble() * (TracMan.getCanvasWidth() - Pac.getPacRadius() * 2);
        double y = random.nextDouble() * (TracMan.getCanvasHeight() - Pac.getPacRadius() * 2);
        incentive = new SurfingIncentive(canvas, x, y);
        incentive.getGraphics().setAllType(incentive);
        incentive.getType();
        canvas.add(incentive);
    }
    public void removeCurrentIncentive(){
        canvas.remove(incentive.getGraphics());
    }
}
