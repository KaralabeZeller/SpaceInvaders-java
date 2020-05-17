package com.space.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.io.ModelLoader;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;

public class Player extends GameObject {

    public Mesh mesh;

    public Player() {
        super(new Vector3f(0, -10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        mesh = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\player.obj", "/textures/beautiful.png" ); //TODO consolidate
        setMesh(mesh);
    }
}
