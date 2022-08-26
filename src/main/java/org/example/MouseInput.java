package org.example;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    Handler handler;
    Camera camera;

    public MouseInput(Handler handler, Camera camera) {
        this.handler = handler;
        this.camera = camera;
    }

    public void mousePressed(MouseEvent e) {
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());

        for (GameObject object : handler.objects) {
            if (object.getId() == ID.Player) {
                handler.add(new Bullet(object.getX() + 16, object.getY() + 24, ID.Bullet, handler, mx, my));
            }
        }
    }
}
