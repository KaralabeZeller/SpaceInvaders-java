package com.space.objects;

import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Blockade {

    public List<Pixel> pixels;
    private Vector3f position;

    public Blockade(Vector3f position) {
        this.position = position;
        pixels = new ArrayList<>();
        initBlocks();
    }

    private void initBlocks() {

        for(int i = 0; i < 10; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY()-0.6f, position.getZ())));
        }
        for(int i = 0; i < 10; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ (i+20)*0.1f, position.getY()-0.6f, position.getZ())));
        }

        for(int i = 0; i < 10; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY()-0.5f, position.getZ())));
        }
        for(int i = 0; i < 10; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ (i+20)*0.1f, position.getY()-0.5f, position.getZ())));
        }

        for(int i = 0; i < 10; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY()-0.4f, position.getZ())));
        }
        for(int i = 0; i < 10; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ (i+20)*0.1f, position.getY()-0.4f, position.getZ())));
        }

        for(int i = 0; i < 30; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY()-0.3f, position.getZ())));
        }

        for(int i = 0; i < 30; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY()-0.2f, position.getZ())));
        }

        for(int i = 0; i < 30; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY()-0.1f, position.getZ())));
        }

        for(int i = 0; i < 30; i++) {
            pixels.add(new Pixel(new Vector3f(position.getX()+ i*0.1f, position.getY(), position.getZ())));
        }

    }

    public void destroy() {
        pixels.forEach(GameObject::destroy);
    }

}
