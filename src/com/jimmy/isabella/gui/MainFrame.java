package com.jimmy.isabella.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jimmy.guiutility.component.NumberLabel;
import com.jimmy.guiutility.component.SoundUtility;

public class MainFrame {
    private JPanel centerPanel;
    private JPanel northPanel;
    private NumberLabel minLabel;
    private NumberLabel secLabel;
    private JPanel timePanel;
    private JButton startBtn;

    private int minute = 25;
    private int second = 0;

    public MainFrame() {

        minLabel = new NumberLabel(minute, false);
        initializeLable(minLabel);

        JLabel sepLabel = new JLabel(":");
        initializeLable(sepLabel);

        secLabel = new NumberLabel(second, true);
        initializeLable(secLabel);

        timePanel = new JPanel();
        timePanel.add(minLabel);
        timePanel.add(sepLabel);
        timePanel.add(secLabel);

        initializeStartBtn();
        northPanel = new JPanel();
        northPanel.add(startBtn, BorderLayout.CENTER);

        centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(northPanel, BorderLayout.NORTH);
        centerPanel.add(timePanel, BorderLayout.CENTER);

        JFrame frame = new JFrame("isabella v0.1");
        frame.setContentPane(centerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        // frame.pack();
        frame.setVisible(true);
    }

    private void initializeStartBtn() {
        startBtn = new JButton("start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBtn.setEnabled(false);
                ExecutorService es = Executors.newSingleThreadExecutor();
                es.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (minute != 0 || second != 0) {
                            if (second == 0) {
                                minute--;
                                second = 60;
                            }
                            second--;
                            minLabel.setText(minute);
                            secLabel.setText(second);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        startBtn.setEnabled(true);
                        SoundUtility.playSound("resource/PomodoroDone.wav");
                    }
                });
            }
        });
    }

    private void initializeLable(JLabel label) {
        Font font = new Font(null, Font.BOLD, 100);
        label.setFont(font);
        label.setForeground(Color.RED);
    }

}
