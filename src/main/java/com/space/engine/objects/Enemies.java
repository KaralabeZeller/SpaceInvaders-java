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
    private Mesh invaderMesh1 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader1.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh2 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader2.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh3 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader3.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh4 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader4.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh5 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader5.obj", "/textures/beautiful.png" ); //TODO consolidate

    private Invader sample;
    private boolean right = true;

    public Enemies() {
        rows = 0;
        aliens = new HashMap<>();
        invaderMesh1.create();
        invaderMesh2.create();
        invaderMesh3.create();
        invaderMesh4.create();
        invaderMesh5.create();
    }

    //TODO implement ability to add more rows
    public void initRow(int count) {

        List<Invader> invaders = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            if(rows == 0)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh1));
            if(rows == 1)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh2));
            if(rows == 2)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh3));
            if(rows == 3)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh4));
            if(rows == 4)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh5));
        }

        aliens.put(rows, invaders);

        if(rows == 0) {
            sample = aliens.get(0).get(0);
        }
        rows++;
    }

    public void destroy() {
        invaderMesh1.destroy();
        invaderMesh2.destroy();
        invaderMesh3.destroy();
        invaderMesh4.destroy();
        invaderMesh5.destroy();
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
