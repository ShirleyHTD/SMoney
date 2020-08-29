package ui;

import exception.UnexpectedLengthException;
import model.AEvent;
import ui.home.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LogInPage extends JFrame {
    private JFrame welcome;
    private JPanel welcomePanel;
    private JButton logIn;
    private JButton cancell;
    public  HomePage popUpWindow;


    //MODIFIES : this
    // EFFECTS : create a log in page with LogIn and Cancel button for the user to select
    public LogInPage() throws IOException, UnexpectedLengthException {
        welcome = new JFrame();
        welcomePanel = new JPanel();
        logIn = new JButton("Log In");
        cancell = new JButton("Cancel");
        popUpWindow = new HomePage();

        welcome.setTitle("Smoney");
        welcome.setSize(500,500);
        welcome.setLocationRelativeTo(null);
        start();

        welcome.setVisible(true);
        logIn.addActionListener(popUpWindow);

        setUpButtons();


    }

    //@SuppressWarnings("checkstyle:MethodLength")
    //MODIFIES: this
    //EFFECTS: set up the window
    private void start() {
        welcome.add(welcomePanel);
        welcomePanel.setLayout(new GridLayout(5,3));

        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(logIn);
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(cancell);
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
        welcomePanel.add(new JLabel(""));
    }

    //MODIFIES: this
    //EFFECTS: add action listener to the buttons
    private void setUpButtons() {
        logIn.addActionListener(popUpWindow);

        cancell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome.dispose();
            }
        });
    }

    //MODIFIES : this
    // EFFECTS : add the list of events on the window that would popup after clicking LogIn
    public void add(AEvent x) throws IOException {
        popUpWindow.add(x);

    }



}
