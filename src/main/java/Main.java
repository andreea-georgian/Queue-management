import View.View;

import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        View theView = new View();
        Controller theController = new Controller(theView);
    }
}
