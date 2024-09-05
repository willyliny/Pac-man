package main;

import java.awt.*;

// 建立地圖
public class GameMap {
    private int[][] map;
    private Color wallColor = new Color(128, 231, 210);
    public GameMap() {
        initializeMap();
    }

    // 初始化地圖
    private void initializeMap() {
        map = new int[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,1,1,1},
            {1,3,1,1,1,2,1,2,1,1,1,3,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1},
            {1,2,1,1,1,2,1,2,1,1,1,2,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,5,1,1,2,1,2,1,5,5,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1},
            {1,2,1,1,1,2,1,2,1,1,1,2,1,1,1,1,1,1,1,1},
            {1,3,2,2,2,2,4,2,2,2,2,3,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    // 查看當前地圖該位置為哪種角色
    public int getCell(int row, int col) {
        return map[row][col];
    }

    // 設定當前地圖為對應角色
    public void setCell(int row, int col, int value) {
        map[row][col] = value;
    }

    // 偵測地圖寬度
    public int getWidth() {
        return map[0].length;
    }

    // 偵測地圖長度
    public int getHeight() {
        return map.length;
    }

    // 檢查是否能移動
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < getHeight() && col >= 0 && col < getWidth() && map[row][col] != 1;
    }

    // 繪製對應的角色在地圖上
    public void draw(Graphics2D g2d, int cellSize) {
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                switch (map[row][col]) {
                    case 0: // Empty
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(x, y, cellSize, cellSize);
                        break;
                    case 1: // Wall
                        g2d.setColor(wallColor);
                        g2d.fillRect(x, y, cellSize, cellSize);
                        break;
                    case 2: // Regular dot
                    case 3: // Power dot
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(x, y, cellSize, cellSize);
                        g2d.setColor(Color.WHITE);
                        int dotSize = (map[row][col] == 2) ? cellSize / 3 : cellSize / 2;
                        g2d.fillOval(x + (cellSize - dotSize) / 2, y + (cellSize - dotSize) / 2, dotSize, dotSize);
                        break;
                }
            }
        }
    }
}