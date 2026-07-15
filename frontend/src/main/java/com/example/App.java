package com.example;

import com.example.ui.screens.auth.Login;

import javax.swing.SwingUtilities;



public class App {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });

    }

}