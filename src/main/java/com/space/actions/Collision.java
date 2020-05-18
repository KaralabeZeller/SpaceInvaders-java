package com.space.actions;

import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;
import com.space.objects.*;
import com.space.util.Constants;

import java.util.Collections;
import java.util.List;

public class Collision {

    public Collision() {

    }

    public void detect(Enemies enemies, Weapons weapons) {
        for(List<Invader> invaders : enemies.aliens.values()) { //
            invaders.forEach(invader->weapons.weapons.forEach(laser->process(invader, laser)));
        }
    }

    public void detect(Blockades blockades, Weapons weapons) {
        blockades.blockades.forEach(blockade -> blockade.pixels.forEach(pixel->weapons.weapons.forEach(laser->process(pixel, laser))));
    }

    private void process(Invader invader, Laser laser) { //TODO detect collision -> different width of invaders

        if(!invader.isVisible() || !laser.isVisible())
            return;

        Vector3f invaderPosition = invader.getPosition();
        Vector3f laserPosition = laser.getPosition();

        if(laserPosition.getY() > invaderPosition.getY() -0.5f) {
            if(laserPosition.getX() >= invaderPosition.getX() -0.6f && laserPosition.getX() <= invaderPosition.getX() + 0.6f) {
                System.out.println("Collision invaderX: " +invaderPosition.getX() + ", laserX: " + laserPosition.getX() + " -  invaderY: " +invaderPosition.getY() + ", laserY: " + laserPosition.getY());
                invader.setVisible(false);
                laser.setVisible(false);
            }
        }
    }

    private void process(Pixel pixel, Laser laser) { //TODO detect collision -> different width of invaders

        if(!pixel.isVisible() || !laser.isVisible())
            return;

        Vector3f pixelPosition = pixel.getPosition();
        Vector3f laserPosition = laser.getPosition();

        if(laserPosition.getY() > pixelPosition.getY() -0.05f) {
            if(laserPosition.getX() >= pixelPosition.getX() -0.1f && laserPosition.getX() <= pixelPosition.getX() + 0.1f) {
                System.out.println("Collision invaderX: " +pixelPosition.getX() + ", laserX: " + laserPosition.getX() + " -  invaderY: " +pixelPosition.getY() + ", laserY: " + laserPosition.getY());
                pixel.setVisible(false);
                laser.setVisible(false);
            }
        }
    }
}
