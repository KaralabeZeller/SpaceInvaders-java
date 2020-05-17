package com.space.objects;

import com.space.engine.io.Input;
import com.space.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Laser extends Pixel {

    private float moveSpeed = 0.1f;

    public Laser(Vector3f position) {
        super(position);
    }

    public void update() {
       setPosition(Vector3f.add(getPosition(), new Vector3f(0, moveSpeed, 0)));

       if(getPosition().getY() >= 0)
           setVisible(false);
    }
}
