package com.space.objects;

import com.space.engine.maths.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Weapons {

    public ConcurrentLinkedQueue<Laser> weapons;
    private int shotDelay = 100;
    private long lastShot = System.currentTimeMillis();

    public Weapons() {
        weapons = new ConcurrentLinkedQueue<>();
    }

    public void fire(Vector3f position) {
        if(lastShot + shotDelay > System.currentTimeMillis())
            return;

        weapons.add(new Laser(position));
        lastShot = System.currentTimeMillis();
    }


    public void update() {
        weapons.forEach(weapon->weapon.update());
       for(Laser laser: weapons) {
           if(!laser.isVisible()) {
               weapons.remove(laser);
           }
       }
    }


    public void destroy() {
        weapons.forEach(weapon->weapon.destroy());
    }
}
