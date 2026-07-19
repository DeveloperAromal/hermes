package com.example.screens.dashboard;

import javax.swing.*;
import com.example.ui.components.UIColors;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {

        setTitle("Dashboard");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        JPanel rightJPanel = new JPanel();
        rightJPanel.setBackground(UIColors.CARD);

        Sidebar sidebar = new Sidebar();

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                sidebar,
                rightJPanel
        );

        splitPane.setDividerLocation(300);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        add(splitPane);

        setVisible(true);
    }
}