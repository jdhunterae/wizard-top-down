package org.example;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "Wizard Game v0.2";
    private boolean isRunning;
    private Thread thread;
    private final Handler handler;

    public Game() {
        new Window(1000, 563, TITLE, this);
        start();

        handler = new Handler();
        handler.add(new Box(100, 100, ID.Block));
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }

        stop();
    }

    public void tick() {
        handler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 563);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
