package com.wenhuang;

import com.wenhuang.gameview.GameView;
import com.wenhuang.gameview.Level1GameView;
import com.wenhuang.gameview.Level2GameView;
import com.wenhuang.gameview.Level3GameView;
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
    public static GameView gameView;
    private int level;

    public Main() {
        level = 1;
        resetGame(new Level1GameView());
        addKeyListener(this);
    }

    private void resetGame(GameView game) {
        moses = new Moses(1, 1);
        gameView = game;
        repaint();
    }

    private void die() {
        level = 1;
        JOptionPane.showMessageDialog(null, "You Die");
        resetGame(new Level1GameView());
    }

    private void changeLevel() {
        if (level == 1) {
            resetGame(new Level1GameView());
        }
        if (level == 2) {
            resetGame(new Level2GameView());
        }
        if (level == 3) {
            resetGame(new Level3GameView());
        }
    }

    private void win() {
        int response = JOptionPane.showOptionDialog(null, "YOU WIM! Want to play again?", "GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, JOptionPane.YES_OPTION);
        switch (response) {
            case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break;
            case JOptionPane.YES_OPTION:
                level = 1;
                resetGame(new Level1GameView());
        }
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
        String result;
        if (e.getKeyCode() == 37 && mosesPoint.x > 1) {
            result = moses.overlap(mosesPoint.x - 1, mosesPoint.y);
            if (result.equals("DIE")) {
                die();
                return;
            }
            if (!result.equals("CANNOT MOVE")) {
                mosesPoint.x -= 1;
            }
            if (result.equals("NEXT")) {
                level++;
                changeLevel();
                return;
            }
            if (result.equals("WIN")) {
                win();
                return;
            }
        }
        if (e.getKeyCode() == 38 && mosesPoint.y > 1) {
            result = moses.overlap(mosesPoint.x, mosesPoint.y - 1);
            if (result.equals("DIE")) {
                die();
                return;
            }
            if (!result.equals("CANNOT MOVE")) {
                mosesPoint.y -= 1;
            }
            if (result.equals("NEXT")) {
                level++;
                changeLevel();
                return;
            }
            if (result.equals("WIN")) {
                win();
                return;
            }
        }
        if (e.getKeyCode() == 39 && mosesPoint.x < COLUMN) {
            result = moses.overlap(mosesPoint.x + 1, mosesPoint.y);
            if (result.equals("DIE")) {
                die();
                return;
            }
            if (!result.equals("CANNOT MOVE")) {
                mosesPoint.x += 1;
            }
            if (result.equals("NEXT")) {
                level++;
                changeLevel();
                return;
            }
            if (result.equals("WIN")) {
                win();
                return;
            }
        }
        if (e.getKeyCode() == 40 && mosesPoint.y < ROW) {
            result = moses.overlap(mosesPoint.x, mosesPoint.y + 1);
            if (result.equals("DIE")) {
                die();
                return;
            }
            if (!result.equals("CANNOT MOVE")) {
                mosesPoint.y += 1;
            }
            if (result.equals("NEXT")) {
                level++;
                changeLevel();
                return;
            }
            if (result.equals("WIN")) {
                win();
                return;
            }
        }
        moses.setPosition(mosesPoint);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
