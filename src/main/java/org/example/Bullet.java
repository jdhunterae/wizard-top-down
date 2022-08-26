package org.example;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {
    private final Handler handler;

    public Bullet(int x, int y, ID id, Handler handler, int mx, int my) {
        super(x, y, id);
        this.handler = handler;

        velX = (mx - x) / 10;
        velY = (my - y) / 10;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        LinkedList<GameObject> objects = handler.objects;
        for (GameObject object : objects) {
            if (object.getId() == ID.Block) {
                if (getBounds().intersects(object.getBounds())) {
                    handler.remove(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }
}
