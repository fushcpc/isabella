package com.jimmy.isabella.gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Lancher {
    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new MainFrame();
    }
}
