package com.space;

import com.space.engine.graphics.Mesh;
import com.space.engine.graphics.Renderer;
import com.space.engine.graphics.Shader;
import com.space.engine.io.Input;
import com.space.engine.io.ModelLoader;
import com.space.engine.io.Window;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.*;
import org.lwjgl.glfw.GLFW;
import org.newdawn.slick.Game;

import java.util.ArrayList;
import java.util.List;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public Renderer renderer;
    public Shader shader;
    public final int WIDTH = 1280, HEIGHT = 760;


    public Camera camera = new Camera(new Vector3f(10, -5, 10), new Vector3f(0, 0, 0));
    public Enemies enemies;
    public GameObject player;
    public Mesh meshPlayer = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\player.obj", "/textures/beautiful.png" ); //TODO consolidate

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
        meshPlayer.create();
        player = new GameObject(new Vector3f(0, -10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1),meshPlayer); //TODO move to separate class

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
        }
        close();
    }

    private void update() {
        enemies.move(0.01f);
        window.update();
        camera.update();
    }


    private void render() {
        for(List<Invader> invaders: enemies.aliens.values()) {
            invaders.forEach(invader -> renderer.renderMesh(invader, camera));
        }
        renderer.renderMesh( player , camera);
        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        enemies.destroy();
        player.destroy();
        shader.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}