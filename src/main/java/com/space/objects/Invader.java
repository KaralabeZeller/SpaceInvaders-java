package com.space.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;
import com.space.util.Constants;

public class Invader extends GameObject {

    public float initialX;

    public Invader(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, Constants.ObjecType type) {
        super(position,rotation,scale,mesh);
        initialX = position.getX();
        setObjecType(type);
    }

    public Invader(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh1, Mesh mesh2, Constants.ObjecType monster) {
        super(position,rotation,scale,mesh1, mesh2);
        initialX = position.getX();
        setObjecType(monster);
    }
}
