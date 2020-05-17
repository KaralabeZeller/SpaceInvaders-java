package com.space.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.io.Input;
import com.space.engine.io.ModelLoader;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;
import org.lwjgl.glfw.GLFW;

public class Pixel extends GameObject {

    public Mesh mesh;

    public Pixel(Vector3f position) {
        super(position,new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

        mesh = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\pixel.obj", "/textures/beautiful.png" ); //TODO consolidate
        setMesh(mesh);
    }

}
