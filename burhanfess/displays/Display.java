package burhanfess.displays;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public interface Display {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Scanner scanner = new Scanner(System.in);
    
    void showMenu();
    void showHeader();
    void run();
    void showFooter();
    void showCurrentDate();
}