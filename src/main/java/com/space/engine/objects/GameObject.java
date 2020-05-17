package com.space.engine.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.maths.Vector3f;

public class GameObject {
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;

	private Mesh mesh;

	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
	}

	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.rotation = rotation;
		this.scale = scale;
		this.position = position;
	}

	public void update() {
		position.setZ(position.getZ() - 0.05f);
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public void moveX(float x) {
		setPosition(new Vector3f(getPosition().getX() + x, getPosition().getY(), getPosition().getZ()));
	}

	public void moveY(float y) {
		setPosition(new Vector3f(getPosition().getX(), getPosition().getY() + y, getPosition().getZ()));
	}

	public void rotateY(float y) {
		setRotation(new Vector3f(getRotation().getX(), getRotation().getY(), getRotation().getZ() + y));
	}

	public void destroy() {
		mesh.destroy();
	}

}