package com.space.actions;

import com.space.engine.maths.Vector3f;
import com.space.objects.Pixel;
import com.space.util.Constants;

public class InvaderLaser extends Pixel {
    private float moveSpeed = 0.15f;

    public InvaderLaser(Vector3f position) {
        super(position);
        setObjecType(Constants.ObjecType.LASER);
    }

    public void update() {
        setPosition(Vector3f.add(getPosition(), new Vector3f(0, -moveSpeed, 0)));

        if(getPosition().getY() >= 0)
            setVisible(false);
    }

    public void moveY() {
        setPosition(Vector3f.add(getPosition(), new Vector3f(0, -moveSpeed, 0)));

        if(getPosition().getY() <= -13)
            setVisible(false);
    }
}
