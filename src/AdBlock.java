import comp127graphics.*;
import comp127graphics.Rectangle;
import  comp127graphics.GraphicsGroup;
import java.awt.*;
import java.security.MessageDigest;

public class AdBlock extends Defenses {
    private static final String MESSAGE = "You have obtained an Ad-Blocker! Ad blockers are a great way to avoid getting your data collected! They do so by blocking requests from your browser to domains that are known to belong to trackers! The blockers can also restrict the types of cookies that domains can set! Enjoy being advertisement tracker free!";


    public AdBlock(CanvasWindow canvas, double x, double y ) {
        super(canvas, x, y);

        setDescription(MESSAGE);
    }

}
