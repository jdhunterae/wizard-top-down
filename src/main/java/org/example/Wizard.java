package org.example;

import java.awt.*;
import java.util.LinkedList;

public class Wizard extends GameObject {
    Handler handler;

    public Wizard(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        collision();

        if (handler.isUp()) velY = -5;
        else if (!handler.isDown()) velY = 0;

        if (handler.isDown()) velY = 5;
        else if (!handler.isUp()) velY = 0;

        if (handler.isRight()) velX = 5;
        else if (!handler.isLeft()) velX = 0;

        if (handler.isLeft()) velX = -5;
        else if (!handler.isRight()) velX = 0;
    }

    private void collision() {
        LinkedList<GameObject> objects = handler.objects;
        for (GameObject object : objects) {
            if (object.getId() == ID.Block) {
                if (getBounds().intersects(object.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 48);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 48);
    }
}
