package ui.home;

import model.CalendarForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyPieChart implements ActionListener, ItemListener {
    CalendarForm calendarForm;
    CreateWindow window;
    JLabel text;

    public MyPieChart() {
        window = new CreateWindow(null,500,500);
    }

    public void addCalendar(CalendarForm calendarForm) {
        this.calendarForm = calendarForm;
    }

    public void actionPerformed(ActionEvent e) {
        text = new JLabel(calendarForm.printDataBase());
        window.thePanel.add(text);
        window.theWindow.setVisible(true);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        return;
    }
}
