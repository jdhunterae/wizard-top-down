package org.example;

import java.awt.*;

public class Box extends GameObject {
    private static final int SIZE = 32;

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
