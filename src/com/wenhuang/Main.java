package com.wenhuang;

import com.wenhuang.gameview.GameView;
import com.wenhuang.gameview.Level1GameView;
import com.wenhuang.sprites.Moses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int CELL_SIZE = 50;
    public static final int COLUMN = WIDTH / CELL_SIZE;
    public static final int ROW = HEIGHT / CELL_SIZE;

    Moses moses;
    GameView gameView;

    public Main() {
        moses = new Moses(1, 1);
        gameView = new Level1GameView();
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        gameView.drawView(g);
        moses.drawSprite(g);
        requestFocusInWindow();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Bible Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Point mosesPoint = moses.getRelativePosition();
        if (e.getKeyCode() == 37 && mosesPoint.x > 1) {
            mosesPoint.x -= 1;
        }
        if (e.getKeyCode() == 38 && mosesPoint.y > 1) {
            mosesPoint.y -= 1;
        }
        if (e.getKeyCode() == 39 && mosesPoint.x < COLUMN) {
            mosesPoint.x += 1;
        }
        if (e.getKeyCode() == 40 && mosesPoint.y < ROW) {
            mosesPoint.y += 1;
        }
        moses.setPosition(mosesPoint);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
