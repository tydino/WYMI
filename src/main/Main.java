package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("WYMI");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //causes window to be sized to fit the perferred size and layouts of its subcomponents

        window.setLocationRelativeTo(null);//center when null
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}


//being made off of basis of this series https://youtu.be/om59cwR7psI