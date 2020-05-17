package com.space.engine.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.io.ModelLoader;
import com.space.engine.maths.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enemies {

    private int rows;
    public Map<Integer, List<Invader>> aliens ;
    private Mesh mesh = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader1.obj", "/textures/beautiful.png" ); //TODO consolidate

    private Invader sample;
    private boolean right = true;

    public Enemies() {
        rows = 0;
        aliens = new HashMap<>();
        mesh.create();
    }

    //TODO implement ability to add more rows
    public void initRow(int count) {

        List<Invader> invaders = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            invaders.add(new Invader(new Vector3f(i*2, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh));
        }

        aliens.put(rows, invaders);

        if(rows == 0) {
            sample = aliens.get(0).get(0);
        }
        rows++;
    }

    public void destroy() {
        mesh.destroy();
    }

    public void move(float x) {
        if(sample.getPosition().getX() > sample.initialX + 2) {
            right = false;
            for(List<Invader> invaders: aliens.values()) {
                invaders.forEach(invader -> invader.moveY(-0.5f));
            }
            //rotateY(90f);
        }
        if(sample.getPosition().getX() < sample.initialX) {
            right = true;
            for(List<Invader> invaders: aliens.values()) {
                invaders.forEach(invader -> invader.moveY(-0.5f));
            }
            //rotateY(-90f);
        }

        if(right) {
            for(List<Invader> invaders: aliens.values()) {
                invaders.forEach(invader -> invader.setPosition(new Vector3f(invader.getPosition().getX() + x, invader.getPosition().getY(), invader.getPosition().getZ())));
            }

        }
        else {
            for(List<Invader> invaders: aliens.values()) {
                invaders.forEach(invader -> invader.setPosition(new Vector3f(invader.getPosition().getX() - x, invader.getPosition().getY(), invader.getPosition().getZ())));
            }
        }
    }
}
