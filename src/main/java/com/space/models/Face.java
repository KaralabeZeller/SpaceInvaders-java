package com.space.models;

import org.joml.Vector3f;

public class Face {

    public Vector3f vertex = new Vector3f();
    public Vector3f normal = new Vector3f();

    public Face(Vector3f vertex, Vector3f normal) {
        this.normal = normal;
        this.vertex = vertex;
    }
}
