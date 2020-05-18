package com.space.objects;

import com.space.actions.InvaderLaser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class AlienAttack {

    public ConcurrentLinkedQueue<InvaderLaser> lasers;
    private Enemies enemies;
    private long frequency;
    private long lastShot;
    private long nextShot;

    public AlienAttack(Enemies enemies, long frequency) {
        this.frequency = frequency;
        this.enemies = enemies;
        lasers = new ConcurrentLinkedQueue<InvaderLaser>();
        lastShot = System.currentTimeMillis();

        calculateNextShot();
    }

    public void update() {
        if(nextShot <= System.currentTimeMillis()) {
            shoot();
            calculateNextShot();
        }
        clearLasers();
    }

    private void clearLasers() {
       for (InvaderLaser laser: lasers) {
           if(!laser.isVisible())
               lasers.remove(laser);
       }
    }

    private void shoot() {
        Invader invader = getRandomInvader();

        if(invader == null)
            return;

        lasers.add(new InvaderLaser(invader.getPosition()));
    }

    // TODO refactor to be able to handle empty rows
    private Invader getRandomInvader() {
        int row = ThreadLocalRandom.current().nextInt(0, enemies.aliens.keySet().size() );

        if(enemies.getAliveInvaders(row) == null || enemies.getAliveInvaders(row).size() == 0)
            return null;

        return enemies.aliens.get(row).get(ThreadLocalRandom.current().nextInt(0, enemies.getAliveInvaders(row).size()));

    }

    private void calculateNextShot() {
        long firstShot = frequency * (ThreadLocalRandom.current().nextInt(1, 5 + 1));
        long secShot = frequency * (ThreadLocalRandom.current().nextInt(1, 3 + 1));

        nextShot = (firstShot * secShot) / 2 + System.currentTimeMillis();
    }


    public void destroy() {
        lasers.forEach(invaderLaser -> invaderLaser.destroy());
    }
}
