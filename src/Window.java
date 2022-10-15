import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    JFrame mainFrame;
    Button buyButton;
    Button resetButton;
    Button cartButton;

    Window() {
        mainFrame = new JFrame();
        mainFrame.setSize(new Dimension(800, 600));

        buyButton = new Button();
        buyButton.setSize(100, 100);
        buyButton.setLabel("Buy Selected");

        resetButton = new Button();
        resetButton.setSize(100, 100);
        resetButton.setLabel("Reset Selection");

        cartButton = new Button();
        cartButton.setSize(100, 100);
        cartButton.setLabel("Cart");
    }
}
