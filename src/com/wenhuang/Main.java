package com.wenhuang;

import com.wenhuang.gameview.GameView;
import com.wenhuang.gameview.Level1GameView;
import com.wenhuang.gameview.Level2GameView;
import com.wenhuang.gameview.Level3GameView;
import com.wenhuang.sprites.Door;
import com.wenhuang.sprites.Level1Sprites.Bug;
import com.wenhuang.sprites.Level1Sprites.Frog;
import com.wenhuang.sprites.Level1Sprites.Ice;
import com.wenhuang.sprites.Level1Sprites.Tombstone;
import com.wenhuang.sprites.Level2Sprites.Anubis;
import com.wenhuang.sprites.Level2Sprites.Cat;
import com.wenhuang.sprites.Level2Sprites.Pharaoh;
import com.wenhuang.sprites.Moses;
import com.wenhuang.sprites.Sprites;

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
            case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION, JOptionPane.CLOSED_OPTION:
                System.exit(0);
                break;
            case JOptionPane.YES_OPTION:
                level = 1;
                resetGame(new Level1GameView());
        }
    }

    private boolean overlapForMoses(int x, int y, Moses m) {
        for (Sprites sprites : gameView.getElements()) {
            if (sprites.getRelativePosition() != null) {
                if (x == m.getRelativePosition().x && y == m.getRelativePosition().y) {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomMove(Moses moses) {
        int new_x = 0;
        int new_y = 0;
        boolean overlap = false;
        for (Sprites sprites : gameView.getElements()) {
            Point spritesPosition = sprites.getRelativePosition();
            do {
                if (spritesPosition != null) {
                    new_x = spritesPosition.x;
                    new_y = spritesPosition.y;
                    if (sprites instanceof Cat || sprites instanceof Ice || sprites instanceof Tombstone || sprites instanceof Door) {
                        continue;
                    }
                    if (sprites instanceof Frog || sprites instanceof Bug || sprites instanceof Anubis || sprites instanceof Pharaoh) {
                        if (1 < spritesPosition.x && spritesPosition.x < COLUMN && 1 < spritesPosition.y && spritesPosition.y < ROW) {
                            int randomMovingDirection = (int) Math.floor(Math.random() * 4);
                            String result;
                            switch (randomMovingDirection) {
                                case 0:
                                    result = sprites.overlap(spritesPosition.x + 1, spritesPosition.y);
                                    if (!result.equals("CANNOT MOVE")) {
                                        new_x = spritesPosition.x + 1;
                                    }
                                    break;
                                case 1:
                                    result = sprites.overlap(spritesPosition.x - 1, spritesPosition.y);
                                    if (!result.equals("CANNOT MOVE")) {
                                        new_x = spritesPosition.x - 1;
                                    }
                                    break;
                                case 2:
                                    result = sprites.overlap(spritesPosition.x, spritesPosition.y + 1);
                                    if (!result.equals("CANNOT MOVE")) {
                                        new_y = spritesPosition.y + 1;
                                    }
                                    break;
                                case 3:
                                    result = sprites.overlap(spritesPosition.x, spritesPosition.y - 1);
                                    if (!result.equals("CANNOT MOVE")) {
                                        new_y = spritesPosition.y - 1;
                                    }
                            }

                        } else if (spritesPosition.x == 1) {
                            String result = sprites.overlap(spritesPosition.x + 1, spritesPosition.y);
                            if (!result.equals("CANNOT MOVE")) {
                                new_x = spritesPosition.x + 1;
                                new_y = spritesPosition.y;
                            }

                        } else if (spritesPosition.y == 1) {
                            String result = sprites.overlap(spritesPosition.x, spritesPosition.y + 1);
                            if (!result.equals("CANNOT MOVE")) {
                                new_x = spritesPosition.x;
                                new_y = spritesPosition.y + 1;
                            }

                        } else if (spritesPosition.x == COLUMN) {
                            String result = sprites.overlap(spritesPosition.x - 1, spritesPosition.y);
                            if (!result.equals("CANNOT MOVE")) {
                                new_x = spritesPosition.x - 1;
                                new_y = spritesPosition.y;
                            }

                        } else if (spritesPosition.y == ROW) {
                            String result = sprites.overlap(spritesPosition.x, spritesPosition.y - 1);
                            if (!result.equals("CANNOT MOVE")) {
                                new_x = spritesPosition.x;
                                new_y = spritesPosition.y - 1;
                            }

                        }
                    }

                }
                overlap = overlapForMoses(new_x, new_y, moses);
            } while (overlap);
            sprites.setPosition(new_x, new_y);
            repaint();

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
        String result = moses.overlap(mosesPoint.x, mosesPoint.y);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:

                if (result.equals("DIE")) {
                    die();
                    return;
                }

                if (mosesPoint.x > 1) {
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

                randomMove(moses);
                break;

            case KeyEvent.VK_UP:

                if (result.equals("DIE")) {
                    die();
                    return;
                }

                if (mosesPoint.y > 1) {
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

                randomMove(moses);
                break;

            case KeyEvent.VK_RIGHT:

                if (result.equals("DIE")) {
                    die();
                    return;
                }

                if (mosesPoint.x < COLUMN) {
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

                randomMove(moses);
                break;

            case KeyEvent.VK_DOWN:

                if (result.equals("DIE")) {
                    die();
                    return;
                }

                if (mosesPoint.y < ROW) {
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

                randomMove(moses);
                break;
            case KeyEvent.VK_A:
                for (int i = mosesPoint.x - 1; i > 0; i--) {
                    for (Sprites s : gameView.getElements()) {
                        if (s.getRelativePosition() != null && s.getRelativePosition().x == i && s.getRelativePosition().y == mosesPoint.y) {
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            } else if (s instanceof Frog || s instanceof Bug || s instanceof Anubis || s instanceof Pharaoh) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_W:
                for (int i = mosesPoint.y - 1; i > 0; i--) {
                    for (Sprites s : gameView.getElements()) {
                        if (s.getRelativePosition() != null && s.getRelativePosition().x == mosesPoint.x && s.getRelativePosition().y == i) {
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            } else if (s instanceof Frog || s instanceof Bug || s instanceof Anubis || s instanceof Pharaoh) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_D:
                for (int i = mosesPoint.x + 1; i <= COLUMN; i++) {
                    for (Sprites s : gameView.getElements()) {
                        if (s.getRelativePosition() != null && s.getRelativePosition().x == i && s.getRelativePosition().y == mosesPoint.y) {
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            } else if (s instanceof Frog || s instanceof Bug || s instanceof Anubis || s instanceof Pharaoh) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_S:
                for (int i = mosesPoint.y + 1; i <= ROW; i++) {
                    for (Sprites s : gameView.getElements()) {
                        if (s.getRelativePosition() != null && s.getRelativePosition().x == mosesPoint.x && s.getRelativePosition().y == i) {
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            } else if (s instanceof Frog || s instanceof Bug || s instanceof Anubis || s instanceof Pharaoh) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                for (Sprites s : gameView.getElements()) {
                    if (s.getRelativePosition() != null && s.getRelativePosition().x == mosesPoint.x && s.getRelativePosition().y == mosesPoint.y) {
                        if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                            return;
                        } else if (s instanceof Frog || s instanceof Bug || s instanceof Anubis || s instanceof Pharaoh) {
                            s.setNullPosition();
                            repaint();
                            return;
                        }
                    }
                }
                break;

        }

        moses.setPosition(mosesPoint);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
