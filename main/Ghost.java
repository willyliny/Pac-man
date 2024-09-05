package main;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Ghost {
    /*
     * row, col :  對應地圖上的位置
     * type : 分為 1, 2, 3, 4分別對應4隻不同的鬼魂
     */
    private int row, col, type;
    private String icon_path;
    private Image ghostImage;
    private Random random = new Random();

    public Ghost(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
        loadImage(this.type);
    }

    // 載入Ghost圖片 依據不同的type
    private void loadImage(int type) {
        switch(this.type) {
            case 1:
                icon_path = "/Users/willylin/Desktop/git/Pac-man/main/img/Ghost1.png";
            break;
            case 2:
                icon_path = "/Users/willylin/Desktop/git/Pac-man/main/img/Ghost2.png";
            break;
            case 3:
                icon_path = "/Users/willylin/Desktop/git/Pac-man/main/img/Ghost3.png";
            break;
            case 4:
                icon_path = "/Users/willylin/Desktop/git/Pac-man/main/img/Ghost4.png";
            break;
        }

        ImageIcon icon_ghost = new ImageIcon(icon_path);
        ghostImage = icon_ghost.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    }

    // Ghost移動
    public void move(GameMap gameMap) {
        int direction = random.nextInt(4); // 上、下、左、右 4個方向
        int newRow = row;
        int newCol = col;

        switch (direction) {
            case 0: newRow--; break; // Up
            case 1: newRow++; break; // Down
            case 2: newCol--; break; // Left
            case 3: newCol++; break; // Right
        }

        // 檢查當前地圖該位置是否可以移動
        if (gameMap.isValidMove(newRow, newCol)) {
            row = newRow;
            col = newCol;
        }
    }

    // 將圖片顯示在地圖上
    public void draw(Graphics2D g2d, int cellSize) {
        g2d.drawImage(ghostImage, col * cellSize, row * cellSize, null);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}