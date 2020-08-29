package ui;

import exception.DayException;
import exception.InvalidDateException;
import exception.MonthException;
import exception.YearException;
import model.AEvent;
import ui.home.CreateWindow;
import ui.home.HomePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class ReadUserInput implements ActionListener {
    HomePage homePage;
    CreateWindow inputWindow;
    //JTextField textField;
    public JTextField year;
    public JTextField month;
    public JTextField day;
    public JTextField amount;
    public JTextField description;
    JButton confirm;
    JButton chi;
    JButton chuan;
    JButton yong;
    JButton income;

    public ArrayList<AEvent> events;
    public String theCategory;
    public String fileName = "D:\\UBC_2019_term1\\CPSC 210\\BigProject\\project_u7a3b\\data\\Data";

    public ReadUserInput(HomePage h) {
        homePage = h;
        setUp();
        confirm.addActionListener(e -> {
            try {
                setConfirm(e);
                clearAll();
            } catch (NumberFormatException ei) {
                System.out.println("Invalid input(s)!");
            } catch (InvalidDateException ee) {
                System.out.println("Invalid date!");
            } catch (NullPointerException eee) {
                System.out.println("Choose one of the categories please!");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                setTheCategory(null);
            }

        });

    }

    public void removeHomepage() {
        homePage = null;
    }

    //MODIFIES: this
    //EFFECTS: initiates all the fields
    private void setUp() {
        inputWindow = new CreateWindow();
        inputWindow.thePanel.setLayout(new GridLayout(3,4));
        inputWindow.thePanel2.setLayout(new GridLayout(2,1));
        chi = new JButton("吃");
        chuan = new JButton("穿");
        yong = new JButton("用");
        income = new JButton("收入");
        year = new JTextField();
        month = new JTextField();
        day = new JTextField();
        amount = new JTextField();
        description = new JTextField();
        confirm = new JButton("Confirm");
        setBoard();
    }

    //MODIFIES: this
    //EFFECTS: set up the window
    private void setBoard() {
        inputWindow.thePanel.add(chi);
        inputWindow.thePanel.add(chuan);
        inputWindow.thePanel.add(yong);
        inputWindow.thePanel.add(income);
        inputWindow.thePanel.add(new JLabel("Year"));
        inputWindow.thePanel.add(new JLabel("Month"));
        inputWindow.thePanel.add(new JLabel("Date"));
        inputWindow.thePanel.add(new JLabel("Amount"));
        inputWindow.thePanel.add(year);
        inputWindow.thePanel.add(month);
        inputWindow.thePanel.add(day);
        inputWindow.thePanel.add(amount);
        inputWindow.thePanel2.add(description);
        inputWindow.thePanel2.add(confirm);
        inputWindow.theWindow.add(inputWindow.thePanel, BorderLayout.NORTH);
        inputWindow.theWindow.add(new JSeparator(), BorderLayout.CENTER);
        inputWindow.theWindow.add(inputWindow.thePanel2, BorderLayout.SOUTH);
        setListeners();
    }

    //MODIFIES: this
    //EFFECTS: set listeners to the buttons
    private void setListeners() {
        chi.addActionListener(e -> {
            setTheCategory("chi");
        });
        chuan.addActionListener(e -> {
            setTheCategory("chuan");
        });
        yong.addActionListener(e -> {
            setTheCategory("yong");
        });
        income.addActionListener(e -> {
            setTheCategory("income");
        });
    }

    public void setTheCategory(String x) {
        theCategory = x;
    }

    //EFFECTS: set action listener to the Confirm button
    private void setConfirm(ActionEvent e) throws Throwable {
        String text = formatUserInput();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(text);
        writer.close();
        ReadFile.read();
        inputWindow.theWindow.setVisible(false);
        //events = ReadFile.get();
        homePage.add(ReadFile.get());
//        for (int i = 0; i < events.size(); i++) {
//            homePage.add(events.get(i));
//        }


    }

    //MODIFIES: this
    //EFFECTS: clear the text in each JTextField
    public void clearAll() {
        year.setText("");
        month.setText("");
        day.setText("");
        amount.setText("");
        description.setText("");
    }



    //EFFECTS: check if user's inputs are valid
    public void throwExceptions() throws YearException, MonthException, DayException {
        AEvent testCase = new AEvent(2019,1,1,"chi", 9.0, " ");

        int test = Integer.parseInt(year.getText());
        testCase.setYear(test);
//        if (test < 2000 || test > 2200 || year.getText() == "") {
//            throw new YearException();
//        }

        test = Integer.parseInt(month.getText());
        testCase.setMonth(test);
//        if (test < 1 || test > 12 || month.getText() == "") {
//            throw new MonthException();
//        }

        test = Integer.parseInt(day.getText());
        testCase.setDay(test);
//        if (test < 1 || test > 31 || day.getText() == "") {
//            throw new DayException();
//        }

        testCase.createCategory(theCategory,0.0);
//        if (theCategory == null) {
//            throw new NullPointerException();
//        }

        Double.parseDouble(amount.getText());

    }

    //EFFECTS: turn user's input into a string
    public String formatUserInput() throws MonthException, DayException, YearException {
        String result = "";
        throwExceptions();
        result = year.getText() + "/" + month.getText() + "/" + day.getText() + "/"
                + theCategory + "/" + amount.getText() + "/ " + description.getText();
        return result;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputWindow.theWindow.setVisible(true);

    }

    @Override
    public int hashCode() {
        return homePage.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
