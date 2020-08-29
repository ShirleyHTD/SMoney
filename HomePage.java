package ui.home;

import exception.UnexpectedLengthException;
import model.AEvent;
import model.Calendar;
import ui.ReadUserInput;
import ui.ReadFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomePage implements ActionListener {
    ReadUserInput readUserInput;
    CreateWindow homePage;
    JTable table;
    private JScrollPane sb;
    private String[] columnName;
    private Calendar calendar;
    DefaultTableModel model;
    private JPanel atBottom;
    private JButton addButton;
    private JButton pie;
    private JButton lineGraph;
    private JButton delete;
    public MyPieChart pieChart;
    Box box;
    final Dimension size = new Dimension(400, 100);


    public HomePage() throws IOException {
        calendar = new Calendar();
        readUserInput = new ReadUserInput(this);

        readFile();

        pieChart = new MyPieChart();
        homePage = new CreateWindow(null, 600, 600);
        columnName = new String[]{"Date", "Total", "Category", "     ", "$"};
        atBottom = new JPanel(new BorderLayout());
        table = new JTable();
        sb = new JScrollPane(table);

        constructWindow();
        getOldData();

    }

    public void removeReadUserInput() {
        readUserInput = null;
    }

    //MODIFIES: this
    //EFFECTS: read the information in the file
    public void readFile() throws IOException {
        ReadFile readFile = new ReadFile();
        //try {
        AEvent event = readFile.get();
        calendar.add(event);
//        for (AEvent i : events) {
//            calendar.add(i);
//        }
        //}
//        catch (FileNotExistException e) {
//            System.out.println("Find previous code fail, file DNE.");
//        }

    }

    //MODIFIES: this
    //EFFECTS: set up the window
    private void constructWindow() {
        table.setFillsViewportHeight(true);
        homePage.thePanel.add(sb, BorderLayout.NORTH);
        homePage.theWindow.pack();
        //model = (DefaultTableModel) table.getModel();

        addButtons();
        homePage.theWindow.add(box, BorderLayout.SOUTH);
        theDeleteButton();

        homePage.theWindow.setVisible(false);
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

    }

    //MODIFIES: this
    //EFFECTS: set up the buttons
    private void addButtons() {
        box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 5, 5, 5));

        addButton = new JButton("+");
        addButton.setPreferredSize(size);
        pie = new JButton("Pie");
        pie.setPreferredSize(size);
        lineGraph = new JButton("Line Graph");
        lineGraph.setPreferredSize(size);
        delete = new JButton("Delete");
        delete.setPreferredSize(size);

        box.add(delete);
        box.add(pie);
        box.add(lineGraph);
        box.add(addButton);


        //homePage.theWindow.add(box, BorderLayout.SOUTH);

        addButton.addActionListener(readUserInput);
        pieChart.addCalendar(calendar);
        pie.addActionListener(pieChart);

    }

    //MODIFIES: this
    //EFFECTS: set up the delete button
    private void theDeleteButton() {
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    deleteAnEvent();
                } catch (IOException e) {
                    System.out.println("soPicky() went wrong");
                }

            }
        });
    }

    //MODIFIES: this
    //EFFECTS: delete the selected event
    private void deleteAnEvent() throws IOException {
        String date = "";
        String category = "";
        String amount = "";
        String description = "";
        try {
            String value = (String) table.getValueAt(table.getSelectedRow(),0);
            if (value.equals(" ")) {
                date = recursive(table.getSelectedRow() - 1);
            } else {
                date = (String) table.getValueAt(table.getSelectedRow(),0);
            }
            category = (String) table.getValueAt(table.getSelectedRow(),2);
            description = (String) table.getValueAt(table.getSelectedRow(),3);
            amount = (String) table.getValueAt(table.getSelectedRow(),4);
        } catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Action ignored, select an item to delete.");
        }

        remove(date, category, description, amount);

    }

    //EFFECTS: recursive method
    public String recursive(int row) {
        if (table.getValueAt(row,0).equals("")) {
            recursive(row - 1);
        }
        return (String) table.getValueAt(row, 0);
    }

    //MODIFIES: this
    //EFFECTS: get the stored data from last time
    private void getOldData() {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            model.setDataVector(calendar.getCs(), columnName);
        } catch (UnexpectedLengthException e) {
            System.out.println("Please consult the Calender.add() for more info.");
        }
    }



    //MODIFIES: this
    //EFFECTS: refresh the homepage with new element added
    public void add(AEvent x) throws IOException {
        calendar.add(x);
        model.setRowCount(0);
        try {
            model.setDataVector(calendar.getCs(), columnName);
        } catch (UnexpectedLengthException e) {
            System.out.println("Refreshing failed. Please consult the Calender.add() for more info.");
        }

    }

    //MODIFIES: this
    //EFFECTS: refresh the homepage with one element removed
    public void remove(String date, String category, String description, String amount) throws IOException {
        calendar.myRemove(date, category, description, amount);
        model.setRowCount(0);
        try {
            model.setDataVector(calendar.getCs(), columnName);
        } catch (UnexpectedLengthException e) {
            System.out.println("none");
        }

    }

    public void actionPerformed(ActionEvent e) {
        homePage.theWindow.setVisible(true);

    }


}







//homePage.thePanel.add(sb);
//billsListed.setVisible(true);
//sb.setVisible(true);
//        container.setLayout(new BorderLayout());
//        container.add(billsListed.getTableHeader(), BorderLayout.PAGE_START);
//        container.add(billsListed, BorderLayout.CENTER);
//billsListed = new JTable(calendar.getcs(),columnName);

//textField = new JTextField(12);
//textField.setFont(textField.getFont().deriveFont(20f));

//            //inputWindow.theWindow.setVisible(false);
//            try {
//                events = ReadFile.get();
//                add(events.get(0));
//            }

//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input(s)!");
//            } catch (InvalidDateException ee) {
//                System.out.println("Invalid date!");
//            } catch (NullPointerException eee) {
//                System.out.println("Choose one of the categories please!");
//            }