/**
 * This class represents a message for a defense a user has encountered or is about to encounter.
 *
 * @author Lu Li
 * */

public class DefenseMessage {
    boolean shown = false;
    String message = "";
    public DefenseMessage(boolean shown, String message){
        this.shown = shown;
        this.message = message;
    }
}
