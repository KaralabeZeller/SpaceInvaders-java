package com.space;

import com.space.engine.graphics.Mesh;
import com.space.engine.graphics.Renderer;
import com.space.engine.graphics.Shader;
import com.space.engine.io.Input;
import com.space.engine.io.ModelLoader;
import com.space.engine.io.Window;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.Camera;
import com.space.engine.objects.GameObject;
import com.space.engine.objects.Invader;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public Renderer renderer;
    public Shader shader;
    public final int WIDTH = 1280, HEIGHT = 760;

    public Mesh mesh = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader1.obj", "/textures/beautiful.png" ); //TODO consolidate
    public GameObject object = new GameObject(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);
    public Camera camera = new Camera(new Vector3f(10, -5, 10), new Vector3f(0, 0, 0));

    public List<GameObject> aliens;

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
        mesh.create();
        shader.create();

        aliens = new ArrayList<>();

        //TODO handle aliens in a separate class
        int i = 0;
        while(i < 10) {
            aliens.add(new GameObject(new Vector3f(i*2, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh));
            i++;
        }
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
        moveAliens();
        window.update();
        camera.update();
    }

    private void moveAliens() {
        for(GameObject alien: aliens)
            alien.moveX(0.01f);

    }

    private void render() {
        for(GameObject alien: aliens)
            renderer.renderMesh(alien, camera);
        window.swapBuffers();
    }

    private void close() {
        window.destroy();
        mesh.destroy();
        shader.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}