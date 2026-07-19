package com.example;

import javax.swing.SwingUtilities;

import com.example.screens.auth.Login;



public class App {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });

    }

}