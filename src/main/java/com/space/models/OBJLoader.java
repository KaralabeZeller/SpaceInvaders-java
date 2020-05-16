package com.space.models;

import org.joml.Vector3f;

import java.io.*;

public class OBJLoader {

    public static Model loadModel(File f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        Model m = new Model();
        String line;

        while((line = br.readLine()) != null) {
            if(line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.vertices.add(new Vector3f(x, y, z));
            } else if(line.startsWith("vn ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.normals.add(new Vector3f(x, y, z));
            } else if(line.startsWith("f ")){
                Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]),
                                                      Float.valueOf(line.split(" ")[2].split("/")[0]),
                                                      Float.valueOf(line.split(" ")[3].split("/")[0]));

                Vector3f normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[3]),
                                                      Float.valueOf(line.split(" ")[2].split("/")[3]),
                                                      Float.valueOf(line.split(" ")[3].split("/")[3]));

                m.faces.add(new Face(vertexIndices, normalIndices));
            }
        }

        return m;
    }
}
