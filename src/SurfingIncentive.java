import comp127graphics.CanvasWindow;

public class SurfingIncentive extends Incentives {
    private static final String MESSAGE = "You have obtained an Ad-Blocker!" +
            "Ad blockers are a great way to avoid getting your data collected!" +
            " They do so by blocking requests from your browser to domains that" +
            " are known to belong to trackers! The blockers can also restrict the types of cookies that domains can set! Enjoy being advertisement tracker free!";


    public SurfingIncentive(CanvasWindow canvas, double x, double y ) {
        super(canvas, x, y);
    }
}
