package com.space.objects;

import com.space.engine.maths.Vector3f;
import com.space.engine.objects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Blockades {

    public List<Blockade> blockades;

    public Blockades(Vector3f position) {
        blockades = new ArrayList<>();

        initBlockades(position);
    }

    private void initBlockades(Vector3f position) {
        blockades.add(new Blockade(position));
        blockades.add(new Blockade(Vector3f.add(position, new Vector3f(8.5f,0,0))));
        blockades.add(new Blockade(Vector3f.add(position, new Vector3f(17f,0,0))));

    }

    public void destroy(){
        blockades.forEach(Blockade::destroy);
    }

    public void update() {
        for(Blockade block: blockades) {
            for(Pixel pixel: block.pixels) {
                if(!pixel.isVisible())
                    block.pixels.remove(pixel);
            }
        }
    }
}
