import comp127graphics.*;
import comp127graphics.Rectangle;
import  comp127graphics.GraphicsGroup;
import java.awt.*;
import java.security.MessageDigest;

public class AdBlock extends Defenses {
    boolean encountered = false;
    private static final String[] MESSAGE = {"You have obtained an Ad-Blocker! Ad blockers are a great way to avoid getting your data collected! They do so by blocking requests from your browser to domains that are known to belong to trackers! The blockers can also restrict the types of cookies that domains can set! Enjoy being advertisement tracker free!",
    "Browsers have built-in options to block third-party cookies! Blocking these cookies can protect you from third-party trackers by blocking cookie requests! But beware, more sophisticated trackers do not require these cookies. They can still get data from you through the exploitation of your browser cache!",
    "Worried about website trackers obtaining information like your location? Do not worry. Using a VPN is an effective way to prevent the leaking of your address and location! Install a VPN, and you fall under the radar!",
    "Due to regulations such as CCPR and GDPR, websites have privacy policies that inform users how their data is being collected and used. A right under these protections that you are given is top Opt-Out of data collection! This Opt-Out feature allows you to deny a website the right to collect data about you! Bye Bye trackers!",
    "Believe it or not, there are dedicated browser extensions, such as Ghostery and Privacy Badger, that will allow you to block trackers directly from a website! Tools like these tell you how many trackers exist, how they are embedded in the website, and what type of information they collect from you. Do not let these trackers get away with it, download tracker blockers now!",
    "A popular search engine used by the public is Google. However, Google contributes greatly to the tracking and collection of your data. Google does so by gathering the searches you make, as well as creating popular software for other companies to use to track your data such as Google Analytics and Google Tag Manager. Avoid getting your data collected by switching to DuckDuckGo, a search engine that does not track you!"};

    public AdBlock(CanvasWindow canvas, double x, double y ) {
        super(canvas, x, y);
    }
}
