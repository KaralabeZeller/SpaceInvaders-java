package com.space.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.io.ModelLoader;
import com.space.engine.maths.Vector3f;
import com.space.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Enemies {

    private int rows;
    public Map<Integer, List<Invader>> aliens ; // TODO invader 1 and 2 models are not in the same Y coordinate
    private Mesh invaderMesh1 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader1.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh2 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader2.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh3 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader3.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh4 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader4.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh5 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader5.obj", "/textures/beautiful.png" ); //TODO consolidate
    private Mesh invaderMesh6 = ModelLoader.loadModel("C:\\Users\\gfert\\IdeaProjects\\SpaceInvaders\\src\\main\\resources\\models\\invader6.obj", "/textures/beautiful.png" ); //TODO consolidate

    private Invader sample;
    private boolean right = true;
    private float moveSpeedY = -0.2f;

    private int meshTimeout = 1000;
    private long lastMesh = System.currentTimeMillis();

    public Enemies() {
        rows = 0;
        aliens = new HashMap<>();
    }

    //TODO implement ability to add more rows
    public void initRow(int count) {

        List<Invader> invaders = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            if(rows == 0)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh1, invaderMesh2, Constants.ObjecType.MONSTER));
            if(rows == 1)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh3, invaderMesh4, Constants.ObjecType.MONSTER));
            if(rows == 2)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh5, invaderMesh6, Constants.ObjecType.MONSTER));
            if(rows == 3)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh3, invaderMesh4, Constants.ObjecType.MONSTER));
            if(rows == 4)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh5, invaderMesh6, Constants.ObjecType.MONSTER));
            if(rows == 5)
                invaders.add(new Invader(new Vector3f(i*2, rows*-1.0f, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), invaderMesh1, invaderMesh2, Constants.ObjecType.MONSTER));
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
        invaderMesh6.destroy();
    }

    public void move(float x) {
        if(sample.getPosition().getX() > sample.initialX + 2) {
            right = false;
            for(List<Invader> invaders: aliens.values()) {
                invaders.forEach(invader -> invader.moveY(moveSpeedY));
            }
            //rotateY(90f);
        }
        if(sample.getPosition().getX() < sample.initialX) {
            right = true;
            for(List<Invader> invaders: aliens.values()) {
                invaders.forEach(invader -> invader.moveY(moveSpeedY));
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

        if(lastMesh + meshTimeout <= System.currentTimeMillis()) {
            for(List<Invader> invaders: aliens.values()) {
                for(Invader invader: invaders) {
                    if (invader.isAlternateMesh()) {
                        invader.setAlternateMesh(false);
                    } else {
                        invader.setAlternateMesh(true);
                    }
                }
            }
            lastMesh = System.currentTimeMillis();
        }
    }

    public List<Invader> getAliveInvaders(int row) {
        return aliens.get(row).stream().filter(invader -> invader.isVisible()).collect(Collectors.toList());

    }

    public boolean isRowEmpty(int row) {
        return aliens.get(row).stream().filter(invader -> invader.isVisible()).count() > 0 ? false : true;
    }


}
