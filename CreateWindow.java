package ui.home;

import javax.swing.*;
import java.awt.*;

public class CreateWindow {
    public  JFrame theWindow;
    public JPanel thePanel;
    public JPanel thePanel2;


    public CreateWindow(Component c,int x,int y) {
        theWindow = new JFrame();
        thePanel = new JPanel();

        theWindow.add(thePanel);

        theWindow.setVisible(false);

        theWindow.setLocationRelativeTo(c);
        theWindow.setSize(x,y);
    }

    public CreateWindow() {
        theWindow = new JFrame();
        thePanel = new JPanel();
        thePanel2 = new JPanel();
        theWindow.setVisible(false);

        theWindow.setLocationRelativeTo(null);
        theWindow.setSize(500,500);
        theWindow.setVisible(false);
    }

    //EFFECT : create a window frame, has access to JFrame for further construction
    public CreateWindow(Component c, String sentence,int x,int y) {
        CreateWindow window = new CreateWindow(c,x,y);
        window.thePanel.add(new JLabel(sentence));

    }


}










//        theWindow = new JFrame();
//        thePanel = new JPanel();
//
//        theWindow.add(thePanel);
//        thePanel.add(new JLabel(sentence));
//
//        theWindow.setVisible(true);
//
//        theWindow.setLocationRelativeTo(c);
//        theWindow.setSize(x,y);