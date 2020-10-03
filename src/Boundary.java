/**
 * This is a boundary class that constructs four sides of the canvasWindow for the ball to bounce back.
 */
public class Boundary{
    static int leftX = 0;
    static int rightX = (int) TracMan.getCanvasWidth();
    static int upperY = 0;
    public static int lowerY = (int) TracMan.getCanvasHeight();
}
