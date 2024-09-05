package main;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {
    private static final int CELL_SIZE = 30;
    private GameMap gameMap;
    private PacMan pacMan;
    private List<Ghost> ghost = new ArrayList<>();
    private int score = 0;
    private int totalDots = 0;
    private boolean gameOver = false;
    private Timer gameTimer;

    public GamePanel() {
        gameMap = new GameMap();
        initializeGame();
        setPreferredSize(new Dimension(gameMap.getWidth() * CELL_SIZE, gameMap.getHeight() * CELL_SIZE));
        setBackground(new Color(0, 0, 0, 0));
        startGame();
    }

    private void initializeGame() {
        for (int i = 0; i < gameMap.getHeight(); i++) {
            for (int j = 0; j < gameMap.getWidth(); j++) {
                // 檢查初始化地圖對應的編號 4為創建pac man
                if (gameMap.getCell(i, j) == 4) {
                    pacMan = new PacMan(i, j);
                } else if (gameMap.getCell(i, j) == 5) {
                    ghost.add(new Ghost(i, j, 1));
                } else if (gameMap.getCell(i, j) == 2 || gameMap.getCell(i, j) == 3) {
                    totalDots++;
                }
            }
        }
    }

    private void startGame() {
        gameTimer = new Timer(200, e -> {
            if (!gameOver) {
                moveDevils();
                checkCollision();
                repaint();
            }
        });
        gameTimer.start();
    }

    private void moveDevils() {
        for (Ghost Ghost : ghost) {
            Ghost.move(gameMap);
        }
    }

    private void checkCollision() {
        for (Ghost ghost : ghost) {
            if (pacMan.getRow() == ghost.getRow() && pacMan.getCol() == ghost.getCol()) {
                endGame("Game Over! You were caught by a devil.");
                return;
            }
        }

        if (totalDots == 0) {
            endGame("Congratulations! You've collected all the dots.");
        }
    }

    private void endGame(String message) {
        gameOver = true;
        gameTimer.stop();
        JOptionPane.showMessageDialog(this, message + " Your score: " + score);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.Src);
        
        gameMap.draw(g2d, CELL_SIZE);
        pacMan.draw(g2d, CELL_SIZE);
        for (Ghost ghost : ghost) {
            ghost.draw(g2d, CELL_SIZE);
        }
        
        // Draw score and remaining dots
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 500, 25);
        g2d.drawString("Dots left: " + totalDots, 500, 50);
        
        g2d.dispose();
    }

    public void handleKeyPress(int keyCode) {
        if (gameOver) return;

        int newRow = pacMan.getRow();
        int newCol = pacMan.getCol();

        switch (keyCode) {
            case KeyEvent.VK_UP: newRow--; break;
            case KeyEvent.VK_DOWN: newRow++; break;
            case KeyEvent.VK_LEFT: newCol--; break;
            case KeyEvent.VK_RIGHT: newCol++; break;
            default: return;
        }

        if (gameMap.isValidMove(newRow, newCol)) {
            gameMap.setCell(pacMan.getRow(), pacMan.getCol(), 0);  // Clear old position
            pacMan.move(newRow, newCol);
            
            // Update score and total dots
            int cellValue = gameMap.getCell(newRow, newCol);
            if (cellValue == 2) {
                score += 10; // Regular dot
                totalDots--;
            } else if (cellValue == 3) {
                score += 50; // Power dot
                totalDots--;
            }
            
            gameMap.setCell(newRow, newCol, 4);  // Set new position
            checkCollision();
            repaint();
        }
    }
}