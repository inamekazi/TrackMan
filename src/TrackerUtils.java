
import comp127graphics.Image;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public final class TrackerUtils {

    /**
     * Weighted average: w=1 returns a; w=0 returns b.
     */
    public static double blend(double a, double b, double w) {
        return a * w + b * (1-w);
    }

    /**
     *
     * @param name Image name and extension
     * @return Image object with the specified image, null if not found
     */
//    public static Image loadCritterImage(String name)
//    {
//        try {
//            System.out.println(Paths.get(CritterUtils.class.getResource("/"+ name).toURI()).toString());
//            return new Image(0,0, Paths.get(CritterUtils.class.getResource("/"+ name).toURI()).toString());
//        } catch(URISyntaxException e) {
//            throw new IllegalArgumentException("Invalid image URI", e);
//        }
//    }

}
