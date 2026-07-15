package com.example.ui.screens.auth;

import com.example.ui.components.RoundedButton;
import com.example.ui.components.RoundedPasswordField;
import com.example.ui.components.RoundedTextField;
import com.example.ui.components.UIColors;
import com.example.ui.screens.dashboard.MainDashboard;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {

        setTitle("Login");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(UIColors.BACKGROUND);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(UIColors.CARD);

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
        title.setForeground(UIColors.FOREGROUND);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(UIColors.MUTED_FOREGROUND);

        RoundedTextField usernameInput = new RoundedTextField(20);
        usernameInput.setPlaceholder("Enter your username");
        usernameInput.setPreferredSize(new Dimension(200, 50));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(UIColors.MUTED_FOREGROUND);

        RoundedPasswordField passwordInput = new RoundedPasswordField(20);
        passwordInput.setPlaceholder("Enter your password");
        passwordInput.setPreferredSize(new Dimension(200, 50));

        RoundedButton loginButton = new RoundedButton("Login", RoundedButton.Variant.PRIMARY);
        loginButton.setPreferredSize(new Dimension(0, 45));

        loginButton.addActionListener(e -> {
            dispose();
            new MainDashboard().setVisible(true);
        });

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
        gbc.insets = new Insets(20, 20, 10, 20);
        rightPanel.add(loginButton, gbc);

        setContentPane(splitPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}