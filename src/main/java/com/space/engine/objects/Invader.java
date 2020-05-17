package com.space.engine.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.maths.Vector3f;

public class Invader extends GameObject {

    public float initialX;

    public Invader(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        super(position,rotation,scale,mesh);
        initialX = position.getX();
    }

}
