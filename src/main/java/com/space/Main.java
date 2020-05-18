package com.space;

import com.space.actions.Collision;
import com.space.engine.graphics.Mesh;
import com.space.engine.graphics.Renderer;
import com.space.engine.graphics.Shader;
import com.space.engine.io.Input;
import com.space.engine.io.ModelLoader;
import com.space.engine.io.Window;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.*;
import com.space.objects.*;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public Renderer renderer;
    public Shader shader;
    public final int WIDTH = 1280, HEIGHT = 760;


    public Camera camera = new Camera(new Vector3f(10, -6, 10), new Vector3f(0, 0, 0)); // TODO move everything relative to Y coordinate (Y+ instead of Y- (Y=0 -> player position))
    public Enemies enemies;
    public Player player;
    public Weapons weaponry;
    public Collision collision;
    public Blockades blockades;


    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
        window = new Window(WIDTH, HEIGHT, "Game");
        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        renderer = new Renderer(window, shader);
        window.setBackgroundColor(0.5f, 0.5f, 0.5f);
        window.create();
        shader.create();
        enemies = new Enemies();
        player = new Player();
        weaponry = new Weapons();
        collision = new Collision();
        blockades = new Blockades(new Vector3f(0,-10f,0));

        enemies.initRow(10);
        enemies.initRow(10);
        enemies.initRow(10);
        enemies.initRow(10);
        enemies.initRow(10);
        enemies.initRow(10);
    }

    public void run() {
        init();
        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
            if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) window.mouseState(true);
            if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) weaponry.fire(player.getPosition()); //TODO implement collision detection
        }
        close();
    }

    private void update() {
        enemies.move(0.01f);
        player.update();
        weaponry.update();

        collision.detect(enemies, weaponry);
        collision.detect(blockades, weaponry);
        window.update();
        camera.update();
    }


    private void render() {
        for(List<Invader> invaders: enemies.aliens.values()) {
            invaders.forEach(invader -> renderer.renderMesh(invader, camera));
        }

        weaponry.weapons.forEach(weapon->renderer.renderMesh(weapon, camera));
        blockades.blockades.forEach(blockade -> blockade.pixels.forEach(pixel->renderer.renderMesh(pixel, camera)));
        renderer.renderMesh(player , camera);
        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        enemies.destroy();
        player.destroy();
        shader.destroy();
        weaponry.destroy();
        blockades.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}