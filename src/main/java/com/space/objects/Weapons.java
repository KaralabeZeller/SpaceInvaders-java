package com.space.objects;

import com.space.engine.maths.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Weapons {

    public List<Laser> weapons;

    public Weapons() {
        weapons = new ArrayList<>();
    }

    public void fire(Vector3f position) {
        weapons.add(new Laser(position));
    }


    public void update() {
        weapons.forEach(weapon->weapon.update());
        weapons.remove(weapons.stream().filter(weapon->!weapon.isVisible()));
    }


    public void destroy() {
        weapons.forEach(weapon->weapon.destroy());
    }
}
