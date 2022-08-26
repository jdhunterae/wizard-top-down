package org.example;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {
    private final Handler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);

            if (object.getId() == ID.Block) {
                if (getBoundsBig().intersects(object.getBounds())) {
                    x += (velX * 5) * -1;
                    y += (velY * 5) * -1;
                    velX *= -1;
                    velY *= -1;
                } else if (choose == 0) {
                    velX = (r.nextInt(8) - 4);
                    velY = (r.nextInt(8) - 4);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        // g.setColor(Color.PINK);
        // g.fillRect(x - 16, y - 16, 64, 64);

        g.setColor(Color.RED);
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
}
