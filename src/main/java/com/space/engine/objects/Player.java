package com.space.engine.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.io.ModelLoader;
import com.space.engine.maths.Vector3f;

public class Player extends GameObject {


    public Player(Mesh mesh) {
        super(new Vector3f(0, -100, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    }



}
