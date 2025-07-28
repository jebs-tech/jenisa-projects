package burhanfess;
import burhanfess.displays.Display;
import burhanfess.displays.UnauthorizedDisplay;

public class BurhanFess {
    private Display currentDisplay;
    
    public BurhanFess() {
        this.currentDisplay = new UnauthorizedDisplay();
    }
    
    public void run() {
        currentDisplay.run();
    }
    
    public static void main(String[] args) {
        BurhanFess app = new BurhanFess();
        app.run();
    }
    
    // Getters and Setters
    public Display getCurrentDisplay() { return currentDisplay; }
    public void setCurrentDisplay(Display currentDisplay) { this.currentDisplay = currentDisplay; }
}