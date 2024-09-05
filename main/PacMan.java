package main;
import javax.swing.*;
import java.awt.*;

public class PacMan {
    private int row, col;
    private Image pacManImage, pacManRightImage, currentPacManImage;

    public PacMan(int row, int col) {
        this.row = row;
        this.col = col;
        loadImages();
    }

    private void loadImages() {
        ImageIcon icon_pacMan = new ImageIcon("/Users/willylin/Desktop/git/Pac-man/main/img/Ghost3.png");
        pacManImage = icon_pacMan.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        ImageIcon icon_pacManRight = new ImageIcon("/Users/willylin/Desktop/git/Pac-man/main/img/Ghost3.png");
        pacManRightImage = icon_pacManRight.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        currentPacManImage = pacManRightImage;
    }

    public void move(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        if (newRow > row) {
            currentPacManImage = pacManImage;
        } else {
            currentPacManImage = pacManRightImage;
        }
    }

    public void draw(Graphics2D g2d, int cellSize) {
        g2d.drawImage(currentPacManImage, col * cellSize, row * cellSize, null);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}