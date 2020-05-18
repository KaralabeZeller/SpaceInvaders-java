package com.space.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.io.Input;
import com.space.engine.io.ModelLoader;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;
import com.space.util.Constants;
import org.lwjgl.glfw.GLFW;

public class Player extends GameObject {

    public Mesh mesh;
    private float moveSpeed = 0.2f;

    public Player() {
        super(new Vector3f(0, -12.5f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        mesh = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\player.obj", "/textures/beautiful.png" ); //TODO consolidate
        setMesh(mesh);
        setObjecType(Constants.ObjecType.PLAYER);
    }
    
    public void update() {

        if (Input.isKeyDown(GLFW.GLFW_KEY_RIGHT)) setPosition(Vector3f.add(getPosition(), new Vector3f(moveSpeed, 0, 0)));
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT)) setPosition(Vector3f.add(getPosition(), new Vector3f(-moveSpeed, 0, 0)));
    }
}
