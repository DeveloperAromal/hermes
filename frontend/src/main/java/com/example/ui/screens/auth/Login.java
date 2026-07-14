package com.example.ui.screens.auth;

import javax.swing.*;
import java.awt.*;




public class Login extends JFrame{

    public Login() {

        setTitle("Login");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(24, 24, 24));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(35, 35, 35));

        JSplitPane splitPane = new JSplitPane(

                JSplitPane.HORIZONTAL_SPLIT,
                leftPanel,
                rightPanel

        );

        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel title = new JLabel("Welcome Back");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);

        JTextField usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(0, 40));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);

        JTextField passwordInput = new JTextField();
        passwordInput.setPreferredSize(new Dimension(0, 40));

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(0, 45));

        gbc.gridx = 0;
        gbc.gridy = 0;

        rightPanel.add(title, gbc);

        gbc.gridy = 1;
        rightPanel.add(usernameLabel, gbc);

        gbc.gridy = 2;
        rightPanel.add(usernameInput, gbc);

        gbc.gridy = 3;
        rightPanel.add(passwordLabel, gbc);

        gbc.gridy = 4;
        rightPanel.add(passwordInput, gbc);

        gbc.gridy = 5;
        rightPanel.add(loginButton, gbc);

        setContentPane(splitPane);
    }

}



