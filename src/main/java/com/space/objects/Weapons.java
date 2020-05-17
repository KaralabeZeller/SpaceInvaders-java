package com.space.objects;

import com.space.engine.maths.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Weapons {

    public List<Laser> weapons;
    private int shotDelay = 100;
    private long lastShot = System.currentTimeMillis();

    public Weapons() {
        weapons = new ArrayList<>();
    }

    public void fire(Vector3f position) {
        if(lastShot + shotDelay > System.currentTimeMillis())
            return;

        weapons.add(new Laser(position));
        lastShot = System.currentTimeMillis();
    }


    public void update() {
        weapons.forEach(weapon->weapon.update());
        weapons.remove(weapons.stream().filter(weapon->!weapon.isVisible())); // TODO check if this works
    }


    public void destroy() {
        weapons.forEach(weapon->weapon.destroy());
    }
}
